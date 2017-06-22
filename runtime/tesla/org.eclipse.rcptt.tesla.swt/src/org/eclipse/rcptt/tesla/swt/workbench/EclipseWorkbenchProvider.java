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
package org.eclipse.rcptt.tesla.swt.workbench;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.tesla.ui.SWTTeslaActivator;

public class EclipseWorkbenchProvider {
	private static IEclipseWorkbenchProvider currentProvider = null;

	public static IEclipseWorkbenchProvider getProvider() {
		initialize();
		return currentProvider;
	}

	private static void initialize() {
		if (currentProvider == null) {
			final String extensionPointId = SWTTeslaActivator.PLUGIN_ID + ".workbenchProvider";
			IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(
							extensionPointId);
			for (IConfigurationElement cfg : elements) {
				try {
					IEclipseWorkbenchProvider provider = (IEclipseWorkbenchProvider) cfg
							.createExecutableExtension("class");
					if (provider.isSupported()) {
						currentProvider = provider;
						break;
					}
				} catch (CoreException e) {
					throw new RuntimeException("Failed to create " + cfg.getName(), e);
				}
			}
			if (currentProvider == null)
				throw new NullPointerException("Failed to find " + extensionPointId
						+ " extension. Is org.eclipse.rcptt.tesla.swt.e*x extension loaded?");
		}
	}
}
