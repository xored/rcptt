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
package org.eclipse.rcptt.tesla.ecl.impl.internal.workbench;

import org.eclipse.rcptt.tesla.ecl.impl.IRestartAutService;
import org.eclipse.rcptt.tesla.ecl.internal.impl.commands.ShutdownAutService;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class WorkbenchRestartService implements IRestartAutService {

	@Override
	public boolean isSupported() {
		return PlatformUI.isWorkbenchRunning();
	}

	@Override
	public void restart() {
		ShutdownAutService.tryTerminateLaunches();
		PlatformUI.getWorkbench().saveAllEditors(false);
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				PlatformUI.getWorkbench().restart();
			}
		});
	}

}
