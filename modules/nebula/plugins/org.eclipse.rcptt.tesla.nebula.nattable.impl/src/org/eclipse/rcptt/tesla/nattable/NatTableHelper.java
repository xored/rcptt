package org.eclipse.rcptt.tesla.nattable;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.util.swt.Bounds;
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.swt.widgets.Event;

public class NatTableHelper {

	/**
	 * Check if cell is editable
	 */
	public static boolean isEditable(NatTable natTable, NatTableCellPosition position) {
		ILayerCell cell = natTable.getCellByPosition(position.getCol(), position.getRow());
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
	 * 
	 * @param natTable
	 * @param col
	 *            position coordinate
	 * @param row
	 *            position coordinate
	 * @return
	 */
	public static boolean isHeaderLayer(NatTable natTable, int col, int row) {
		ILayer layer = natTable.getLayer();
		ILayer layerByPosition = layer.getUnderlyingLayerByPosition(col, row);
		if (layerByPosition instanceof ColumnHeaderLayer) {
			return true;
		}

		if (layer instanceof GridLayer) {
			ILayer bodyLayer = ((GridLayer) layer).getBodyLayer();
			return !bodyLayer.equals(layerByPosition);
		}

		return false;
	}

	/**
	 * Fire single left click event on cell
	 */
	public static void clickOnCell(final NatTable natTable, int col, int row, final SWTUIPlayer player) {
		ILayerCell cell = natTable.getCellByPosition(col, row);
		final Event[] event = Events.createClick(Bounds.centerAbs(cell.getBounds()));
		player.exec("Performing click on NatTable cell", new Runnable() {
			@Override
			public void run() {
				player.getEvents().sendAll(natTable, event);
			}
		});
	}

}
