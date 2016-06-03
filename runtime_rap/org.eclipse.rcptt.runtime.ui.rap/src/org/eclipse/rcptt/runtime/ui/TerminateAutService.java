package org.eclipse.rcptt.runtime.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.Client;
import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.ui.RWTUtils;
import org.eclipse.swt.widgets.Display;


public class TerminateAutService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context) throws InterruptedException, CoreException {
		Display display = RWTUtils.findDisplay();
		if (display != null) {
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					Client client = RWT.getClient();
					if (client != null) {
						JavaScriptExecutor executor = RWT.getClient()
								.getService(JavaScriptExecutor.class);
                        executor.execute("window.open('', '_self', ''); window.close()");
					}
				}
			});
		}
		return Status.OK_STATUS;
	}
}

