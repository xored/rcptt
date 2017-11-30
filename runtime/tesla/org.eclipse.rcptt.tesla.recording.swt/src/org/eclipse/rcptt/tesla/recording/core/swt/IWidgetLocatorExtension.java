/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.recording.core.swt;

import org.eclipse.rcptt.tesla.core.protocol.WindowUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.FindResult;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

public interface IWidgetLocatorExtension {

	/**
	 * Extends widget locator findElement functionality.
	 * <p>
	 * CAUTION: Now (1 Aug 2013) the extensions are used only from the
	 * findMenuItem method.
	 * 
	 * @see SWTWidgetLocator#findElement(SWTUIElement, boolean, boolean,
	 *      boolean)
	 * @see SWTWidgetLocator#findElement(Widget, boolean, boolean, boolean)
	 */
	FindResult findElement(SWTUIElement widget, boolean unknownAllowed,
			boolean alwaysFindLeaf, boolean supportEclipseWorkbench);

	/**
	 * In case when some custom widget should be handled as some default widget, this method can be used.
	 * <p>
	 * See {@link WidgetClassifier} class for details.
	 */
	IWidgetClassifierExtension getWidgetClassifierExtension();

	/**
	 * Checks if processed control should be skipped
	 * Is called from SWTWidgetLocator#findElement
	 * 
	 * @param supportEclipseWorkbench
	 * @param control
	 * @param shell
	 * @return
	 */
	boolean isSkippedControl(boolean supportEclipseWorkbench, Control control, Shell shell);

	boolean isAfterSkippedForWidget(Widget widget, SWTUIElement lowerParent);

	boolean isMenuSourceFiltered(Widget widget, Menu upperMenu);

	Object findMenuSource(Menu menu);

	WindowUIElement getShell(Shell shell, boolean alwaysFindLeaf);

}
