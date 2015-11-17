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

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.dispatch.IScriptletExtension;
import org.eclipse.rcptt.tesla.ecl.model.Click;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.TeslaPackage;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.util.swt.Events;

import com.google.common.collect.ImmutableSet;

public class ClickActionServiceExtension extends BaseActionService implements IScriptletExtension {

	private static final Set<String> HANDLED_CUSTOM_KIND_IDS = ImmutableSet.of(NebulaNatTableElementKinds.NAT_TABLE,
			NebulaNatTableElementKinds.NAT_TABLE_CELL);

	@Override
	protected Object exec(Command command) throws CoreException {
		assert (command.eClass().getClassifierID() == TeslaPackage.CLICK);
		ControlHandler control = ((Click) command).getControl();
		return MouseActionServiceExtension.execMouseEvent(NatTableMouseEventKind.CLICK, control,
				Events.DEFAULT_BUTTON, Events.EMPTY_MASK);
	}

	@Override
	public boolean canHandle(Command command) {
		return command instanceof Click &&
				HANDLED_CUSTOM_KIND_IDS.contains(((Click) command).getControl().getCustomKindId());
	}

}
