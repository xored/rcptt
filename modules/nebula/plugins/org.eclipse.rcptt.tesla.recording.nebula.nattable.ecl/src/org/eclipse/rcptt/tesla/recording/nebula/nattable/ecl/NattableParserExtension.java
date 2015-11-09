package org.eclipse.rcptt.tesla.recording.nebula.nattable.ecl;

import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.ecl.TeslaScriptletFactory;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.GetCell;
import org.eclipse.rcptt.tesla.ecl.model.Mouse;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.recording.core.ecl.TeslaCommand;
import org.eclipse.rcptt.tesla.recording.core.ecl.parser.TeslaParser;
import org.eclipse.rcptt.util.swt.KeysAndButtons;

/**
 * Parser extension for NatTable-specific Tesla protocol commands.
 */
public class NattableParserExtension extends TeslaScriptletFactory {

	@TeslaCommand(packageUri = NattablePackage.eNS_URI, classifier = "NatTableMouseEvent")
	public static Command natTableMouseEvent(TeslaParser parser, NatTableMouseEvent ntmEvent) {
		GetCell gcCommand = createGetCellCommand(ntmEvent.getColumn(), ntmEvent.getRow(), ntmEvent.getElement());
		return makePipe(parser.selectorOf(ntmEvent.getElement()), gcCommand, getMouseCommand(ntmEvent));
	}

	private static Command getMouseCommand(NatTableMouseEvent ntmEvent) {
		if (ntmEvent.getKind() == NatTableMouseEventKind.CLICK && ntmEvent.getButton() == 1) {
			return TeslaFactory.eINSTANCE.createClick();
		} else {
			Mouse command = TeslaFactory.eINSTANCE.createMouse();
			command.setButton(KeysAndButtons.getButtonNameSafe(ntmEvent.getButton()));
			command.setEvent(ntmEvent.getKind().getLiteral().toLowerCase());
			return command;
		}
	}

	private static GetCell createGetCellCommand(int column, int row, Element parent) {
		GetCell command = TeslaFactory.eINSTANCE.createGetCell();
		command.setColumn(column);
		command.setRow(row);
		ControlHandler controlHandler = TeslaFactory.eINSTANCE.createControlHandler();
		controlHandler.setCustomKindId(NebulaNatTableElementKinds.NAT_TABLE);
		command.setParent(controlHandler);
		return command;
	}

}
