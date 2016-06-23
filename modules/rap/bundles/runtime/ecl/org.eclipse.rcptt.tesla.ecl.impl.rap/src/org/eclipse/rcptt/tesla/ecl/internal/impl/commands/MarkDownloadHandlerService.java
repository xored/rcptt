package org.eclipse.rcptt.tesla.ecl.internal.impl.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.ecl.impl.rap.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.internal.impl.TeslaImplPlugin;
import org.eclipse.rcptt.tesla.ecl.rap.model.MarkDownloadHandler;
import org.eclipse.rcptt.tesla.swt.download.RapDownloadHandlerManager;

public class MarkDownloadHandlerService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context) throws InterruptedException, CoreException {
		final MarkDownloadHandler handlers = (MarkDownloadHandler) command;

		if (handlers.getHandler().isEmpty()) {
			return TeslaImplPlugin.err("The handlers list is empty"); //$NON-NLS-1$
		}

		for (String id : handlers.getHandler()) {
			RapDownloadHandlerManager.addHandler(id);
		}

		TeslaBridge.waitExecution();
		return Status.OK_STATUS;
	}

}