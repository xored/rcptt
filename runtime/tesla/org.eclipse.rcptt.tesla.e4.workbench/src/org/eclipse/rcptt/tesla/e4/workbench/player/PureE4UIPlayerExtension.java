/*******************************************************************************
 * Copyright (c) 2009, 2018 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.e4.workbench.player;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.internal.ui.player.AbstractSWTUIPlayerExtension;

public class PureE4UIPlayerExtension extends AbstractSWTUIPlayerExtension {

	@Override
	public GenericElementKind getKind(Object w) {
		if (w instanceof MPart) {
			return new GenericElementKind(ElementKind.Part);
		}
		return null;
	}

}
