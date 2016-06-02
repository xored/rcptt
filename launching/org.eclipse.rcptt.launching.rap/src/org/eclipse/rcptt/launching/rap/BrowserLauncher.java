package org.eclipse.rcptt.launching.rap;

import static org.eclipse.rcptt.launching.rap.Activator.PLUGIN_ID;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class BrowserLauncher {

	private final static String POINT_ID = PLUGIN_ID + ".browserLaunchDelegate"; //$NON-NLS-1$
	private IBrowserLaunchDelegate[] delegates = null;

	public void launch(URL uri, RAPLaunchConfig config) throws CoreException {
		for (IBrowserLaunchDelegate delegate : getLaunchers()) {
			delegate.launch(uri, config);
		}
	}

	private synchronized IBrowserLaunchDelegate[] getLaunchers() throws CoreException {
		if (delegates == null) {
			final IExtensionRegistry registry = Platform.getExtensionRegistry();
			final IConfigurationElement[] elements = registry.getConfigurationElementsFor(POINT_ID);

			final IBrowserLaunchDelegate[] result = new IBrowserLaunchDelegate[elements.length];
			for (int i = 0; i < elements.length; i++) {
				result[i] = (IBrowserLaunchDelegate) elements[i].createExecutableExtension("class"); //$NON-NLS-1$
			}

			delegates = result;
		}
		return delegates;
	}
}
