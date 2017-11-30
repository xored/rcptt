/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.internal.workbench;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
// TODO (e4 support): organize plugin classes
public class TeslaWorkbenchPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.rcptt.tesla.workbench";

	// The shared instance
	private static TeslaWorkbenchPlugin plugin;

	/**
	 * The constructor
	 */
	public TeslaWorkbenchPlugin() {
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
	public static TeslaWorkbenchPlugin getDefault() {
		return plugin;
	}

	public static void log(Throwable t) {
		log(t.getMessage(), t);
	}

	public static void log(String message, Throwable t) {
		log(createStatus(message, t));
	}

	public static void log(IStatus status) {
		if (getDefault() != null) {
			getDefault().getLog().log(status);
		}
	}

	public static IStatus createStatus(String message, Throwable t) {
		return createStatus(Status.ERROR, message, t);
	}

	public static IStatus createStatus(int state, String message, Throwable t) {
		return new Status(state, PLUGIN_ID, message, t);
	}

}
