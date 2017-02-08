/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.recording.aspects.swt.rap;

import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;

public interface IAssertSWTEventListener extends IAbstractSWTEventListener {

	public boolean handleEventInFreeze(Widget widget, int type, Event event);
	public void handleUpdateHover(Control control);

	public boolean highlightWidget(Widget widget);

	public boolean updateAssertionPanelWindow(Widget widget);

	public SWTUIElement getSWTUIElement(Element element);

	public Element getElement(SWTUIElement swtUIElement);

}
