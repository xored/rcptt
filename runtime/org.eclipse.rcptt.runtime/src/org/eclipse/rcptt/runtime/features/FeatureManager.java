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
package org.eclipse.rcptt.runtime.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.internal.runtime.Activator;

public class FeatureManager {

	private static FeatureManager instance;

	private static List<IFeatureInstaller> installers;

	private FeatureManager() {
		installers = new ArrayList<IFeatureInstaller>();

		IConfigurationElement[] elements = Platform
				.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID + ".featureInstaller");
		for (IConfigurationElement cfg : elements) {
			try {
				IFeatureInstaller provider = (IFeatureInstaller) cfg
						.createExecutableExtension("class");
				installers.add(provider);
			} catch (CoreException e) {
				Activator.log(e);
			}
		}
	}

	public static FeatureManager getInstance() {
		if (instance == null) {
			instance = new FeatureManager();
		}
		return instance;
	}

	public void installFeatures() {
		for (IFeatureInstaller installer : installers) {
			installer.installFeatures();
		}
	}

}
