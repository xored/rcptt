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
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ViewerUIElement;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.ecl.impl.AbstractActionService;
import org.eclipse.rcptt.tesla.ecl.impl.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;

public abstract class BaseActionService extends AbstractActionService {

	@Override
	protected IStatus handleWithExtensions(Command command, IProcess context)
			throws InterruptedException, CoreException {
		return null;
	}

	protected static ViewerUIElement getNatTableUIElement(ControlHandler control) throws CoreException {
		ViewerUIElement element = new ViewerUIElement(findNatTableElement(control), TeslaBridge.getPlayer());
		TeslaBridge.storeLastControlUIElement(element);
		return element;
	}

	private static Element findNatTableElement(ControlHandler control) throws CoreException {
		if (NebulaNatTableElementKinds.NAT_TABLE_CELL.equals(control.getCustomKindId())) {
			control = control.getParent();
		}
		return TeslaBridge.find(control);
	}
}