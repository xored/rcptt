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
package org.eclipse.rcptt.tesla.nattable.model;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;

import com.google.common.base.Objects;

public class NatTableCellElement extends NatTablePartElement {

	private final ILayerCell cell;
	private final NatTableCellPosition position;

	public NatTableCellElement(NatTableSWTElement natTable, NatTableCellPosition position) {
		super(natTable);
		this.position = position;
		this.cell = findCellObject(position);
	}

	@Override
	public String getGenerationKind() {
		return NebulaNatTableElementKinds.NAT_TABLE_CELL;
	}

	protected ILayerCell findCellObject(NatTableCellPosition position) {
		NatTable table = (NatTable) getNatTable().widget;
		return table.getCellByPosition(position.getCol(), position.getRow());
	}

	public ILayerCell getCell() {
		return cell;
	}

	public NatTableCellPosition getPosition() {
		return this.position;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(cell, natTable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NatTableCellElement other = (NatTableCellElement) obj;
		return Objects.equal(cell, other.cell) && Objects.equal(natTable, other.natTable);
	}

}
