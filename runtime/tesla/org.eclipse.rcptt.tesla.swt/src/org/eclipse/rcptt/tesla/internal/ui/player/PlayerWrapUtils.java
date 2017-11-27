/*******************************************************************************
 * Copyright (c) 2009, 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.internal.ui.player;

import org.eclipse.swt.widgets.Widget;

public class PlayerWrapUtils {

	public static Object unwrap(SWTUIElement element) {
		if (element == null) {
			return null;
		}
		return element.unwrap();
	}

	/**
	 * 
	 * @param widget
	 * @return <code>null</code> if input is <code>null</code>, corresponding
	 *         widget otherwise
	 */
	public static Widget unwrapWidget(SWTUIElement widget) {
		if (widget == null) {
			return null;
		}
		return widget.unwrapWidget();
	}
}
