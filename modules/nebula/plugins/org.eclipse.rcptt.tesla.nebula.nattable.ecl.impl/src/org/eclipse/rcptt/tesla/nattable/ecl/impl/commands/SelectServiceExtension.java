package org.eclipse.rcptt.tesla.nattable.ecl.impl.commands;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.dispatch.IScriptletExtension;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.Select;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nattable.ecl.impl.NatTablePlugin;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;

public class SelectServiceExtension extends BaseActionService implements IScriptletExtension {

	@Override
	public boolean canHandle(Command command) {
		if (command instanceof Select) {
			ControlHandler control = ((Select) command).getControl();
			return NebulaNatTableElementKinds.NAT_TABLE.equals(control.getCustomKindId());
		}

		return false;
	}

	@Override
	protected Object exec(Command command) throws CoreException {
		return handleSelect((Select) command);
	}

	private Object handleSelect(Select command) throws CoreException {
		String path = (String) command.getItems().get(0);
		if (path == null || path.isEmpty()) {
			throw new CoreException(new Status(IStatus.ERROR, NatTablePlugin.PLUGIN_ID,
					"Indexes for columns aren't specified for NatTable select command"));
		}

		if (!NatTableCellPosition.isValidPath(path)) {
			throw new CoreException(new Status(IStatus.ERROR, NatTablePlugin.PLUGIN_ID,
					"NatTable select command should have following path format: (indexColumn:indexRow)"));
		}

		ControlHandler control = command.getControl();
		control.setPath(path);
		getNatTableUIElement(control).setSelection(path);

		return control;
	}

}
