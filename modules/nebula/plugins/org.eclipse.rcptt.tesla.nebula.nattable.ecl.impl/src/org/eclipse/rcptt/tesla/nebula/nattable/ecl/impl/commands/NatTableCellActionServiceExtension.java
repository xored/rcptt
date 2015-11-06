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
import org.eclipse.rcptt.tesla.ecl.model.CellEdit;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.TeslaPackage;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.impl.NatTablePlugin;

import com.google.common.collect.Sets;

public class NatTableCellActionServiceExtension extends NatTableBaseActionService implements IScriptletExtension {

	private static final Set<String> HANDLED_CUSTOM_KIND_IDS = Sets.newHashSet(NebulaNatTableElementKinds.NAT_TABLE,
			NebulaNatTableElementKinds.NAT_TABLE_CELL);

	@Override
	protected Object exec(Command command) throws CoreException {
		ControlHandler control = ((CellEdit) command).getControl();
		ViewerUIElement element = getNatTableUIElement(control);
		switch (command.eClass().getClassifierID()) {
		case TeslaPackage.ACTIVATE_CELL_EDIT:
			element.activateCellEditor(control.getPath());
			break;
		case TeslaPackage.APPLY_CELL_EDIT:
			element.applyCellEditor();
			break;
		case TeslaPackage.CANCEL_CELL_EDIT:
			element.cancelCellEditor();
			break;
		case TeslaPackage.DEACTIVATE_CELL_EDIT:
			element.deactivateCellEditor();
			break;
		default:
			throw new CoreException(NatTablePlugin.err(this.getClass().getName() + " unknown edit command"));
		}
		
		return control;
	}

	@Override
	public boolean canHandle(Command command) {
		return command instanceof CellEdit &&
				HANDLED_CUSTOM_KIND_IDS.contains(((CellEdit) command).getControl().getCustomKindId());
	}

}
