/*******************************************************************************
 * Copyright (c) 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.recording.nebula.nattable.ecl;

import java.util.List;

import org.eclipse.rcptt.tesla.ecl.model.Selector;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.NebulaNatTableElementKinds;
import org.eclipse.rcptt.tesla.nebula.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.recording.core.ecl.ISelectorParserExtension;

/**
 * A parser extension for the {@code get-nebula-nat-parser} command.
 */
public class NatTableSelectorParserExtension implements ISelectorParserExtension {

	@Override
	public Selector selector(String customKindId, String text, List<String> path, List<Integer> indexes) {
		if (NebulaNatTableElementKinds.NAT_TABLE.equals(customKindId)) {
			return NattableFactory.eINSTANCE.createGetNatTable();
		}
		return null;
	}

	@Override
	public boolean isParamNameForced(String commandName, String paramName) {
		return false;
	}

}
