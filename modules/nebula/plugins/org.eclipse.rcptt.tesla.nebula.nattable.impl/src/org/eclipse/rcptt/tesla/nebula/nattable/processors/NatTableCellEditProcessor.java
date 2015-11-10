package org.eclipse.rcptt.tesla.nebula.nattable.processors;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.command.ILayerCommand;
import org.eclipse.nebula.widgets.nattable.edit.command.EditCellCommand;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectColumnCommand;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectRowsCommand;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.tesla.core.protocol.ActivateCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.ApplyCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.BooleanResponse;
import org.eclipse.rcptt.tesla.core.protocol.CancelCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.DeactivateCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.IElementProcessorMapper;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;
import org.eclipse.rcptt.tesla.core.protocol.raw.ResponseStatus;
import org.eclipse.rcptt.tesla.internal.ui.SWTElementMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.nebula.nattable.NatTableHelper;
import org.eclipse.rcptt.tesla.nebula.nattable.NatTableMapper;
import org.eclipse.rcptt.tesla.nebula.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.tesla.nebula.nattable.model.NatTableSWTElement;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.rcptt.util.swt.Bounds;
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;

class NatTableCellEditProcessor {

	static Response executeActivateCell(ActivateCellEditor command, IElementProcessorMapper mapper, String id,
			SWTUIPlayer player) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		try {
			NatTableSWTElement natTableElement = (NatTableSWTElement) SWTElementMapper.getMapper(id).get(
					command.getElement());
			final NatTable natTable = (NatTable) natTableElement.widget;
			final String path = command.getPath().get(0);
			NatTableCellPosition position = NatTableHelper.parsePath(path);
			position = NatTableHelper.getPositionByPathPosition(natTable, position);
			final ILayerCell cell = natTable.getCellByPosition(position.getCol(), position.getRow());

			player.exec("Start editing of NatTable cell", new Runnable() {
				@Override
				public void run() {
					natTable.doCommand(new EditCellCommand(natTable, natTable.getConfigRegistry(), cell));
					// TODO: Handle return value: false indicates that the command hasn't been handled.
				}
			});

			response.setResult(true);

		} catch (Exception e) {
			response.setResult(false);
			response.setStatus(ResponseStatus.FAILED);
			response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
		}
		return response;
	}

	static Response executeMouseEvent(NatTableMouseEvent command, NatTableMapper mapper, String id,
			final SWTUIPlayer player) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		try {
			NatTableSWTElement natTableElement = (NatTableSWTElement) SWTElementMapper.getMapper(id).get(
					command.getElement());
			NatTable natTable = (NatTable) natTableElement.widget;
			int col = command.getColumn();
			int row = command.getRow();
			int button = command.getButton();
			int stateMask = command.getStateMask();
			boolean shiftMask = (stateMask & SWT.SHIFT) != 0;
			boolean ctrlMask = (stateMask & SWT.CTRL) != 0;
			switch (command.getKind()) {
			case CLICK:
				if (button == Events.DEFAULT_BUTTON && stateMask == Events.EMPTY_MASK) {
					NatTableHelper.clickOnCell(natTable, col, row, player);
				} else {
					mouseDownEventOnCell(natTable, col, row, button, player, stateMask);
					mouseUpEventOnCell(natTable, col, row, button, player, stateMask);
					if (button == Events.DEFAULT_BUTTON) {
						selectCell(player, natTable, col, row, shiftMask, ctrlMask);
					}
				}
				break;
			case DOWN:
				mouseDownEventOnCell(natTable, col, row, button, player, stateMask);
				if (button == Events.DEFAULT_BUTTON) {
					selectCell(player, natTable, col, row, shiftMask, ctrlMask);
				}
				break;
			case UP:
				mouseUpEventOnCell(natTable, col, row, button, player, stateMask);
				// Explicitly send a SelectCellCommand because the MouseMove and MouseUp events don't seem to trigger
				// the desired selection on replay.
				// TODO: Find out why and fix this!
				if (button == Events.DEFAULT_BUTTON) {
					selectCell(player, natTable, col, row, true, ctrlMask);
				}
				break;
			}
			response.setResult(true);
		} catch (Exception e) {
			response.setResult(false);
			response.setStatus(ResponseStatus.FAILED);
			response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
		}
		return response;
	}

	/**
	 * Selects the given cell by explicitly sending a {@link SelectCellCommand} to the {@link NatTable}. If the cell is
	 * a column header, a {@link SelectColumnCommand} is sent, specifying the first cell below the column header cell.
	 * Similarly for {@link SelectRowCommand}s.
	 */
	private static void selectCell(SWTUIPlayer player, final NatTable natTable, final int col, final int row,
			final boolean shiftMask, final boolean ctrlMask) {
		player.exec("Toggle selection of NatTable cell", new Runnable() {
			@Override
			public void run() {
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
		});
	}

	/**
	 * Fire a mouse down event on the given cell
	 */
	public static void mouseDownEventOnCell(final NatTable natTable, int col, int row, int button,
			final SWTUIPlayer player, int stateMask) {
		ILayerCell cell = natTable.getCellByPosition(col, row);
		Point point = Bounds.centerAbs(cell.getBounds());
		final Event event = Events.createMouseDown(button, 1, stateMask, point.x, point.y);
		player.exec("Performing mouse down event on NatTable cell", new Runnable() {
			@Override
			public void run() {
				player.getEvents().sendEvent(natTable, event);
			}
		});
	}

	/**
	 * Fire a mouse up event on the given cell
	 */
	public static void mouseUpEventOnCell(final NatTable natTable, int col, int row, int button,
			final SWTUIPlayer player, int stateMask) {
		ILayerCell cell = natTable.getCellByPosition(col, row);
		Point point = Bounds.centerAbs(cell.getBounds());
		final Event event = Events.createMouseUp(button, 1, stateMask, point.x, point.y);
		player.exec("Performing mouse up event on NatTable cell", new Runnable() {
			@Override
			public void run() {
				player.getEvents().sendEvent(natTable, event);
			}
		});
	}

	static Response executeApplyCellEditor(ApplyCellEditor command, IElementProcessorMapper mapper,
			String id) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		try {
			NatTableSWTElement natTableElement = (NatTableSWTElement) SWTElementMapper.getMapper(id).get(
					command.getElement());
			NatTable natTable = (NatTable) natTableElement.widget;

			ICellEditor editor = natTable.getActiveCellEditor();
			if (editor != null && !editor.isClosed()) {
				editor.commit(MoveDirectionEnum.NONE);
				editor.close();
			}

			response.setResult(true);

		} catch (Exception e) {
			response.setResult(false);
			response.setStatus(ResponseStatus.FAILED);
			response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
		}
		return response;
	}

	static Response executeCancelCellEditor(CancelCellEditor command, IElementProcessorMapper mapper,
			String id) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		try {
			NatTableSWTElement natTableElement = (NatTableSWTElement) SWTElementMapper.getMapper(id).get(
					command.getElement());
			NatTable natTable = (NatTable) natTableElement.widget;

			ICellEditor editor = natTable.getActiveCellEditor();
			if (editor != null && !editor.isClosed()) {
				editor.close();
			}

			response.setResult(true);

		} catch (Exception e) {
			response.setResult(false);
			response.setStatus(ResponseStatus.FAILED);
			response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
		}
		return response;
	}

	static Response executeDeactivateCellEditor(DeactivateCellEditor command,
			IElementProcessorMapper mapper, String id) {
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		try {
			NatTableSWTElement natTableElement = (NatTableSWTElement) SWTElementMapper.getMapper(id).get(
					command.getElement());
			NatTable natTable = (NatTable) natTableElement.widget;

			ICellEditor editor = natTable.getActiveCellEditor();
			if (editor != null && !editor.isClosed()) {
				editor.commit(MoveDirectionEnum.NONE);
				editor.close();
			}

			response.setResult(true);

		} catch (Exception e) {
			response.setResult(false);
			response.setStatus(ResponseStatus.FAILED);
			response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
		}
		return response;
	}

}
