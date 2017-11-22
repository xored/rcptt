package org.eclipse.rcptt.runtime.e4.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.internal.Workbench;
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
				isCompatibilityLayerDisabled = Workbench.getInstance() == null;
			} catch (ClassNotFoundException e) {
				// ignore
			}
		}

		return isE4ContextsBundleActive && isCompatibilityLayerDisabled;
	}

}
