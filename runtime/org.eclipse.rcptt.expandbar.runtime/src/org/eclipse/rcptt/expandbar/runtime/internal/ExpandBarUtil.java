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
package org.eclipse.rcptt.expandbar.runtime.internal;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Widget;

import org.eclipse.rcptt.util.ReflectionUtil;

public class ExpandBarUtil {
	public static boolean isRelatedWidget(Widget widget) {
		return widget instanceof ExpandBar || widget instanceof ExpandItem;
	}

	public static Rectangle getItemHeaderBounds(ExpandItem item) {
		int x = (Integer) ReflectionUtil.getField(item, "x");
		int y = (Integer) ReflectionUtil.getField(item, "y");
		int width = (Integer) ReflectionUtil.getField(item, "width");
		int height = item.getHeaderHeight();
		return new Rectangle(x, y, width, height);
	}

}
