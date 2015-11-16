package org.eclipse.rcptt.tesla.nattable.processors;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.tesla.core.context.ContextManagement.Context;
import org.eclipse.rcptt.tesla.core.info.AdvancedInformation;
import org.eclipse.rcptt.tesla.core.info.Q7WaitInfoRoot;
import org.eclipse.rcptt.tesla.core.protocol.ActivateCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.ApplyCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.BooleanResponse;
import org.eclipse.rcptt.tesla.core.protocol.CancelCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.DeactivateCellEditor;
import org.eclipse.rcptt.tesla.core.protocol.IElementProcessorMapper;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;
import org.eclipse.rcptt.tesla.core.protocol.SelectCommand;
import org.eclipse.rcptt.tesla.core.protocol.SelectResponse;
import org.eclipse.rcptt.tesla.core.protocol.SetSelection;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;
import org.eclipse.rcptt.tesla.core.protocol.raw.ResponseStatus;
import org.eclipse.rcptt.tesla.internal.core.AbstractTeslaClient;
import org.eclipse.rcptt.tesla.internal.core.processing.ElementGenerator;
import org.eclipse.rcptt.tesla.internal.core.processing.ITeslaCommandProcessor;
import org.eclipse.rcptt.tesla.internal.ui.SWTElementMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTModelMapperExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTEvents;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTModelMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.processors.SWTUIProcessor;
import org.eclipse.rcptt.tesla.jface.TeslaCellEditorManager;
import org.eclipse.rcptt.tesla.jobs.JobsManager;
import org.eclipse.rcptt.tesla.nattable.NatTableMapper;
import org.eclipse.rcptt.tesla.nattable.NatTablePlayerExtension;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NebulaNatTable;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.rcptt.tesla.swt.dialogs.SWTDialogManager;
import org.eclipse.rcptt.tesla.swt.events.TeslaEventManager;
import org.eclipse.rcptt.tesla.swt.events.TeslaTimerExecManager;
import org.eclipse.swt.widgets.Widget;

import com.google.common.collect.ImmutableSet;


public class NatTableProcessor implements ITeslaCommandProcessor, ISWTModelMapperExtension {

	private AbstractTeslaClient client;
	private final NatTablePlayerExtension extension = new NatTablePlayerExtension();
	private String id;

	private static final Set<String> SUPPORTED_SELECTORS = ImmutableSet.of(
			NebulaNatTableElementKinds.NAT_TABLE,
			NebulaNatTableElementKinds.NAT_TABLE_CELL);

	private static final Set<EClass> SUPPORTED_COMMANDS = ImmutableSet.of(
			NattablePackage.Literals.NAT_TABLE_MOUSE_EVENT,
			ProtocolPackage.Literals.CLICK,
			ProtocolPackage.Literals.SELECT_COMMAND);

	@Override
	public void initialize(AbstractTeslaClient client, String id) {
		this.client = client;
		this.id = id;
		SWTUIPlayer.addExtension(extension);
	}

	@Override
	public String getFeatureID() {
		return "org.eclipse.rcptt.tesla.nattable";
	}

	@Override
	public boolean isSelectorSupported(String kind) {
		return SUPPORTED_SELECTORS.contains(kind);
	}

	@Override
	public SelectResponse select(SelectCommand cmd, ElementGenerator generator, IElementProcessorMapper mapper) {
		String kind = cmd.getData().getKind();
		if(kind.equals(NebulaNatTableElementKinds.NAT_TABLE)) {
			return getSWTProcessor().select(cmd, generator, mapper);
		}
		if (kind.equals(NebulaNatTableElementKinds.NAT_TABLE_CELL)) {
			return NatTableSelectionProcessor.selectItem(cmd, getMapper(), id);
		}

		return createFailedSelect("Incorrect selection request.");
	}

	@Override
	public boolean isCommandSupported(Command command) {
		return SUPPORTED_COMMANDS.contains(command.eClass());
	}

	@Override
	public Response executeCommand(Command command, IElementProcessorMapper mapper) {
		EClass eClass = command.eClass();
		EPackage pkg = eClass.getEPackage();
		if (pkg.equals(ProtocolPackage.eINSTANCE)) {
			switch (eClass.getClassifierID()) {
			case ProtocolPackage.SET_SELECTION:
				SetSelection select = (SetSelection) command;
				if (select.getElement().getKind().equals(NebulaNatTableElementKinds.NAT_TABLE)) {
					return NatTableSelectionProcessor.executeCellSelection(select, getMapper(), id, getPlayer());
				}

				break;
			case ProtocolPackage.ACTIVATE_CELL_EDITOR:
				ActivateCellEditor activateCell = (ActivateCellEditor) command;
				String kind = activateCell.getElement().getKind();
				if (NebulaNatTableElementKinds.NAT_TABLE.equals(kind)
						|| NebulaNatTableElementKinds.NAT_TABLE_CELL.equals(kind)) {
					return NatTableCellEditProcessor.executeActivateCell(activateCell, mapper, id, getPlayer());
				}
				break;
			case ProtocolPackage.APPLY_CELL_EDITOR:
				ApplyCellEditor aplyCellEditor = (ApplyCellEditor) command;
				if (aplyCellEditor.getElement().getKind().equals(NebulaNatTableElementKinds.NAT_TABLE)) {
					return NatTableCellEditProcessor.executeApplyCellEditor(aplyCellEditor, mapper, id);
				}
				break;
			case ProtocolPackage.CANCEL_CELL_EDITOR:
				CancelCellEditor cancelCellEditor = (CancelCellEditor) command;
				if (cancelCellEditor.getElement().getKind().equals(NebulaNatTableElementKinds.NAT_TABLE)) {
					return NatTableCellEditProcessor.executeCancelCellEditor(cancelCellEditor, mapper, id);
				}
				break;
			case ProtocolPackage.DEACTIVATE_CELL_EDITOR:
				DeactivateCellEditor deactivateCellEditor = (DeactivateCellEditor) command;
				if (deactivateCellEditor.getElement().getKind().equals(NebulaNatTableElementKinds.NAT_TABLE)) {
					return NatTableCellEditProcessor.executeDeactivateCellEditor(deactivateCellEditor, mapper, id);
				}
				break;
			}
		} else if (pkg.equals(NattablePackage.eINSTANCE)) {
			if (command instanceof NatTableMouseEvent) {
				BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
				try {
					NatTableMouseEvent event = (NatTableMouseEvent) command;
					NatTable natTable = (NatTable) SWTElementMapper.getMapper(id).get(event.getElement()).widget;
					SWTUIPlayer player = getPlayer();
					SWTEvents events = player.getEvents();
					player.exec("mouse event on NatTable", new NatTableMouseEventRunner(natTable, events, event));
					response.setResult(true);
				} catch (Exception e) {
					response.setResult(false);
					response.setStatus(ResponseStatus.FAILED);
					response.setMessage(NLS.bind(TeslaSWTMessages.SWTUIProcessor_CannotSetSelection, e.getMessage()));
				}
				return response;
			}
		}

		return null;
	}

	@Override
	public PreExecuteStatus preExecute(Command command, PreExecuteStatus previousStatus, Q7WaitInfoRoot info) {
		return null;
	}

	@Override
	public void postSelect(Element element, IElementProcessorMapper mapper) {
	}

	@Override
	public boolean isInactivityRequired() {
		return false;
	}

	@Override
	public boolean canProceed(Context context, Q7WaitInfoRoot info) {
		return true;
	}

	@Override
	public void clean() {
		getMapper().clear();
		SWTModelMapper.initializeExtensions(client
				.getProcessors(ISWTModelMapperExtension.class));
		getPlayer().clean();
		TeslaCellEditorManager.getInstance().clean();
		SWTDialogManager.clear();
		JobsManager.getInstance().clean();
		TeslaEventManager.getManager().setNoWaitForJobs(false);
		TeslaTimerExecManager.getManager().clearTimers();
	}

	@Override
	public void terminate() {
		SWTUIPlayer.removeExtension(extension);
		client = null;
	}

	@Override
	public void checkHang() {
	}

	@Override
	public void collectInformation(AdvancedInformation information, Command lastCommand) {
	}

	@Override
	public void notifyUI() {
	}

	private SWTUIProcessor getSWTProcessor() {
		return client.getProcessor(SWTUIProcessor.class);
	}

	private SWTUIPlayer getPlayer() {
		return getSWTProcessor().getPlayer();
	}

	public NatTableMapper getMapper() {
		return NatTableMapper.getMapper(getFeatureID());
	}
	
	public static org.eclipse.rcptt.tesla.core.ui.Widget mapWidget(
			SWTUIElement element, org.eclipse.rcptt.tesla.core.ui.Widget result) {
		Widget widget = element.unwrap();
		if (widget instanceof NatTable) {
			NatTable table = (NatTable) widget;
			NebulaNatTable natTable = NattableFactory.eINSTANCE.createNebulaNatTable();
			SWTModelMapper.fillControl(natTable, table);
		}

		return result;
	}

	@Override
	public org.eclipse.rcptt.tesla.core.ui.Widget mapExtraValues(SWTUIElement element,
			org.eclipse.rcptt.tesla.core.ui.Widget result) {
		return mapWidget(element, result);
	}
	
	private SelectResponse createFailedSelect(String msg) {
		SelectResponse response = ProtocolFactory.eINSTANCE
				.createSelectResponse();
		response.setStatus(ResponseStatus.FAILED);
		response.setMessage(msg);
		return response;
	}
}
