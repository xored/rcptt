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
package org.eclipse.rcptt.runtime.ui.model.e4x;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class E4ModelPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.rcptt.runtime.ui.model.e4x"; //$NON-NLS-1$

	// The shared instance
	private static E4ModelPlugin plugin;
	
	/**
	 * The constructor
	 */
	public E4ModelPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static E4ModelPlugin getDefault() {
		return plugin;
	}

	public static void log(Throwable t) {
		log(createStatus(t));
	}

	public static void log(String message, Throwable t) {
		log(createStatus(message, t));
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void info(String message, Object... args) {
		info(null, message, args);
	}

	public static void info(Throwable e, String message, Object... args) {
		log(new Status(Status.INFO, PLUGIN_ID, String.format(message, args), e));
	}

	public static void error(Throwable cause, String message, Object... args) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, String.format(message, args), cause));
	}

	public static IStatus createStatus(Throwable t) {
		return new Status(Status.ERROR, PLUGIN_ID, t.getMessage(), t);
	}

	public static IStatus createStatus(String message, Throwable t) {
		return new Status(Status.ERROR, PLUGIN_ID, message, t);
	}
}
