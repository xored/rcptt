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
package org.eclipse.rcptt.tesla.recording.nattable.ecl;

import java.util.List;

import org.eclipse.rcptt.tesla.ecl.model.GetCell;
import org.eclipse.rcptt.tesla.ecl.model.GetColumnHeader;
import org.eclipse.rcptt.tesla.ecl.model.Selector;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.tesla.recording.core.ecl.ISelectorParserExtension;

/**
 * A parser extension for the {@code get-nat-table} command.
 */
public class NatTableSelectorParserExtension implements ISelectorParserExtension {

	@Override
	public Selector selector(String customKindId, String text, List<String> path, List<Integer> indexes) {
		if (NebulaNatTableElementKinds.NAT_TABLE.equals(customKindId)) {
			return NattableFactory.eINSTANCE.createGetNatTable();
		} else if (NebulaNatTableElementKinds.NAT_TABLE_CELL.equals(customKindId)) {
			NatTableCellPosition position = NatTableCellPosition.fromPath(path.get(0));
			if (position.getCol() == -1) {
				GetColumnHeader selector = TeslaFactory.eINSTANCE.createGetColumnHeader();
				selector.setText(position.getText());
				return selector;
			} else if (position.getRow() == -1) {
				GetRowHeader selector = NattableFactory.eINSTANCE.createGetRowHeader();
				selector.setText(position.getText());
				return selector;
			} else {
				GetCell getCell = TeslaFactory.eINSTANCE.createGetCell();
				getCell.setRow(position.getRow());
				getCell.setColumn(position.getCol());
				return getCell;
			}
		}
		return null;
	}

	@Override
	public boolean isParamNameForced(String commandName, String paramName) {
		return false;
	}

}
