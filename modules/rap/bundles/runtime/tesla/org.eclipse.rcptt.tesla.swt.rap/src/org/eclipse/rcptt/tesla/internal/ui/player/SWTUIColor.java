/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.internal.ui.player;

import org.eclipse.swt.graphics.Color;

import org.eclipse.rcptt.tesla.core.protocol.UIColor;

public class SWTUIColor extends UIColor {
	private Color cl;

	public SWTUIColor(int red, int blue, int green) {
		super(red, blue, green);
	}

	public SWTUIColor(Color cl) {
		this.cl = cl;
	}

	public Color getColor() {
		return cl;
	}
}