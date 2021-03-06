/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.launching.p2utils;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Q7P2UtilsActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.rcptt.launching.p2utils"; //$NON-NLS-1$

	// The shared instance
	private static Q7P2UtilsActivator plugin;
	
	/**
	 * The constructor
	 */
	public Q7P2UtilsActivator() {
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
	
	public static Bundle bundle() {
		return Platform.getBundle(PLUGIN_ID);
	}

	public static ILog log() {
		return Platform.getLog(bundle());
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Q7P2UtilsActivator getDefault() {
		return plugin;
	}
	public void log(Throwable e) {
		log(status(e));
	}

	public void log(String msg, Throwable e) {
		log(status(msg, e));
	}

	public void info(String msg) {
		log(new Status(IStatus.INFO, PLUGIN_ID, msg, null));
	}

	public static IStatus status(Throwable e) {
		return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
	}

	public static IStatus status(String msg, Throwable e) {
		return new Status(IStatus.ERROR, PLUGIN_ID, msg, e);
	}

	public static IStatus status(String msg) {
		return new Status(IStatus.ERROR, PLUGIN_ID, msg);
	}

	public static void log(IStatus status) {
		log().log(status);
	}

}
