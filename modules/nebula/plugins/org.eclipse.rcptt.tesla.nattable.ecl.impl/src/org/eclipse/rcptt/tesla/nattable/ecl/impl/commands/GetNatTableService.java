/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
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
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.ecl.impl.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.Selector;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetNatTable;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;

public class GetNatTableService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context) throws InterruptedException, CoreException {
		TeslaBridge.waitDelay();

		ControlHandler handler = TeslaFactory.eINSTANCE.createControlHandler();
		GetNatTable selector = (GetNatTable) command;
		handler.setParent(selector.getParent());
		handler.setAfter(selector.getAfter());

		handler.setKind(ElementKind.Custom);
		handler.setCustomKindId(customKindIdFrom(selector));
		TeslaBridge.find(handler);
		TeslaBridge.waitExecution();
		context.getOutput().write(handler);

		return Status.OK_STATUS;
	}

	private static String customKindIdFrom(Selector s) {
		if (s instanceof GetNatTable)
			return NebulaNatTableElementKinds.NAT_TABLE;

		throw new IllegalArgumentException(GetNatTableService.class.getName() + " unknown selector type");
	}

}
