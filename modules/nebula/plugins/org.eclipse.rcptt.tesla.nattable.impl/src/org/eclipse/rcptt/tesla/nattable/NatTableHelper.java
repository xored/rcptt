package org.eclipse.rcptt.tesla.nattable;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;

public class NatTableHelper {

	/**
	 * Check if cell is editable
	 */
	public static boolean isEditable(NatTable natTable, NatTableCellPosition position) {
		ILayerCell cell = position.getCell(natTable);
		IConfigRegistry configRegistry = natTable.getConfigRegistry();
		IEditableRule rule = configRegistry.getConfigAttribute(
				EditConfigAttributes.CELL_EDITABLE_RULE, DisplayMode.EDIT, cell.getConfigLabels()
						.getLabels());

		if (rule == null) {
			return false;
		}

		return rule.isEditable(cell, configRegistry);
	}
	
	/**
	 * Get cell position by coordinates
	 */
	public static NatTableCellPosition getCellPosition(NatTable natTable, int x, int y) {
		int colPosition = natTable.getColumnPositionByX(x);
		int rowPosition = natTable.getRowPositionByY(y);
		if (colPosition == -1 || rowPosition == -1)
			return null;

		return new NatTableCellPosition(colPosition, rowPosition);
	}

	public static boolean isNatTableCell(NatTable natTable, int x, int y) {
		return getCellPosition(natTable, x, y) != null;
	}

	/**
	 * Returns whether the position points to a column header cell.
	 */
	public static boolean isColumnHeader(NatTable natTable, NatTableCellPosition position) {
		return getUnderlyingLayer(natTable, position) instanceof ColumnHeaderLayer;
	}

	/**
	 * Returns whether the position points to a row header cell.
	 */
	public static boolean isRowHeader(NatTable natTable, NatTableCellPosition position) {
		return getUnderlyingLayer(natTable, position) instanceof RowHeaderLayer;
	}

	private static ILayer getUnderlyingLayer(NatTable natTable, NatTableCellPosition position) {
		ILayerCell cell = position.getCell(natTable);
		int col = cell.getColumnPosition();
		int row = cell.getRowPosition();
		return natTable.getLayer().getUnderlyingLayerByPosition(col, row);
	}

}
