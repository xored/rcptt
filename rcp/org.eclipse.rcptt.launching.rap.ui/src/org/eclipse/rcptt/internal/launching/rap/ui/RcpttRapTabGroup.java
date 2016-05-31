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
package org.eclipse.rcptt.internal.launching.rap.ui;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.pde.ui.launcher.EclipseLauncherTabGroup;
import org.eclipse.rcptt.internal.launching.ext.UpdateVMArgs;
import org.eclipse.rcptt.launching.target.ITargetPlatformHelper;

public class RcpttRapTabGroup extends EclipseLauncherTabGroup {

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = null;
		tabs = new ILaunchConfigurationTab[] {
				new RapAUTMainTab(this),

				// new AUTArgumentsTab(), new ConfigurationTab(),
				// new TracingTab(), new EnvironmentTab(), new AUTCommonTab()
		};
		setTabs(tabs);
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		super.setDefaults(configuration);

		UpdateVMArgs.updateVMArgs(configuration);
	}

	public void doUpdate(ITargetPlatformHelper info) {
		ILaunchConfigurationTab[] tabs = getTabs();
		for (ILaunchConfigurationTab tab : tabs) {
			if (tab instanceof IAUTListener) {
				((IAUTListener) tab).update(info);
			}
		}
	}
}
