/*******************************************************************************
 * Copyright (c) 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.nattable.ecl.impl.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.dispatch.IScriptletExtension;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.ecl.impl.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.GetCell;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;

public class GetCellServiceExtension implements IScriptletExtension {

	@Override
	public IStatus service(Command command, IProcess context) throws InterruptedException, CoreException {
		TeslaBridge.waitDelay();
		GetCell gcCommand = (GetCell) command;
		ControlHandler handler = TeslaFactory.eINSTANCE.createControlHandler();
		handler.setParent(gcCommand.getParent());
		handler.setAfter(gcCommand.getAfter());
		handler.setKind(ElementKind.Custom);
		handler.setCustomKindId(NebulaNatTableElementKinds.NAT_TABLE_CELL);
		handler.setPath(gcCommand.getColumn() + ":" + gcCommand.getRow());
		TeslaBridge.find(handler);
		TeslaBridge.waitExecution();
		context.getOutput().write(handler);
		return Status.OK_STATUS;
	}

	@Override
	public boolean canHandle(Command command) {
		return command instanceof GetCell &&
				NebulaNatTableElementKinds.NAT_TABLE.equals(((GetCell) command).getParent().getCustomKindId());
	}

}
