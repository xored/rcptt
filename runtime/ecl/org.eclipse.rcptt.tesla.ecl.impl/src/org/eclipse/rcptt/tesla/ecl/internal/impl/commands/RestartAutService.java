/*******************************************************************************
 * Copyright (c) 2009, 2018 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.ecl.internal.impl.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.ecl.impl.IRestartAutService;
import org.eclipse.rcptt.tesla.ecl.internal.impl.TeslaImplPlugin;

public class RestartAutService implements ICommandService {

	private static IRestartAutService service = null;

	@Override
	public IStatus service(Command command, IProcess context) throws CoreException {
		getService().restart();
		return Status.OK_STATUS;
	}

	private static IRestartAutService getService() {
		if (service == null) {
			final String extensionPointId = TeslaImplPlugin.PLUGIN_ID + ".restartService";
			IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(extensionPointId);
			for (IConfigurationElement element : elements) {
				try {
					IRestartAutService extension = (IRestartAutService) element
							.createExecutableExtension("class");
					if (extension.isSupported()) {
						service = extension;
					}
				} catch (CoreException e) {
					throw new RuntimeException("Failed to create " + element.getName(), e);
				}
			}
			if (service == null) {
				throw new NullPointerException("Failed to find " + extensionPointId
						+ " extension. Is 'org.eclipse.rcptt.tesla.ecl.impl.*' extension loaded?");
			}
		}
		return service;
	}

}
