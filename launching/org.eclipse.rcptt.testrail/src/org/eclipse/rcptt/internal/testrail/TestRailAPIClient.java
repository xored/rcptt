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
package org.eclipse.rcptt.internal.testrail;

import java.text.MessageFormat;

import org.eclipse.rcptt.testrail.domain.TestRailTestResult;
import org.eclipse.rcptt.testrail.domain.TestRailTestRun;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestRailAPIClient {
	private static final String ENDPOINT = "index.php?/api/v2";
	private APIClient client;
	private String projectId;

	public TestRailAPIClient() {
		final String address = TestRailPlugin.getTestRailAddress() + ENDPOINT;
		final String username = TestRailPlugin.getTestRailUsername();
		final String password = TestRailPlugin.getTestRailPassword();
		this.client = new APIClient(address, username, password);

		final String projectId = TestRailPlugin.getTestRailProjectId();
		this.projectId = projectId.substring(1);
	}

	public TestRailTestRun addRun(TestRailTestRun testRunDraft) {
		String method = MessageFormat.format("/add_run/{0}", projectId);
		String params = new Gson().toJson(testRunDraft).toString();
		String response = client.sendPostRequest(method, params);
		if (response == null) {
			TestRailPlugin.log(ErrorMessages.TestRailAPIClient_FailedToAddTestRun);
			return null;
		}

		TypeToken<TestRailTestRun> token = new TypeToken<TestRailTestRun>() {
		};
		TestRailTestRun testRun = new Gson().fromJson(response, token.getType());
		return testRun;
	}

	public void addResultForTestCase(TestRailTestResult testCaseResult) {
		String method = MessageFormat.format("/add_result_for_case/{0}/{1}",
				testCaseResult.getRunId(), testCaseResult.getCaseId());
		String params = new Gson().toJson(testCaseResult).replace("\\n", "\n").toString();
		String response = client.sendPostRequest(method, params);
		if (response == null) {
			TestRailPlugin.log(ErrorMessages.TestRailAPIClient_FailedToAddTestResult);
		}
	}
}
