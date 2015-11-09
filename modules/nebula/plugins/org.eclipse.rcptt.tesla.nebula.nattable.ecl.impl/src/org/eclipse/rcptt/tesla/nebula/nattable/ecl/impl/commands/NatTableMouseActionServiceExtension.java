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
package org.eclipse.rcptt.tesla.nebula.nattable.ecl.impl.commands;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.dispatch.IScriptletExtension;
import org.eclipse.rcptt.tesla.core.protocol.ViewerUIElement;
import org.eclipse.rcptt.tesla.ecl.impl.TeslaBridge;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.Mouse;
import org.eclipse.rcptt.tesla.ecl.model.TeslaPackage;
import org.eclipse.rcptt.tesla.nebula.nattable.NatTableHelper;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nebula.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory;
import org.eclipse.rcptt.util.swt.KeysAndButtons;

import com.google.common.collect.ImmutableSet;

public class NatTableMouseActionServiceExtension extends NatTableBaseActionService implements IScriptletExtension {

	private static final Set<String> HANDLED_CUSTOM_KIND_IDS = ImmutableSet.of(NebulaNatTableElementKinds.NAT_TABLE,
			NebulaNatTableElementKinds.NAT_TABLE_CELL);

	@Override
	protected Object exec(Command command) throws CoreException {
		assert (command.eClass().getClassifierID() == TeslaPackage.MOUSE);
		Mouse mouseCommand = (Mouse) command;
		NatTableMouseEventKind kind = NatTableMouseEventKind.getByName(mouseCommand.getEvent().toUpperCase());
		int button = KeysAndButtons.getButtonNumber(mouseCommand.getButton());
		ControlHandler control = mouseCommand.getControl();
		return execMouseEvent(kind, control, button);
	}

	static ControlHandler execMouseEvent(NatTableMouseEventKind kind, ControlHandler controlHandler, int button)
			throws CoreException {
		NatTableMouseEvent mouseEvent = NattableFactory.eINSTANCE.createNatTableMouseEvent();
		ViewerUIElement uiElement = getNatTableUIElement(controlHandler);
		NatTableCellPosition position = NatTableHelper.parsePath(controlHandler.getPath());
		mouseEvent.setColumn(position.getCol());
		mouseEvent.setRow(position.getRow());
		mouseEvent.setKind(kind);
		mouseEvent.setElement(uiElement.getElement());
		mouseEvent.setButton(button);
		TeslaBridge.getPlayer().safeExecuteCommand(mouseEvent);
		return controlHandler;
	}

	@Override
	public boolean canHandle(Command command) {
		return command instanceof Mouse &&
				HANDLED_CUSTOM_KIND_IDS.contains(((Mouse) command).getControl().getCustomKindId());
	}

}
