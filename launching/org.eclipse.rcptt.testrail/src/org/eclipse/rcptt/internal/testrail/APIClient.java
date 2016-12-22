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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class APIClient {
	private String url;
	private String username;
	private String password;

	public APIClient(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String sendGetRequest(String endpoint) {
		HttpGet request = new HttpGet(url + endpoint);
		return sendRequest(request);
	}

	public String sendPostRequest(String endpoint, String params) {
		HttpPost request = new HttpPost(url + endpoint);
		try {
			request.setEntity(new StringEntity(params));
		} catch (UnsupportedEncodingException e) {
			TestRailPlugin.log(ErrorMessages.APIClient_ErrorWhileGenerationRequest, e);
			return null;
		}
		return sendRequest(request);
	}

	private String sendRequest(HttpUriRequest request) {
		HttpClient client = createClient(request);
		try {
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			String entity = EntityUtils.toString(response.getEntity());
			if (code >= 300) {
				TestRailPlugin.log(MessageFormat.format(ErrorMessages.APIClient_HTTPError, entity));
				return null;
			}
			return entity;
		} catch (Exception e) {
			TestRailPlugin.log(ErrorMessages.APIClient_ErrorWhileSendingRequest, e);
			e.printStackTrace();
			return null;
		}
	}

	private HttpClient createClient(HttpUriRequest request) {
		// TODO (test-rail-support) use closable client?
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic " + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

		String contentTypeHeader = "application/json";
		request.addHeader(HttpHeaders.CONTENT_TYPE, contentTypeHeader);

		HttpClient client = HttpClientBuilder.create().build();
		return client;
	}
}
