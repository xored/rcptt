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
package org.eclipse.rcptt.tesla.recording.workbench;

import java.util.Map;

import org.eclipse.rcptt.tesla.core.context.ContextManagement;
import org.eclipse.rcptt.tesla.core.context.ContextManagement.Context;
import org.eclipse.rcptt.tesla.core.protocol.CompositeUIElement;
import org.eclipse.rcptt.tesla.core.protocol.ControlUIElement;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.PartUIElement;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.SetStatusDialogMode;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.SetMode;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.FindResult;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.TeslaSWTAccess;
import org.eclipse.rcptt.tesla.recording.aspects.ActionType;
import org.eclipse.rcptt.tesla.recording.aspects.IExtendedSWTEventListener;
import org.eclipse.rcptt.tesla.recording.aspects.IWorkbenchEventListener;
import org.eclipse.rcptt.tesla.recording.aspects.SWTEventManager;
import org.eclipse.rcptt.tesla.recording.aspects.WorkbenchEventManager;
import org.eclipse.rcptt.tesla.recording.core.IRecordingHelper;
import org.eclipse.rcptt.tesla.recording.core.IRecordingProcessor;
import org.eclipse.rcptt.tesla.recording.core.TeslaRecorder;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTEventRecorder;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTRecordingHelper;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTWidgetLocator;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusAdapter;

public class WorkbenchRecordingProcessor
		implements IRecordingProcessor, IWorkbenchEventListener, IExtendedSWTEventListener {

	private final class PartListener implements IPartListener {

		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
			if (getRecorder() == null) {
				return;
			}
			if (!getRecorder().hasListeners()) {
				return;
			}
			Context context = ContextManagement.currentContext();
			// TODO: This is Eclipse version dependent test
			if (!context.contains("org.eclipse.swt.custom.CTabFolder", "onMouse")) {
				return;
			}
			if (!TeslaCore.isEclipse4()) {
				// Skip for editors, will be done from different place.
				if (part instanceof IEditorPart) {
					return;
				}
			}
			Display display = PlatformUI.getWorkbench().getDisplay();
			Shell[] shells = display.getShells();
			for (Shell shell : shells) {
				if (isModal(shell)) {
					return;
				}
			}
			PartUIElement resultPart = getLocator().findPartElement(part, false);
			if (resultPart != null) {
				resultPart.close();
			}
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partActivated(IWorkbenchPart part) {
			if (getRecorder() == null) {
				return;
			}
			if (!getRecorder().hasListeners()) {
				return;
			}
			boolean hashMouseUp = false;
			Context context = ContextManagement.currentContext();

			if (context.contains("org.eclipse.swt.custom.CTabFolder", "onMouse")) {
				hashMouseUp = true;
			}
			if (!hashMouseUp) {
				StackTraceElement[] stack = context.getStackTrace();
				for (StackTraceElement e : stack) {
					if (e.getMethodName().equals("mouseUp") || e.getMethodName().equals("handleEvent")
							|| e.getMethodName().equals("activate")) {
						String className = e.getClassName();
						int dollarPos = className.indexOf('$');
						if (dollarPos > 0)
							className = className.substring(0, dollarPos);
						if ("org.eclipse.e4.ui.workbench.renderers.swt.StackRenderer".equals(className)) {
							hashMouseUp = true;
							break;
						}
					}
				}
			}
			if (!hashMouseUp) {
				return;// Skip, because not used click
			}

			if (context.contains("org.eclipse.ui.internal.WorkbenchPage", "hideView"))

			{
				// View was activated programmatically, so ignore.
				return;
			}
			Display display = PlatformUI.getWorkbench().getDisplay();
			Shell[] shells = display.getShells();
			for (Shell shell : shells) {
				if (isModal(shell)) {
					return;
				}
			}

			PartUIElement resultPart = getLocator().findPartElement(part, false);
			if (resultPart != null) {
				resultPart.click();
			}
		}

	}

	private TeslaRecorder recorder;
	private SWTEventRecorder swtRecorder;
	private WorkbenchWidgetLocator locator;

	private IPartListener listener;
	private IPageListener pageListener;
	private IWindowListener windowListener;

	public WorkbenchRecordingProcessor() {
	}

	public void closeEditors(IEditorReference[] refArray) {
		Display display = PlatformUI.getWorkbench().getDisplay();
		Shell[] shells = display.getShells();
		for (Shell shell : shells) {
			if (isModal(shell)) {
				return;
			}
		}
		if (!getSWTRecorder().getRecorder().hasListeners()) {
			return;
		}
		for (IEditorReference ref : refArray) {
			PartUIElement resultPart = getLocator().findPartElement(ref.getPart(false), false);
			if (resultPart != null) {
				resultPart.close();
			}
		}
	}

	@Override
	public boolean isExclusiveEventHandle(Widget widget, int type, Event event) {
		return false;
	}

	@Override
	// TODO (e4 support): is it enough?
	public void recordEvent(Widget widget, int type, Event event) {
		if (type == SWT.Selection || type == SWT.DefaultSelection) {
			processSelection(widget, event, type);
		} else if (type == SWT.MouseDown) {
			processMouseDown(widget, event);
		}
	}

	private void processSelection(Widget widget, Event event, int type) {
		if (EclipseWorkbenchProvider.getProvider().isViewOrEditorButton(widget)) {
			ToolItem button = (ToolItem) widget;
			String tip = button.getToolTipText();
			CTabFolder ctabFolder = EclipseWorkbenchProvider.getProvider().getTabFolderFromButton(button);
			if (ctabFolder != null && tip != null) {
				if (tip.equals("Maximize")) {
					recordTabFolderEvent(ctabFolder, SWTEventManager.EVENT_TAB_MAXIMIZE);
				} else if (tip.equals("Minimize")) {
					recordTabFolderEvent(ctabFolder, SWTEventManager.EVENT_TAB_MINIMIZE);
				} else if (tip.equals("Restore")) {
					recordTabFolderEvent(ctabFolder, SWTEventManager.EVENT_TAB_RESTORE);
				} else if (tip.equals("View Menu")) {
					// do nothing
				} else if (tip.equals("Show List")) {
					recordTabFolderEvent(ctabFolder, SWTEventManager.EVENT_TAB_SHOW_LIST);
				}
				return;
			}
		}

		if ((widget instanceof MenuItem) && SWTWidgetLocator.isCTabFolderListMenuItem((MenuItem) widget)) {
			CTabFolder miTabFolder = SWTWidgetLocator.getCTabFolder((MenuItem) widget);
			FindResult result = getSWTRecorder().getLocator().findElement(miTabFolder, true, false, false);
			getSWTRecorder().processCTabFolderItemSelection(miTabFolder, result, ((MenuItem) widget).getText());
			return;
		}
	}

	private void processMouseDown(Widget widget, Event event) {
		if ((widget instanceof TabFolder || widget instanceof CTabFolder)) {
			// Check for workbench internal element click
			if (!EclipseWorkbenchProvider.getProvider().isInternalWorkbenchElement(widget)) {
				FindResult result = getSWTRecorder().getLocator().findElement(widget, true, false, false);
				if (result != null && result.realElement.getKind().is(ElementKind.TabFolder)) {
					String tabName = null;
					boolean closeAction = false;
					if (widget instanceof TabFolder) {
						TabItem[] items = ((TabFolder) widget).getItems();
						for (TabItem tabItem : items) {
							Rectangle bounds = tabItem.getBounds();
							if (bounds.contains(event.x, event.y)) {
								// Set selection on selected widget.
								tabName = PlayerTextUtils.removeAcceleratorFromText(tabItem.getText());
								break;
							}
						}

					} else if (widget instanceof CTabFolder) {
						CTabItem[] items = ((CTabFolder) widget).getItems();
						for (CTabItem tabItem : items) {
							Rectangle bounds = tabItem.getBounds();
							if (bounds.contains(event.x, event.y)) {
								// Check for tab item close rect and record
								// close command for tab item.
								// Set selection on selected widget.
								tabName = PlayerTextUtils.removeAcceleratorFromText(tabItem.getText());
								Rectangle rect = TeslaSWTAccess.getCTabItemCloseRect(tabItem);
								if (rect != null) {
									if (rect.contains(event.x, event.y)) {
										closeAction = true;
									}
								}
								break;
							}
						}
					}
					if (tabName != null) {
						if (!closeAction) {
							if (widget instanceof CTabFolder) {
								getSWTRecorder().processCTabFolderItemSelection((CTabFolder) widget, result, tabName);
							}
						} else {
							CompositeUIElement v = new CompositeUIElement(result.element, getRecorder());
							ControlUIElement tabItem = v.tabItem(tabName);
							tabItem.close();
						}
					}
				}
			}
		}
	}

	@Override
	public void recordTabFolderEvent(Control tabControl, int eventId) {
		FindResult result = null;

		if (tabControl instanceof CTabFolder) {
			CTabFolder tabFolder = (CTabFolder) tabControl;
			Widget w = EclipseWorkbenchProvider.getProvider().extractViewOrEditorControl(tabFolder);
			if (w != null) {
				Map<Control, Object> references = EclipseWorkbenchProvider.getProvider()
						.getWorkbenchReference();
				SWTUIElement element = getSWTRecorder().getPlayer().wrap(references.get(w));
				result = getSWTRecorder().getLocator().findElement(element, false, false, true);
			}
		}

		if (result == null)
			result = getSWTRecorder().getLocator().findElement(tabControl, false, false, true);

		if (result != null) {
			ControlUIElement ctrl = new ControlUIElement(result.element, getRecorder());
			switch (eventId) {
			case SWTEventManager.EVENT_TAB_MINIMIZE:
				ctrl.minimize();
				break;
			case SWTEventManager.EVENT_TAB_MAXIMIZE:
				ctrl.maximize();
				break;
			case SWTEventManager.EVENT_TAB_RESTORE:
				ctrl.restore();
				break;
			case SWTEventManager.EVENT_TAB_SHOW_LIST:
				ctrl.showTabList();
				break;
			}
		}
	}

	@Override
	public void recordDragEvent(Event event) {
	}

	@Override
	public void recordSWTDialog(Dialog dialog, Object result) {
	}

	@Override
	public void recordStyledTextOffset(StyledText text) {
	}

	@Override
	public void recordStyledTextActionBefore(StyledText text, int action) {
	}

	@Override
	public void recordStyledTextActionAfter(StyledText text, int action) {
	}

	@Override
	public void setCurrentEvent(Event event) {
	}

	@Override
	public void removeClosedShell(SWTUIElement wrappedShell) {
	}

	public void restartEclipse() {
		getSWTRecorder().getRecorder().safeExecuteCommand(
				ProtocolFactory.eINSTANCE.createWaitForRestart());
	}

	public void initialize(TeslaRecorder teslaRecorder) {
		this.recorder = teslaRecorder;
		locator = new WorkbenchWidgetLocator(recorder, getSWTRecorder().getLocator(),
				SWTRecordingHelper.getHelper().getPlayer());
		getSWTRecorder().getLocator().addExtension(locator);

		listener = new PartListener();
		pageListener = new IPageListener() {

			public void pageOpened(IWorkbenchPage page) {
				page.addPartListener(listener);
			}

			public void pageClosed(IWorkbenchPage page) {
			}

			public void pageActivated(IWorkbenchPage page) {
				page.addPartListener(listener);
			}
		};
		windowListener = new IWindowListener() {

			public void windowOpened(IWorkbenchWindow window) {
				window.addPageListener(pageListener);
				IWorkbenchPage[] pages = window.getPages();
				for (IWorkbenchPage page : pages) {
					page.addPartListener(listener);
				}
			}

			public void windowDeactivated(IWorkbenchWindow window) {
			}

			public void windowClosed(IWorkbenchWindow window) {
			}

			public void windowActivated(IWorkbenchWindow window) {
				window.addPageListener(pageListener);
			}
		};
		IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.addWindowListener(windowListener);
		IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		for (IWorkbenchWindow win : windows) {
			win.addPageListener(pageListener);
			IWorkbenchPage[] pages = win.getPages();
			for (IWorkbenchPage page : pages) {
				page.addPartListener(listener);
			}
		}

		SWTEventManager.addListener(this);
		WorkbenchEventManager.addListener(this);
		PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				return true;
			}

			public void postShutdown(IWorkbench workbench) {
				restartEclipse();
			}
		});
	}

	public int getInitLevel() {
		return 1000;
	};

	public void clear() {
	}

	public void setFreeze(boolean value, SetMode command) {
	}

	public void recordAction(ActionType type) {
		if (recorder == null) {
			return;
		}
		if (!recorder.hasListeners()) {
			return;
		}
		recorder.safeExecuteCommand(commandByAction(type));
	}

	private Command commandByAction(ActionType type) {
		switch (type) {
		case ABOUT_DIALOG:
			return ProtocolFactory.eINSTANCE.createClickAboutMenu();
		case PREFERENCE_DIALOG:
			return ProtocolFactory.eINSTANCE.createClickPreferencesMenu();
		}
		throw new IllegalArgumentException("Couldn't find command for action: "
				+ type);
	}

	public void recordAddStatus(StatusAdapter adapter, boolean modal) {
		if (recorder == null) {
			return;
		}
		if (!recorder.hasListeners()) {
			return;
		}
		SetStatusDialogMode mode = ProtocolFactory.eINSTANCE
				.createSetStatusDialogMode();
		mode.setEnabled(true);
		recorder.executeCommand(mode);
	}

	public void recordStatusCleanup() {
		if (recorder == null) {
			return;
		}
		if (!recorder.hasListeners()) {
			return;
		}
		SetStatusDialogMode mode = ProtocolFactory.eINSTANCE
				.createSetStatusDialogMode();
		mode.setEnabled(false);
		recorder.executeCommand(mode);
	}

	private boolean isModal(Shell shell) {
		int style = shell.getStyle();
		int mask = SWT.SYSTEM_MODAL | SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL | SWT.MODELESS;
		String text = shell.getText();
		if (text != null && (text.trim().equalsIgnoreCase("problem occurred")
				|| text.trim().equalsIgnoreCase("Operation failed"))) {
			return true;
		}
		return (style & mask) > 0;
	}

	public void resetAssertSelection() {
	}

	public IRecordingHelper<?> getHelper() {
		return null;
	}

	public WorkbenchWidgetLocator getLocator() {
		return locator;
	}

	private TeslaRecorder getRecorder() {
		return recorder;
	}

	private SWTEventRecorder getSWTRecorder() {
		if (swtRecorder == null) {
			swtRecorder = recorder.getProcessor(SWTEventRecorder.class);
		}
		return swtRecorder;
	}

}
