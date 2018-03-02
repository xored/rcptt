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
package org.eclipse.rcptt.tesla.swt;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.tesla.ui.SWTTeslaActivator;
import org.eclipse.swt.widgets.Display;

public class TeslaDisplayProvider {

	private static ITeslaDisplayProvider provider = null;

	private static ITeslaDisplayProvider getProvider() {
		if (provider == null) {
			final String extensionPointId = SWTTeslaActivator.PLUGIN_ID + ".displayProvider";
			IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(extensionPointId);
			for (IConfigurationElement element : elements) {
				try {
					ITeslaDisplayProvider extension = (ITeslaDisplayProvider) element
							.createExecutableExtension("class");
					if (extension.isSupported()) {
						provider = extension;
					}
				} catch (CoreException e) {
					throw new RuntimeException("Failed to create " + element.getName(), e);
				}
			}
			if (provider == null) {
				provider = new DefaultProvider();
			}
		}
		return provider;
	}

	public static Display getDisplay() {
		return getProvider().getDisplay();
	}

	private static class DefaultProvider implements ITeslaDisplayProvider {

		@Override
		public boolean isSupported() {
			return true;
		}

		@Override
		public Display getDisplay() {
			return Display.getDefault();
		}

	}

}
