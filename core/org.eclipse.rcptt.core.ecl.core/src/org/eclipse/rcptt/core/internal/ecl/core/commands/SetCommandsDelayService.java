/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.core.internal.ecl.core.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.core.Q7;
import org.eclipse.rcptt.core.ecl.core.model.SetCommandsDelay;
import org.eclipse.rcptt.tesla.core.TeslaFeatures;

public class SetCommandsDelayService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		SetCommandsDelay commandsDelay = (SetCommandsDelay) command;
		Q7.INSTANCE.setCommandsExecutionDelay(commandsDelay.getDelay());
		TeslaFeatures.getInstance()
				.getOption(TeslaFeatures.COMMAND_EXECUTION_DELAY)
				.setValue(Integer.toString(commandsDelay.getDelay()));
		return Status.OK_STATUS;
	}
}
