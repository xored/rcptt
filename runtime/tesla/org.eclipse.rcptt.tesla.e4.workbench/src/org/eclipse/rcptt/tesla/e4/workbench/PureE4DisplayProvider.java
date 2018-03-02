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
package org.eclipse.rcptt.tesla.e4.workbench;

import org.eclipse.rcptt.runtime.e4.ui.PureE4Helper;
import org.eclipse.rcptt.runtime.e4.ui.PureE4ModelProcessor;
import org.eclipse.rcptt.tesla.swt.ITeslaDisplayProvider;
import org.eclipse.swt.widgets.Display;

public class PureE4DisplayProvider implements ITeslaDisplayProvider {

	@Override
	public boolean isSupported() {
		return PureE4Helper.isPureE4();
	}

	@Override
	public Display getDisplay() {
		return PureE4ModelProcessor.getDisplay();
	}

}
