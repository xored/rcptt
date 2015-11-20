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
package org.eclipse.rcptt.tesla.swt.workbench;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;

public interface IEclipseWorkbenchProvider {

	Menu getViewMenu(IWorkbenchPart workbenchPart,
			IWorkbenchPartReference reference, boolean create);

	List<?> getPaneFolderButtonListeners(Object paneFolder);

	Control getToolbar(IWorkbenchPartReference reference);

	boolean canClickView(IWorkbenchPartReference reference);

	Map<Control, SWTUIElement> getWorkbenchReference(SWTUIPlayer player);

	void processTabFolderButton(Widget widget, int buttonId);

	void processTabShowList(Widget widget);

	boolean isVisible(IWorkbenchPartReference reference);

	boolean isActiveContainsView(IWorkbenchPage page,
			IWorkbenchPartReference reference);

	boolean isSupported();

	boolean isViewOrEditorButton(Widget widget);

	Widget extractViewOrEditorControl(CTabFolder tabFolder);

	CTabFolder getTabFolderFromButton(ToolItem button);

	Text getQuickAccess();

	void updateActiveSelection(List<Object> selectionData, SWTUIElement parent);

	String getViewId(Widget widget);

	GenericElementKind getWidgetKind(Widget widget);

	String getWidgetRawText(Widget widget);

	void activatePart(SWTUIElement element);

	Widget selectPart(PlayerSelectionFilter f);

	IWorkbenchWindow[] getWorkbenchWindows();

	Display getDisplay();

	int getWorkbenchWindowCount();

	Shell getActiveShell();

	void applyContext(Context context) throws CoreException;

}
