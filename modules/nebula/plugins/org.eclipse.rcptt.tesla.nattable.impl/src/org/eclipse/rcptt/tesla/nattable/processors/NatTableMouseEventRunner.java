package org.eclipse.rcptt.tesla.nattable.processors;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.command.ILayerCommand;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectColumnCommand;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectRowsCommand;
import org.eclipse.rcptt.tesla.core.protocol.BooleanResponse;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTEvents;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.util.swt.Bounds;
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;

/**
 * A {@link Runnable} for executing a mouse click on a {@link NatTable}. This should be passed to
 * {@link org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer#exec(String, Runnable)} to run on the UI thread.
 */
class NatTableMouseEventRunner implements Runnable {
	private NatTable natTable;
	private SWTEvents events;
	private NatTableMouseEvent event;

	NatTableMouseEventRunner(NatTable natTable, SWTEvents events, NatTableMouseEvent event) {
		this.natTable = natTable;
		this.events = events;
		this.event = event;
	}

	@Override
	public void run() {
		ILayerCell cell = NatTableCellPosition.fromMouseEvent(event).getCell(natTable);
		if (cell == null) {
			return; // TODO: Throw an exception??
		}
		executeMouseEvent(event, cell);
	}

	private void executeMouseEvent(NatTableMouseEvent command, ILayerCell cell) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		int button = command.getButton();
		int stateMask = command.getStateMask();
		boolean shiftMask = (stateMask & SWT.SHIFT) != 0;
		boolean ctrlMask = (stateMask & SWT.CTRL) != 0;
		switch (command.getKind()) {
		case CLICK:
			if (button == Events.DEFAULT_BUTTON && stateMask == Events.EMPTY_MASK) {
				Event[] event = Events.createClick(Bounds.centerAbs(cell.getBounds()));
				events.sendAll(natTable, event);
			} else {
				mouseDownEventOnCell(cell, button, stateMask);
				mouseUpEventOnCell(cell, button, stateMask);
				if (button == Events.DEFAULT_BUTTON) {
					selectCell(cell, shiftMask, ctrlMask);
				}
			}
			break;
		case DOWN:
			mouseDownEventOnCell(cell, button, stateMask);
			if (button == Events.DEFAULT_BUTTON) {
				selectCell(cell, shiftMask, ctrlMask);
			}
			break;
		case UP:
			mouseUpEventOnCell(cell, button, stateMask);
			// Explicitly send a SelectCellCommand because the MouseMove and MouseUp events don't seem to trigger
			// the desired selection on replay.
			// TODO: Find out why and fix this!
			if (button == Events.DEFAULT_BUTTON) {
				selectCell(cell, true, ctrlMask);
			}
			break;
		}
		response.setResult(true);
	}

	/**
	 * Selects the given cell by explicitly sending a {@link SelectCellCommand} to the {@link NatTable}. If the cell is
	 * a column header, a {@link SelectColumnCommand} is sent, specifying the first cell below the column header cell.
	 * Similarly for {@link SelectRowCommand}s.
	 */
	private void selectCell(ILayerCell cell, boolean shiftMask, boolean ctrlMask) {
		int col = cell.getColumnPosition();
		int row = cell.getRowPosition();
		ILayer layer = natTable.getLayer().getUnderlyingLayerByPosition(col, row);
		ILayerCommand command;
		if (layer instanceof ColumnHeaderLayer) {
			command = new SelectColumnCommand(natTable, col, layer.getRowCount(), shiftMask, ctrlMask);
		} else if (layer instanceof RowHeaderLayer) {
			command = new SelectRowsCommand(natTable, layer.getColumnCount(), row, shiftMask, ctrlMask);
		} else {
			command = new SelectCellCommand(natTable, col, row, shiftMask, ctrlMask);
		}
		natTable.doCommand(command);
	}

	/**
	 * Fire a mouse down event on the given cell
	 */
	private void mouseDownEventOnCell(ILayerCell cell, int button, int stateMask) {
		Point point = Bounds.centerAbs(cell.getBounds());
		Event event = Events.createMouseDown(button, 1, stateMask, point.x, point.y);
		events.sendEvent(natTable, event);
	}

	/**
	 * Fire a mouse up event on the given cell
	 */
	private void mouseUpEventOnCell(ILayerCell cell, int button, int stateMask) {
		Point point = Bounds.centerAbs(cell.getBounds());
		Event event = Events.createMouseUp(button, 1, stateMask, point.x, point.y);
		events.sendEvent(natTable, event);
	}

}
