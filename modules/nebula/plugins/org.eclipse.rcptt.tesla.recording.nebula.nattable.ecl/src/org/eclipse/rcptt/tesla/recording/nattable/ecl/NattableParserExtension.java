package org.eclipse.rcptt.tesla.recording.nattable.ecl;

import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.tesla.ecl.TeslaScriptletFactory;
import org.eclipse.rcptt.tesla.ecl.model.ControlHandler;
import org.eclipse.rcptt.tesla.ecl.model.GetCell;
import org.eclipse.rcptt.tesla.ecl.model.GetColumnHeader;
import org.eclipse.rcptt.tesla.ecl.model.Mouse;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.recording.core.ecl.TeslaCommand;
import org.eclipse.rcptt.tesla.recording.core.ecl.parser.TeslaParser;
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.rcptt.util.swt.KeysAndButtons;

/**
 * Parser extension for NatTable-specific Tesla protocol commands.
 */
public class NattableParserExtension extends TeslaScriptletFactory {

	@TeslaCommand(packageUri = NattablePackage.eNS_URI, classifier = "NatTableMouseEvent")
	public static Command natTableMouseEvent(TeslaParser parser, NatTableMouseEvent ntmEvent) {
		return makePipe(parser.selectorOf(ntmEvent.getElement()),
				createGetCommand(ntmEvent),
				createMouseCommand(ntmEvent));
	}

	private static Command createMouseCommand(NatTableMouseEvent ntmEvent) {
		if (ntmEvent.getKind() == NatTableMouseEventKind.CLICK
				&& ntmEvent.getButton() == Events.DEFAULT_BUTTON
				&& ntmEvent.getStateMask() == Events.EMPTY_MASK) {
			return TeslaFactory.eINSTANCE.createClick();
		} else {
			Mouse command = TeslaFactory.eINSTANCE.createMouse();
			command.setButton(KeysAndButtons.getButtonNameSafe(ntmEvent.getButton()));
			command.setEvent(ntmEvent.getKind().getLiteral().toLowerCase());
			command.setWith(KeysAndButtons.stateMaskToStr(ntmEvent.getStateMask()));
			return command;
		}
	}

	private static Command createGetCommand(NatTableMouseEvent event) {
		ControlHandler controlHandler = TeslaFactory.eINSTANCE.createControlHandler();
		controlHandler.setCustomKindId(NebulaNatTableElementKinds.NAT_TABLE);
		if (event.isColumnHeader()) {
			GetColumnHeader command = TeslaFactory.eINSTANCE.createGetColumnHeader();
			command.setIndex(event.getColumn());
			command.setParent(controlHandler);
			return command;
		} else if (event.isRowHeader()) {
			GetRowHeader command = NattableFactory.eINSTANCE.createGetRowHeader();
			command.setIndex(event.getRow());
			command.setParent(controlHandler);
			return command;
		} else {
			GetCell command = TeslaFactory.eINSTANCE.createGetCell();
			command.setColumn(event.getColumn());
			command.setRow(event.getRow());
			command.setParent(controlHandler);
			return command;
		}
	}

}
