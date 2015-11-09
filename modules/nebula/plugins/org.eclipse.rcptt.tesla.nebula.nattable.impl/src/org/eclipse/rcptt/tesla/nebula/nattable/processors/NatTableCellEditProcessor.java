package org.eclipse.rcptt.tesla.nebula.nattable.processors;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.edit.command.EditCellCommand;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
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
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.swt.SWT;

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
			switch (command.getKind()) {
			case CLICK:
				if (button == Events.DEFAULT_BUTTON && stateMask == Events.EMPTY_MASK) {
					NatTableHelper.clickOnCell(natTable, col, row, player);
				} else {
					NatTableHelper.mouseDownEventOnCell(natTable, col, row, button, player, stateMask);
					NatTableHelper.mouseUpEventOnCell(natTable, col, row, button, player, stateMask);
					selectCell(player, natTable, col, row, (stateMask & SWT.SHIFT) != 0, (stateMask & SWT.CTRL) != 0);
				}
				break;
			case DOWN:
				NatTableHelper.mouseDownEventOnCell(natTable, col, row, button, player, stateMask);
				break;
			case UP:
				NatTableHelper.mouseUpEventOnCell(natTable, col, row, button, player, stateMask);
				// Excplicitly send a SelectCellCommand because the MouseMove and MouseUp events don't seem to trigger
				// the desired selection on replay.
				// TODO: Find out why and fix this!
				selectCell(player, natTable, col, row, true, false);
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

	private static void selectCell(SWTUIPlayer player, final NatTable natTable, final int col, final int row,
			final boolean shiftMask, final boolean controlMask) {
		player.exec("Toggle selection of NatTable cell", new Runnable() {
			@Override
			public void run() {
				natTable.doCommand(new SelectCellCommand(natTable, col, row, shiftMask, controlMask));
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
