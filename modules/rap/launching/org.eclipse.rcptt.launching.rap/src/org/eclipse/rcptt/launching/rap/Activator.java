package org.eclipse.rcptt.launching.rap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {
	public final static String PLUGIN_ID = "org.eclipse.rcptt.launching.rap"; //$NON-NLS-1$

	private static Activator plugin;

	public static Activator getDefault() {
		return plugin;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public void errorLog(String messge, Throwable e) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, messge, e);
		getLog().log(status);
	}

}
