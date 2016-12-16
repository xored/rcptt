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
package org.eclipse.rcptt.testengine.testrail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.internal.core.model.Q7TestCase;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.launching.ITestEngineListener;
import org.eclipse.rcptt.testengine.testrail.domain.TestRailTestResult;
import org.eclipse.rcptt.testengine.testrail.domain.TestRailTestRun;

public class TestRailService implements ITestEngineListener {
	private String TESTRUN_NAME_PREFIX = "RCPTT Test Run ";
	private TestRailAPIClient testRailAPI;

	public TestRailService() {
		this.testRailAPI = new TestRailAPIClient();
	}

	@Override
	public void executionCompleted(List<EclScenarioExecutable> scenarios) {
		try {
			TestRailTestRun testRunDraft = createTestRunDraft(scenarios);
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
				.filter(testRailId -> testRailId != "")
				.collect(Collectors.toList());
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
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
			return q7TestCase.getTestRailId();
		} catch (ModelException e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
			return "";
		}
	}

	private void addTestResult(String testRunId, EclScenarioExecutable scenario) {
		try {
			Q7TestCase q7TestCase = (Q7TestCase) scenario.getActualElement();
			String testCaseId = q7TestCase.getTestRailId();

			TestRailTestResult testResultDraft = createTestResultDraft(scenario);
			testResultDraft.setRunId(testRunId);
			testResultDraft.setCaseId(testCaseId);
			testRailAPI.addResultForTestCase(testResultDraft);
		} catch (Exception e) {
			// TODO (test-rail-support) catch exception
			e.printStackTrace();
		}
	}

	private TestRailTestResult createTestResultDraft(EclScenarioExecutable scenario) {
		int testCaseStatus = scenario.getResultStatus().getSeverity();
		String testCaseStatusId = "";
		String testCaseComment = scenario.getResultStatus().getMessage();

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
