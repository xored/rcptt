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

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

public class TestRailPlugin extends Plugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.rcptt.testengine.internal.testrail"; //$NON-NLS-1$

	public static final String TESTRAIL_STATE = "TESTRAIL_STATE";
	public static final String TESTRAIL_ADDRESS = "TESTRAIL_ADDRESS";
	public static final String TESTRAIL_USERNAME = "TESTRAIL_USERNAME";
	public static final String TESTRAIL_PASSWORD = "TESTRAIL_PASSWORD";
	public static final String TESTRAIL_PROJECTID = "TESTRAIL_PROJECTID";
	public static final int DEFAULT_TESTRAIL_STATE = 0;
	public static final String DEFAULT_TESTRAIL_PROJECTID = "1";

	// The shared instance
	private static TestRailPlugin plugin;

	/**
	 * The constructor
	 */
	public TestRailPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static TestRailPlugin getDefault() {
		return plugin;
	}

	public static IEclipsePreferences getPreferences() {
		return InstanceScope.INSTANCE.getNode(PLUGIN_ID);
	}

	public static boolean getTestRailState() {
		final IEclipsePreferences preferences = getPreferences();
		return preferences.getInt(TESTRAIL_STATE, DEFAULT_TESTRAIL_STATE) == 1;
	}

	public static void setTestRailState(final int state) {
		final IEclipsePreferences preferences = getPreferences();
		preferences.putInt(TESTRAIL_STATE, state);
		try {
			preferences.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTestRailAddress() {
		final IEclipsePreferences preferences = getPreferences();
		return preferences.get(TESTRAIL_ADDRESS, "");
	}

	public static void setTestRailAddress(final String address) {
		final IEclipsePreferences preferences = getPreferences();
		preferences.put(TESTRAIL_ADDRESS, address);
		try {
			preferences.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTestRailUsername() {
		final IEclipsePreferences preferences = getPreferences();
		return preferences.get(TESTRAIL_USERNAME, "");
	}

	public static void setTestRailUsername(final String username) {
		final IEclipsePreferences preferences = getPreferences();
		preferences.put(TESTRAIL_USERNAME, username);
		try {
			preferences.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTestRailPassword() {
		final IEclipsePreferences preferences = getPreferences();
		return preferences.get(TESTRAIL_PASSWORD, "");
	}

	public static void setTestRailPassword(final String password) {
		final IEclipsePreferences preferences = getPreferences();
		preferences.put(TESTRAIL_PASSWORD, password);
		try {
			preferences.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTestRailProjectId() {
		final IEclipsePreferences preferences = getPreferences();
		return preferences.get(TESTRAIL_PROJECTID, DEFAULT_TESTRAIL_PROJECTID);
	}

	public static void setTestRailProjectId(final String projectId) {
		final IEclipsePreferences preferences = getPreferences();
		preferences.put(TESTRAIL_PROJECTID, projectId);
		try {
			preferences.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
