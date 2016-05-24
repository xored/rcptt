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
package org.eclipse.rcptt.internal.runtime.ui;



import static org.eclipse.rap.rwt.internal.service.ContextProvider.getApplicationContext;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

import org.eclipse.rcptt.reporting.core.ReportManager;
import org.eclipse.rcptt.runtime.ui.AutEventManager;
import org.eclipse.rcptt.runtime.ui.Q7ServerStarter;
import org.eclipse.rcptt.runtime.ui.RAPPhaseListener;
import org.eclipse.rcptt.tesla.ui.RWTUtils;

public class Q7RuntimeStartup implements IStartup {
	public void earlyStartup() {
		try {

			getApplicationContext().getLifeCycleFactory().getLifeCycle().addPhaseListener(new RAPPhaseListener());

			if(RWTUtils.getWorkbench()!= null)
			{
			RWTUtils.getWorkbench().addWorkbenchListener(
					new IWorkbenchListener() {
						public boolean preShutdown(IWorkbench workbench,
								boolean forced) {
							Activator.info(new Exception(
									"Just to get a current stack trace logged. This is not an error."),
									"Workbench is about to shut down");
							return true;
						}

						public void postShutdown(IWorkbench workbench) {
							ReportManager.storeState();
							tryTerminateLaunches();
						}
					});
			}
		} finally {
			Q7ServerStarter.INSTANCE.start();
			// Send a started object
			AutEventManager.getInstance().sendStartup();
		}
	}

	private void tryTerminateLaunches() {
		try {
			// shutdown all launch configurations
//			ILaunchManager manager = DebugPlugin.getDefault()
//					.getLaunchManager();
//			for (ILaunch launch : manager.getLaunches()) {
//				launch.terminate();
//			}
		} catch (Throwable e) {
			// do nothing
		}
	}
}
