/*******************************************************************************
 * Copyright (c) 2009, 2017 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.internal.runtime.ui;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.rcptt.reporting.core.ReportManager;
import org.eclipse.rcptt.runtime.ui.AutEventManager;
import org.eclipse.rcptt.runtime.ui.Q7ServerStarter;
import org.eclipse.rcptt.tesla.internal.core.ITeslaE4LifeCycleHandler;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

public class Q7RuntimeStartup implements IStartup, ITeslaE4LifeCycleHandler {
	@Override
	public void earlyStartup() {
		try {
			Q7ServerStarter.INSTANCE.start();
			PlatformUI.getWorkbench().addWorkbenchListener(
					new IWorkbenchListener() {
						@Override
						public boolean preShutdown(IWorkbench workbench,
								boolean forced) {
							Activator.info(new Exception(
									"Just to get a current stack trace logged. This is not an error."),
									"Workbench is about to shut down");
							return true;
						}

						@Override
						public void postShutdown(IWorkbench workbench) {
							ReportManager.storeState();
							tryTerminateLaunches();
						}
					});
		} finally {
			// Send a started object
			AutEventManager.getInstance().sendStartup();
		}
	}

	@Override
	public void startup() {
		try {
			Q7ServerStarter.INSTANCE.start();
		} finally {
			// Send a started object
			AutEventManager.getInstance().sendStartup();
		}

	}

	@Override
	public void shutdown() {
		Activator.info(new Exception("Just to get a current stack trace logged. This is not an error."),
				"Workbench is about to shut down");
		ReportManager.storeState();
		tryTerminateLaunches();
	}

	private void tryTerminateLaunches() {
		try {
			// shutdown all launch configurations
			ILaunchManager manager = DebugPlugin.getDefault()
					.getLaunchManager();
			for (ILaunch launch : manager.getLaunches()) {
				launch.terminate();
			}
		} catch (Throwable e) {
			// do nothing
		}
	}
}
