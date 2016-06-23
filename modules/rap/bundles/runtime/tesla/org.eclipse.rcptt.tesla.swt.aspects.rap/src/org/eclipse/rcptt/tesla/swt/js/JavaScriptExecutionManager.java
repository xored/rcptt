package org.eclipse.rcptt.tesla.swt.js;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Artem Kovalev
 */
public class JavaScriptExecutionManager {

	private static JavaScriptExecutionManager manager;

	public static JavaScriptExecutionManager getManager()
	{
		if(manager == null){
			manager = new JavaScriptExecutionManager();
		}
		return manager;
	}

	private final AtomicBoolean stoped = new AtomicBoolean(false);

	public  void stop()
	{
		stoped.set(true);
	}

	public  void start()
	{
		stoped.set(false);
	}

	public  boolean isActive()
	{
		return !stoped.get();
	}

}
