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
package org.eclipse.rcptt.ecl.internal.debug.runtime;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.debug.commands.CommandsFactory;
import org.eclipse.rcptt.ecl.debug.commands.DebugCommand;
import org.eclipse.rcptt.ecl.debug.commands.DebugScript;
import org.eclipse.rcptt.ecl.parser.EclCoreParser;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class DebugScriptService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		DebugScript s = (DebugScript) command;

		String content = s.getContent();
		if (content == null || content.length() == 0)
			return Status.OK_STATUS;
		Command c = EclCoreParser.newCommand(content);
		DebugCommand dc = CommandsFactory.eINSTANCE.createDebugCommand();
		dc.setCommand(c);
		dc.setPath(s.getPath());
		dc.setSession(s.getSession());

		IProcess process = context.getSession().execute(c);
		return process.waitFor();
	}

}
