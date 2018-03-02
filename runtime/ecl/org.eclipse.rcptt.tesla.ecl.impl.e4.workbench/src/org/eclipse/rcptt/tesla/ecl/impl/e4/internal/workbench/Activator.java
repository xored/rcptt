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
package org.eclipse.rcptt.tesla.ecl.impl.e4.internal.workbench;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.tesla.ecl.impl.TeslaBridge;
import org.osgi.framework.BundleContext;

// TODO (e4 support): remove a dependency on org.eclipse.rcptt.runtime.e4.ui?
public class Activator extends Plugin {
	public static final String PLUGIN_ID = "org.eclipse.rcptt.tesla.ecl.impl.e3";
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		TeslaBridge.shutdown(); // Need for test cases that use AUT restart
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static IStatus err(String message) {
		return err(message, null);
	}

	public static IStatus err(String message, Throwable throwable) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message, throwable);
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static CoreException makeCoreException(String message) {
		return makeCoreException(message, null);
	}

	public static CoreException makeCoreException(String message, Throwable e) {
		return new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, message,
				e));
	}
}
