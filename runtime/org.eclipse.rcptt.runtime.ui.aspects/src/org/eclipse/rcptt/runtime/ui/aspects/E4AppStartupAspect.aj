package org.eclipse.rcptt.runtime.ui.aspects;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.rcptt.runtime.ui.AutEventManager;
import org.eclipse.rcptt.runtime.ui.Q7ServerStarter;
import org.eclipse.rcptt.tesla.core.am.AspectManager;

public aspect E4AppStartupAspect {

	public E4AppStartupAspect() {
		AspectManager.activateAspect(E4UIAspectsActivator.PLUGIN_ID, this.getClass().getName());
	}

	pointcut capturePostParamenter(String topic, Object data) 
		: execution(boolean org.eclipse.e4.ui.services.internal.events.EventBroker.post(String, Object)) 
			&& args(topic, data) && target(org.eclipse.e4.ui.services.internal.events.EventBroker);

	@SuppressAjWarnings("adviceDidNotMatch")
	before(String topic, Object data) : capturePostParamenter(topic, data)
	   {

		if (UIEvents.UILifeCycle.APP_STARTUP_COMPLETE.equals(topic)) {

			Q7ServerStarter.INSTANCE.start();
			AutEventManager.getInstance().sendStartup();

		}
	}

}
