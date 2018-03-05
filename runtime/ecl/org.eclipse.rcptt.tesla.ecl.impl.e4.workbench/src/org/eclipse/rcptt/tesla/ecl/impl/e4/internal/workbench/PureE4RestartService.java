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

import org.eclipse.rcptt.runtime.e4.ui.PureE4Helper;
import org.eclipse.rcptt.runtime.e4.ui.PureE4ModelProcessor;
import org.eclipse.rcptt.tesla.ecl.impl.IRestartAutService;
import org.eclipse.rcptt.tesla.ecl.internal.impl.commands.ShutdownAutService;
import org.eclipse.swt.widgets.Display;

public class PureE4RestartService implements IRestartAutService {

	@Override
	public boolean isSupported() {
		return PureE4Helper.isPureE4();
	}

	@Override
	public void restart() {
		ShutdownAutService.tryTerminateLaunches();
		// TODO (e4 support): save all dirtyable parts
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				PureE4ModelProcessor.getWorkbench().restart();
			}
		});
	}

}
