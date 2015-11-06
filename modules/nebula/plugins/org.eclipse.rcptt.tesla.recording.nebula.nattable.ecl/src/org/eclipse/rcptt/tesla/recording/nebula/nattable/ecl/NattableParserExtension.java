package org.eclipse.rcptt.tesla.recording.nebula.nattable.ecl;

import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.tesla.ecl.TeslaScriptletFactory;
import org.eclipse.rcptt.tesla.ecl.model.GetCell;
import org.eclipse.rcptt.tesla.ecl.model.TeslaFactory;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable;
import org.eclipse.rcptt.tesla.recording.core.ecl.TeslaCommand;
import org.eclipse.rcptt.tesla.recording.core.ecl.parser.TeslaParser;

public class NattableParserExtension extends TeslaScriptletFactory {

	@TeslaCommand(packageUri = NattablePackage.eNS_URI, classifier = "SetSelectionNatTable")
	public static Command setSelectionNatTable(TeslaParser parser, SetSelectionNatTable setSelection) {
		GetCell command = TeslaFactory.eINSTANCE.createGetCell();
		command.setColumn(setSelection.getColumn());
		command.setRow(setSelection.getRow());
		return command;
	}

}
