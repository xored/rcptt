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
package org.eclipse.rcptt.runtime.e4.ui;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class PureE4Helper {
	
	public static boolean isPureE4() {
		Bundle e4ContextsBundle = Platform.getBundle("org.eclipse.e4.core.contexts");
		boolean isE4ContextsBundleActive = e4ContextsBundle == null ? false
				: Bundle.ACTIVE == e4ContextsBundle.getState();

		// TODO (e4 support): review
		boolean isCompatibilityLayerDisabled = true;
		Bundle workbenchBundle = Platform.getBundle("org.eclipse.ui.workbench");
		if (workbenchBundle != null) {
			try {
				workbenchBundle.loadClass("org.eclipse.ui.internal.Workbench");
				isCompatibilityLayerDisabled = org.eclipse.ui.internal.Workbench.getInstance() == null;
			} catch (ClassNotFoundException e) {
				// ignore
			}
		}

		return isE4ContextsBundleActive && isCompatibilityLayerDisabled;
	}

}
