/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.testrail;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.internal.core.model.Q7TestCase;
import org.eclipse.rcptt.internal.launching.Executable;
import org.eclipse.rcptt.internal.launching.ExecutionSession;
import org.eclipse.rcptt.internal.launching.GroupExecutable;
import org.eclipse.rcptt.internal.launching.PrepareExecutionWrapper;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.internal.testrail.TestRailAPIClient;
import org.eclipse.rcptt.internal.testrail.TestRailPlugin;
import org.eclipse.rcptt.launching.IExecutable;
import org.eclipse.rcptt.launching.ITestEngine;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.testrail.domain.TestRailTestResult;
import org.eclipse.rcptt.testrail.domain.TestRailTestRun;

public class TestRailService implements ITestEngine {
	private static final String TESTRAIL_ID_PARAM = "testrail-id";
	private static final String TESTRESULT_CONTEXT_PREFIX = "__Contexts:__ ";
	private static final String TESTRESULT_FAILMSG_PREFIX = "__Fail message:__\n";

	private TestRailAPIClient testRailAPI;
	private boolean testRailEnabled;
	private String testRunId;

	public TestRailService() {
		this.testRailEnabled = TestRailPlugin.getTestRailState();
		this.testRailAPI = new TestRailAPIClient();
	}

	@Override
	public void sessionStarted(ExecutionSession session) {
		this.testRunId = null;
		if (!testRailEnabled) {
			return;
		}

		try {
			TestRailTestRun testRunDraft = createTestRunDraft(session);
			if (testRunDraft == null) {
				return;
			}

			TestRailTestRun testRun = testRailAPI.addRun(testRunDraft);
			this.testRunId = testRun.getId();
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
		}
	}

	@Override
	public void sessionCompleted(ExecutionSession session) {
	}

	@Override
	public void executionStarted(EclScenarioExecutable scenario) {
	}

	@Override
	public void executionCompleted(EclScenarioExecutable scenario, Report report) {
		if (!testRailEnabled) {
			return;
		}
		if (testRunId == null) {
			return;
		}

		try {
			TestRailTestResult testResultDraft = createTestResultDraft(scenario, report);
			if (testResultDraft == null) {
				return;
			}

			testRailAPI.addResultForTestCase(testResultDraft);
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
		}
	}

	private TestRailTestRun createTestRunDraft(ExecutionSession session) {
		String name = getTestRunName(session);
		List<String> caseIds = getTestRunCaseIds(session);
		if (caseIds.isEmpty()) {
			return null;
		}

		TestRailTestRun testRunDraft = new TestRailTestRun();
		testRunDraft.setName(name);
		testRunDraft.setIncludeAll(false);
		testRunDraft.setCaseIds(caseIds);
		return testRunDraft;
	}

	private TestRailTestResult createTestResultDraft(EclScenarioExecutable scenario, Report report) {
		try {
			String testCaseId = getTestRailId(scenario);
			if (testCaseId == null) {
				return null;
			}

			String testCaseStatus = getTestRailStatus(scenario);
			if (testCaseStatus == null) {
				return null;
			}

			String testCaseDuration = getTestRailDuration(report);
			String testCaseComment = getTestRailComment(scenario, report);

			TestRailTestResult testResultDraft = new TestRailTestResult();
			testResultDraft.setRunId(testRunId);
			testResultDraft.setCaseId(testCaseId);
			testResultDraft.setStatus(testCaseStatus);
			testResultDraft.setElapsed(testCaseDuration);
			testResultDraft.setComment(testCaseComment);
			return testResultDraft;
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
			return null;
		}
	}

	private EclScenarioExecutable getScenario(Executable wrapper) {
		if (wrapper instanceof PrepareExecutionWrapper) {
			IExecutable executable = ((PrepareExecutionWrapper) wrapper).getExecutable();
			if (executable instanceof GroupExecutable) {
				IExecutable scenario = ((GroupExecutable) executable).getRoot();
				if (scenario instanceof EclScenarioExecutable)
					return (EclScenarioExecutable) scenario;
			}
		}
		return null;
	}

	private String getTestRailId(EclScenarioExecutable scenario) {
		try {
			Q7TestCase q7TestCase = (Q7TestCase) scenario.getActualElement();
			return q7TestCase.getProperties().get(TESTRAIL_ID_PARAM);
		} catch (ModelException e) {
			// TODO (test-rail-support) Add a proper logging
			return null;
		}
	}

	private String getTestRunName(ExecutionSession session) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		LocalDateTime localDate = LocalDateTime.now();
		return MessageFormat.format("{0} {1}", session.getName(), dateFormatter.format(localDate));
	}

	private List<String> getTestRunCaseIds(ExecutionSession session) {
		final Executable[] executables = session.getExecutables();
		final List<EclScenarioExecutable> scenarios = Arrays.stream(executables)
				.map(wrapper -> getScenario(wrapper))
				.filter(scenario -> scenario != null)
				.collect(Collectors.toList());

		final List<String> caseIds = scenarios.stream()
				.map(scenario -> getTestRailId(scenario))
				.filter(testRailId -> testRailId != null)
				.collect(Collectors.toList());

		return caseIds;
	}

	private String getTestRailDuration(Report report) {
		Node reportRoot = report.getRoot();
		long duration = reportRoot.getEndTime() - reportRoot.getStartTime();

		if (duration != 0) {
			return MessageFormat.format("{0}s", String.format("%d", duration / 1000));
		}
		return null;
	}

	private String getTestRailStatus(EclScenarioExecutable scenario) {
		int severity = scenario.getResultStatus().getSeverity();

		switch (severity) {
		case IStatus.OK:
		case IStatus.INFO:
			return "1";
		case IStatus.WARNING:
		case IStatus.ERROR:
			return "5";
		case IStatus.CANCEL:
			return null;
		}
		return null;
	}

	private String getTestRailComment(EclScenarioExecutable scenario, Report report) {
		String testCaseComment = "";

		List<String> variantName = scenario.getVariantName();
		if (variantName != null && !variantName.isEmpty()) {
			String contextNames = variantName.stream()
					.collect(Collectors.joining(", "))
					.toString();
			testCaseComment = TESTRESULT_CONTEXT_PREFIX + contextNames;
		}

		int testCaseSeveriry = scenario.getResultStatus().getSeverity();
		if (testCaseSeveriry == IStatus.ERROR) {
			if (!testCaseComment.equals("")) {
				testCaseComment += "\n\n";
			}

			Node reportRoot = report.getRoot();
			testCaseComment += TESTRESULT_FAILMSG_PREFIX + ReportUtils.getFailMessage(reportRoot);
		}

		return testCaseComment;
	}
}
