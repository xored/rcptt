package org.eclipse.rcptt.rap.aspects;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.eclipse.rap.rwt.internal.application.ApplicationContextImpl;
import org.eclipse.rcptt.reporting.core.ReportManager;
import org.eclipse.rcptt.runtime.ui.rap.AutEventManager;
import org.eclipse.rcptt.runtime.ui.rap.Q7ServerStarter;
import org.eclipse.rcptt.tesla.core.am.rap.AspectManager;

public aspect RapAspect {
	public RapAspect() {
		AspectManager.activateAspect(RapAspectActivator.PLUGIN_ID, this.getClass().getName());
	}

	@SuppressAjWarnings("adviceDidNotMatch")
	after(ApplicationContextImpl app):
		execution(void org.eclipse.rap.rwt.internal.application.ApplicationContextImpl.activate ()) && target(app) {
		if (app.allowsRequests()) {
			Q7ServerStarter.INSTANCE.start();
			// Send a started object
			AutEventManager.getInstance().sendStartup();
		} else {
			// log error
		}
	}

	@SuppressAjWarnings("adviceDidNotMatch")
	after(ApplicationContextImpl app):
		execution(void org.eclipse.rap.rwt.internal.application.ApplicationContextImpl.deactivate ()) && target(app) {

		ReportManager.storeState();
	}
}
