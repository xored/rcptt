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
package org.eclipse.rcptt.testengine.internal.testrail;

import java.nio.charset.Charset;

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

	private String sendRequest(HttpUriRequest request) throws Exception {
		// TODO (test-rail-support) use closable client?
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic " + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

		String contentTypeHeader = "application/json";
		request.addHeader(HttpHeaders.CONTENT_TYPE, contentTypeHeader);

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);
		return EntityUtils.toString(response.getEntity());
	}

	public String sendGetRequest(String endpoint) throws Exception {
		HttpGet request = new HttpGet(url + endpoint);
		return sendRequest(request);
	}

	public String sendPostRequest(String endpoint, String params) throws Exception {
		HttpPost request = new HttpPost(url + endpoint);
		request.setEntity(new StringEntity(params));
		return sendRequest(request);
	}
}
