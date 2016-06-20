package org.eclipse.rcptt.tesla.ecl.internal.impl.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.ecl.impl.rap.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.internal.impl.TeslaImplPlugin;
import org.eclipse.rcptt.tesla.ecl.rap.model.RunJs;
import org.eclipse.rcptt.tesla.ui.RWTUtils;
import org.eclipse.rcptt.util.StringUtils;

public class RunJsService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context) throws InterruptedException, CoreException {

		final RunJs js = (RunJs)command;

		if(StringUtils.isEmpty(js.getJsCode())) {
			return TeslaImplPlugin.err("Js Code is empty."); //$NON-NLS-1$
		}

		RWTUtils.findDisplay().asyncExec(new Runnable() {
			public void run() {
				RWT.getClient().getService(JavaScriptExecutor.class).execute(js.getJsCode());
			}
		});

		TeslaBridge.waitExecution();
		return Status.OK_STATUS;
	}

}
