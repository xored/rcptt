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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.internal.core.model.Q7TestCase;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.internal.testrail.TestRailAPIClient;
import org.eclipse.rcptt.internal.testrail.TestRailPlugin;
import org.eclipse.rcptt.launching.ITestEngineListener;
import org.eclipse.rcptt.testrail.domain.TestRailTestResult;
import org.eclipse.rcptt.testrail.domain.TestRailTestRun;

import com.google.common.base.Joiner;

public class TestRailService implements ITestEngineListener {
	private static final String TESTRAIL_ID = "testrail-id";
	private static final String TESTRUN_NAME_PREFIX = "RCPTT Test Run ";
	private static final String TESTRESULT_COMMENT_PREFIX = "Context: ";

	private boolean testRailEnabled;
	private TestRailAPIClient testRailAPI;

	public TestRailService() {
		this.testRailEnabled = TestRailPlugin.getTestRailState();
		this.testRailAPI = new TestRailAPIClient();
	}

	@Override
	public void executionCompleted(List<EclScenarioExecutable> scenarios) {
		if (!testRailEnabled) {
			return;
		}
		try {
			TestRailTestRun testRunDraft = createTestRunDraft(scenarios);
			if (testRunDraft == null) {
				return;
			}

			TestRailTestRun testRun = testRailAPI.addRun(testRunDraft);
			String testRunId = testRun.getId();

			scenarios.stream()
					.forEach(scenario -> addTestResult(testRunId, scenario));
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
		}
	}

	private TestRailTestRun createTestRunDraft(List<EclScenarioExecutable> scenarios) {
		List<String> caseIds = scenarios.stream()
				.map(scenario -> getTestRailId(scenario))
				.filter(testRailId -> testRailId != null && !testRailId.equals(""))
				.collect(Collectors.toList());
		
		if (caseIds.isEmpty()) {
			return null;
		}

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		LocalDateTime localDate = LocalDateTime.now();
		String name = TESTRUN_NAME_PREFIX + dateFormatter.format(localDate);

		TestRailTestRun testRunDraft = new TestRailTestRun();
		testRunDraft.setName(name);
		testRunDraft.setIncludeAll(false);
		testRunDraft.setCaseIds(caseIds);
		return testRunDraft;
	}

	private String getTestRailId(EclScenarioExecutable scenario) {
		try {
			Q7TestCase q7TestCase = (Q7TestCase) scenario.getActualElement();
			return q7TestCase.getProperties().get(TESTRAIL_ID);
		} catch (ModelException e) {
			// TODO (test-rail-support) Add a proper logging
			return null;
		}
	}

	private void addTestResult(String testRunId, EclScenarioExecutable scenario) {
		try {
			Q7TestCase q7TestCase = (Q7TestCase) scenario.getActualElement();
			String testCaseId = q7TestCase.getProperties().get(TESTRAIL_ID);
			if (testCaseId == null) {
				return;
			}

			TestRailTestResult testResultDraft = createTestResultDraft(scenario);
			if (testResultDraft != null) {
				testResultDraft.setRunId(testRunId);
				testResultDraft.setCaseId(testCaseId);
				testRailAPI.addResultForTestCase(testResultDraft);
			}
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
		}
	}

	private TestRailTestResult createTestResultDraft(EclScenarioExecutable scenario) {
		String testCaseStatusId = "";
		String testCaseComment = "";

		List<String> variantName = scenario.getVariantName();
		if (variantName != null && !variantName.isEmpty()) {
			testCaseComment = TESTRESULT_COMMENT_PREFIX + Joiner.on(',').join(variantName);
		}

		int testCaseStatus = scenario.getResultStatus().getSeverity();
		switch (testCaseStatus) {
		case IStatus.OK:
		case IStatus.INFO:
			testCaseStatusId = "1";
			break;
		case IStatus.WARNING:
		case IStatus.ERROR:
			testCaseStatusId = "5";
			break;
		case IStatus.CANCEL:
			return null;
		}

		TestRailTestResult testResultDraft = new TestRailTestResult();
		testResultDraft.setStatus(testCaseStatusId);
		testResultDraft.setComment(testCaseComment);
		return testResultDraft;
	}
}
