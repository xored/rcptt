package org.eclipse.rcptt.tesla.ui;

import org.eclipse.rcptt.tesla.swt.events.TeslaEventManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

public class RWTUtils {

	public static Display findDisplay()
	{
		return TeslaEventManager.getManager().getDisplay();
	}

	public static IWorkbench getWorkbench()
	{
		return (IWorkbench)TeslaEventManager.getManager().getWorkbench();
	}
	public static IWorkbenchWindow[] getWorkbenchWindows() {
		return getWorkbench() == null ? new IWorkbenchWindow[0] : getWorkbench().getWorkbenchWindows();
	}

}
