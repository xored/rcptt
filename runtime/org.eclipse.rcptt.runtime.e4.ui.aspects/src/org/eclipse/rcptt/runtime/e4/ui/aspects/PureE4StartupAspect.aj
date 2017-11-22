package org.eclipse.rcptt.runtime.e4.ui.aspects;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.rcptt.runtime.AutEventManager;
import org.eclipse.rcptt.runtime.Q7ServerStarter;
import org.eclipse.rcptt.runtime.e4.ui.PureE4Helper;
import org.eclipse.rcptt.tesla.core.am.AspectManager;

public aspect PureE4StartupAspect {

	public PureE4StartupAspect() {
		AspectManager.activateAspect(Activator.PLUGIN_ID, this.getClass().getName());
	}

	pointcut postEvent(String topic, Object data) 
		: execution(boolean org.eclipse.e4.ui.services.internal.events.EventBroker.post(String, Object)) 
			&& args(topic, data) && target(org.eclipse.e4.ui.services.internal.events.EventBroker);

	@SuppressAjWarnings("adviceDidNotMatch")
	before(String topic, Object data) : postEvent(topic, data) {
		if (UIEvents.UILifeCycle.APP_STARTUP_COMPLETE.equals(topic)
				&& PureE4Helper.isPureE4()) {
			Q7ServerStarter.INSTANCE.start();
			AutEventManager.getInstance().sendStartup();
		}
	}

}
