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
package org.eclipse.rcptt.tesla.swt.e3x;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartPane;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.PartStack;
import org.eclipse.ui.internal.Perspective;
import org.eclipse.ui.internal.ViewPane;
import org.eclipse.ui.internal.ViewStackTrimToolBar;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchPartReference;
import org.eclipse.ui.internal.presentations.PaneFolder;
import org.eclipse.ui.internal.presentations.PaneFolderButtonListener;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolder;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.eclipse.rcptt.core.scenario.WorkbenchContext;
import org.eclipse.rcptt.ctx.workbench.impl.Activator;
import org.eclipse.rcptt.ctx.workbench.impl.ResourcesSupport;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.tesla.core.TeslaLimits;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.ecl.impl.UIRunnable;
import org.eclipse.rcptt.tesla.ecl.impl.Utils;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.player.TeslaSWTAccess;
import org.eclipse.rcptt.tesla.internal.ui.player.UIJobCollector;
import org.eclipse.rcptt.tesla.swt.workbench.EclipseWorkbenchProvider;
import org.eclipse.rcptt.tesla.swt.workbench.IEclipseWorkbenchProvider;

@SuppressWarnings("restriction")
public class ClassicEclipseWorkbenchProvider implements IEclipseWorkbenchProvider {

	public Menu getViewMenu(IWorkbenchPart workbenchPart, IWorkbenchPartReference reference, boolean create) {
		if (workbenchPart.getSite() == null) {
			return null;
		}

		PartPane pane = ((PartSite) workbenchPart.getSite()).getPane();
		if (!(pane instanceof ViewPane)) {
			return null;
		}

		MenuManager menuManager = ((ViewPane) pane).getMenuManager();
		if (menuManager == null) {
			return null;
		}

		Menu menu = menuManager.getMenu();
		if (menu != null && !menu.isDisposed()) {
			return menu;
		}

		if (!create) {
			return null;
		}

		menuManager.createContextMenu(pane.getControl().getParent());
		return menuManager.getMenu();
	}

	private List<?> getPaneFolderButtonListeners(Object paneFolder) {
		try {
			Field field = PaneFolder.class.getDeclaredField("buttonListeners");
			field.setAccessible(true);
			return (List<?>) field.get(paneFolder);
		} catch (Throwable e) {
			TeslaCore.log(e);
		}
		return null;
	}

	public Control getToolbar(IWorkbenchPartReference reference) {
		return ((WorkbenchPartReference) reference).getPane().getToolBar();
	}

	public boolean canClickView(IWorkbenchPartReference reference) {
		IWorkbenchPage page = reference.getPage();
		Perspective perspective = ((WorkbenchPage) page).getActivePerspective();
		IWorkbenchPart part = reference.getPart(true);
		if (part != null && part instanceof IViewPart) {
			if (!perspective.containsView((IViewPart) part)) {
				return false;
			}
		}
		return true;
	}

	public Map<Control, SWTUIElement> getWorkbenchReference(SWTUIPlayer player) {
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		if (!Display.getCurrent().equals(PlatformUI.getWorkbench().getDisplay())) {
			return new HashMap<Control, SWTUIElement>();
		}
		Map<Control, SWTUIElement> references = new HashMap<Control, SWTUIElement>();
		for (IWorkbenchWindow iWorkbenchWindow : windows) {
			// This is workbench window
			IWorkbenchPage page = iWorkbenchWindow.getActivePage();
			IEditorReference[] editors = page.getEditorReferences();
			IViewReference[] views = page.getViewReferences();
			for (IViewReference iViewReference : views) {
				WorkbenchPartReference ref = ((WorkbenchPartReference) iViewReference);
				if (ref.getVisible()) {
					Control control = ref.getPane().getControl();
					references.put(control, player.wrap(ref));
					Control toolBar = ref.getPane().getToolBar();
					if (toolBar != null) {
						references.put(toolBar, player.wrap(ref));
					}
				}
			}
			for (IEditorReference iEditorReference : editors) {
				WorkbenchPartReference ref = ((WorkbenchPartReference) iEditorReference);
				if (ref.getVisible()) {
					Control control = ref.getPane().getControl();
					references.put(control, player.wrap(ref));
					Control toolBar = ref.getPane().getToolBar();
					if (toolBar != null) {
						references.put(toolBar, player.wrap(ref));
					}
				}
			}
		}
		return references;
	}

	public void processTabFolderButton(Widget widget, int buttonId) {
		if (!(widget instanceof CTabFolder)) {
			if (widget instanceof Control) {
				Composite parent = ((Control) widget).getParent();
				if (parent == null)
					return;
				for (Control c : parent.getChildren())
					if (c instanceof CTabFolder && !c.isDisposed()) {
						Object data = ((CTabFolder) c).getData();
						if (data instanceof PartStack) {
							PartStack stack = (PartStack) data;
							PartPane selection = stack.getSelection();
							if (selection != null && selection.getControl() == widget)
								widget = c;
						}
					}
			}
		}

		if (!(widget instanceof CTabFolder))
			return;

		// --

		PaneFolder paneFolder = TeslaSWTAccess.getThis(PaneFolder.class, widget, SWT.Dispose);
		if (paneFolder != null) {
			List<?> listenters = getPaneFolderButtonListeners(paneFolder);
			for (Object listener : listenters) {
				if (listener instanceof PaneFolderButtonListener) {
					((PaneFolderButtonListener) listener).stateButtonPressed(buttonId);
				}
			}
		}
	}

	public void processTabShowList(Widget widget) {
		if (!(widget instanceof CTabFolder)) {
			if (widget instanceof Control) {
				Composite parent = ((Control) widget).getParent();
				if (parent == null)
					return;
				for (Control c : parent.getChildren())
					if (c instanceof CTabFolder && !c.isDisposed()) {
						Object data = ((CTabFolder) c).getData();
						if (data instanceof PartStack) {
							PartStack stack = (PartStack) data;
							PartPane selection = stack.getSelection();
							if (selection != null && selection.getControl() == widget)
								widget = c;
						}
					}
			}
		}

		if (!(widget instanceof CTabFolder))
			return;

		// --

		PaneFolder paneFolder = TeslaSWTAccess.getThis(PaneFolder.class, widget, SWT.Dispose);
		if (paneFolder != null) {
			List<?> listenters = getPaneFolderButtonListeners(paneFolder);
			for (Object listener : listenters) {
				if (listener instanceof PaneFolderButtonListener) {
					CTabFolderEvent event = TeslaSWTAccess.createCTabFolderEvent(widget);
					((PaneFolderButtonListener) listener).showList(event);
				}
			}
		}
	}

	public boolean isVisible(IWorkbenchPartReference reference) {
		return reference instanceof WorkbenchPartReference && ((WorkbenchPartReference) reference).getVisible();
	}

	public boolean isActiveContainsView(IWorkbenchPage page, IWorkbenchPartReference reference) {
		Perspective perspective = ((WorkbenchPage) page).getActivePerspective();
		IWorkbenchPart part = reference.getPart(true);
		if (part != null && part instanceof IViewPart) {
			if (!perspective.containsView((IViewPart) part)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSupported() {
		Version version = TeslaCore.getPlatformVersion();
		int major = version.getMajor();
		int minor = version.getMinor();
		return major == 3 && minor > 4 && minor < 9;
	}

	private static final List<String> viewTooltips = Arrays.asList("View Menu", "Maximize", "Minimize", "Restore");

	public boolean isViewOrEditorButton(Widget widget) {
		if (!(widget instanceof ToolItem))
			return false;

		ToolItem item = (ToolItem) widget;
		if (!viewTooltips.contains(item.getToolTipText())) {
			return false;
		}

		// view menu
		if (TeslaSWTAccess.getThis(DefaultTabFolder.class, item.getParent(), SWT.MouseDown) != null)
			return true;

		// restore button
		if (TeslaSWTAccess.getThis(ViewStackTrimToolBar.class, item.getData()) != null)
			return true;

		return false;
	}

	public Widget extractViewOrEditorControl(CTabFolder tabFolder) {
		Object data = tabFolder.getData();
		if (data instanceof PartStack) {
			PartStack stack = (PartStack) data;
			PartPane selection = stack.getSelection();
			if (selection != null)
				return selection.getControl();
		}

		return null;
	}

	public CTabFolder getTabFolderFromButton(ToolItem button) {
		// TODO Restore button support
		return null;
	}

	public Text getQuickAccess() {
		// no quick access on e3
		return null;
	}

	public void updateActiveSelection(List<Object> selectionData, SWTUIElement parent) {
	}

	public String getViewId(Widget widget) {
		if (widget instanceof Composite)
			for (Listener l : widget.getListeners(SWT.Activate))
				if (l instanceof ViewPane)
					return ((ViewPane) l).getID();

		return null;
	}

	@Override
	public GenericElementKind getWidgetKind(Widget w) {
		// not supported for now
		return null;
	}

	@Override
	public String getWidgetRawText(Widget widget) {
		// not supported for now
		return null;
	}

	@Override
	public void activatePart(SWTUIElement element) {
		// not supported for now
	}

	@Override
	public Widget selectPart(PlayerSelectionFilter f) {
		// not supported for now
		return null;
	}

	@Override
	public IWorkbenchWindow[] getWorkbenchWindows() {
		return PlatformUI.getWorkbench().getWorkbenchWindows();
	}

	@Override
	public Display getDisplay() {
		return PlatformUI.getWorkbench().getDisplay();
	}

	@Override
	public int getWorkbenchWindowCount() {
		return PlatformUI.getWorkbench().getWorkbenchWindowCount();
	}

	@Override
	public Shell getActiveShell() {
		// Focus on AUT
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
			if (windows.length > 0) {
				window = windows[0];
			} else {
				return null;
			}
		}
		return window.getShell();
	}

	@Override
	public void applyContext(org.eclipse.rcptt.core.scenario.Context context) throws CoreException {
	
		final WorkbenchContext ctx = (WorkbenchContext) context;

		
		final UIJobCollector collector = new UIJobCollector();
		Job.getJobManager().addJobChangeListener(collector);
		try {
			Display display = PlatformUI.getWorkbench().getDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					collector.enable();
				}
			});
			if (ctx.isNoModalDialogs()) {
				display.asyncExec(closeModalDialogsAsync);
				display.asyncExec(closeModalDialogsAsync);
				IStatus status = UIRunnable.exec(closeModalDialogs);
				if (!status.isOK())
					throw new CoreException(status);
			}
			UIRunnable.exec(closeIntro);
			final IWorkbenchPage page = UIRunnable.exec(activatePerspective(ctx));
			if (ctx.isCloseEditors()) {
				if (page != null) {
					UIRunnable.exec(closeAllEditors(page));
				}
			}
			if (ctx.isClearClipboard()) {
				UIRunnable.exec(clearClipboard);
			}
			String perspectiveId = getPerspectiveId(ctx);
			if (page != null && perspectiveId != null && perspectiveId.length() > 0 && ctx.isResetPerspective()) {

				List<IPerspectiveDescriptor> descriptors = Arrays.asList(page.getSortedPerspectives());

				// Close all perspectives

				// Wait until some jobs to finish, before trying to close
				// perspective
				// collector.addAllJobs(10 * 1000);
				for (final IPerspectiveDescriptor desc : descriptors) {
					setPageInput(page, getDefaultPageInput());
					UIRunnable.exec(closePerspective(page, desc));
				}

				UIRunnable.exec(cleanOtherPerspectives(page));
			}

			UIRunnable.exec(setPerspective(ctx, page));

			openParts(ctx);

			updateSelection(ctx);
			UIRunnable.exec(new UIRunnable<Object>() {
				@Override
				public Object run() throws CoreException {
					collector.setNeedDisable();
					return null;
				}
			});
			collector.join(TeslaLimits.getContextJoinTimeout());
		} catch (Exception e) {
			CoreException ee = new CoreException(RcpttPlugin
					.createStatus("Failed to execute context: " + ctx.getName() + " Cause: " + e.getMessage(), e));
			RcpttPlugin.log(e);
			throw ee;
		} finally {
			Job.getJobManager().removeJobChangeListener(collector);
		}

	}
	
	private UIRunnable<IStatus> closeModalDialogs = new UIRunnable<IStatus>() {
		@Override
		public IStatus run() throws CoreException {
			try {
				return Utils.closeDialogs();
			} catch (Throwable e) {
				return RcpttPlugin.createStatus(e);
			}
		}
	};
	private Runnable closeModalDialogsAsync = new Runnable() {
		public void run() {
			Utils.closeDialogs();
		}
	};
	private UIRunnable<Object> closeIntro = new UIRunnable<Object>() {
		@Override
		public Object run() throws CoreException {
			// e4 support
			if (TeslaCore.isE4()) {
				return null;
			}
			
			IIntroManager manager = PlatformUI.getWorkbench().getIntroManager();
			if (manager != null) {
				IIntroPart intro = manager.getIntro();
				if (intro != null) {
					manager.closeIntro(intro);
				}
			}
			return null;
		}
	};
	private UIRunnable<Object> clearClipboard = new UIRunnable<Object>() {
		@Override
		public Object run() throws CoreException {
			Display display = EclipseWorkbenchProvider.getProvider().getDisplay();
			Clipboard clipboard = new Clipboard(display);
			// First put something into clipboard, to force our
			// clipboard became owner of system clipboard
			clipboard.setContents(new Object[] { " " }, new Transfer[] { TextTransfer.getInstance() });

			// clipboard.clearContents(DND.CLIPBOARD);
			// clipboard.clearContents(DND.SELECTION_CLIPBOARD);
			clipboard.clearContents();
			return null;
		}
	};
	
	private UIRunnable<Object> setSelection(final ISelectionProvider provider) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				provider.setSelection(new StructuredSelection());
				return null;
			}
		};
	}

	private UIRunnable<Object> setPerspective(final WorkbenchContext ctx, final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				showPerspective(ctx);
				if (page != null && page.getSortedPerspectives().length == 0) {
					String defaultPerspectiveId = PlatformUI.getWorkbench().getPerspectiveRegistry()
							.getDefaultPerspective();
					IPerspectiveDescriptor perspectiveDesc = PlatformUI.getWorkbench().getPerspectiveRegistry()
							.findPerspectiveWithId(defaultPerspectiveId);
					page.setPerspective(perspectiveDesc);
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> cleanOtherPerspectives(final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				// Clean other perspectives
				for (final IPerspectiveDescriptor persp : page.getWorkbenchWindow().getWorkbench()
						.getPerspectiveRegistry().getPerspectives()) {
					// if (descriptors.contains(persp)) {
					Bundle bundle = Platform.getBundle("org.eclipse.osgi");
					bundle.getVersion();

					IPreferenceStore store = org.eclipse.ui.internal.WorkbenchPlugin.getDefault().getPreferenceStore();

					store.setToDefault(persp.getId() + "_persp");
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> closePerspective(final IWorkbenchPage page, final IPerspectiveDescriptor desc) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				try {
					page.closePerspective(desc, false, false);
				} catch (Throwable e) {
					RcpttPlugin.log(e);
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> closeAllEditors(final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				page.closeAllEditors(false);
				return null;
			}
		};
	}

	private UIRunnable<IWorkbenchPage> activatePerspective(final WorkbenchContext ctx) {
		return new UIRunnable<IWorkbenchPage>() {
			@Override
			public IWorkbenchPage run() throws CoreException {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage lpage = window.getActivePage();
				if (lpage == null) {
					lpage = showPerspective(ctx);
				}
				return lpage;
			}
		};
	}

	private Object getDefaultPageInput() {
		try {
			return ResourcesSupport.getWorkspaceRoot();
		} catch (Throwable e) {
		}
		return null;
	}

	protected void setPageInput(IWorkbenchPage page, Object value) {
		try {
			Field field = org.eclipse.ui.internal.WorkbenchPage.class.getDeclaredField("input");
			field.setAccessible(true);
			field.set(page, value);
		} catch (SecurityException e) {
			Activator.log(e);
		} catch (NoSuchFieldException e) {
			Activator.log(e);
		} catch (IllegalArgumentException e) {
			Activator.log(e);
		} catch (IllegalAccessException e) {
			Activator.log(e);
		}
	}
	
	private String getPerspectiveId(org.eclipse.rcptt.core.scenario.Context context) {
		WorkbenchContext pContext = (WorkbenchContext) context;
		return pContext.getPerspectiveId();
	}
	
	private void openParts(WorkbenchContext context) throws CoreException {
		final IWorkbench workbench = PlatformUI.getWorkbench();

		final IWorkbenchPage page = UIRunnable.exec(new UIRunnable<IWorkbenchPage>() {
			@Override
			public IWorkbenchPage run() throws CoreException {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window == null) {
					window = workbench.getWorkbenchWindows()[0];
				}
				IWorkbenchPage activePage = window.getActivePage();
				if (activePage == null) {
					IWorkbenchPage[] pages = window.getPages();
					if (pages.length > 0) {
						activePage = pages[0];
					}
				}
				return activePage;
			}
		});
		if (page != null) {
			// show views
			for (final String viewId : context.getViews()) {
				IViewReference[] references = page.getViewReferences();
				boolean found = false;
				for (IViewReference iViewReference : references) {
					if (viewId.equals(iViewReference.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					UIRunnable.exec(new UIRunnable<Object>() {
						@Override
						public Object run() throws CoreException {
							page.showView(viewId);
							return null;
						}
					});
				}
			}
			// close opened editors
			if (context.isCloseEditors()) {
				UIRunnable.exec(new UIRunnable<Object>() {
					@Override
					public Object run() throws CoreException {
						page.closeAllEditors(false);
						return null;
					}
				});
			}
			// open editors
			try {
				ResourcesSupport.openEditors(page, context);
			} catch (CoreException e) {
				throw e;
			} catch (Throwable e) {

			}
		}
	}
	
	private void updateSelection(WorkbenchContext ctx) {
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		for (IWorkbenchWindow win : windows) {
			IWorkbenchPage[] pages = win.getPages();
			for (IWorkbenchPage page0 : pages) {
				IViewReference[] references = page0.getViewReferences();
				for (IViewReference ref : references) {
					if (!ctx.getViews().contains(ref.getId())) {
						continue;
					}
					IWorkbenchPart part = ref.getPart(false);
					if (part != null) {
						final ISelectionProvider provider = part.getSite().getSelectionProvider();
						if (provider != null) {
							try {
								UIRunnable.exec(setSelection(provider));
							} catch (Throwable e) {
								RcpttPlugin.log(e);
							}
						}
					}
				}
			}
		}
	}
	
	private IWorkbenchPage showPerspective(WorkbenchContext context) throws CoreException {
		// open perspective
		IWorkbench workbench = PlatformUI.getWorkbench();
		String perspective = getPerspectiveId(context);
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (perspective != null && perspective.length() > 0) {
			return workbench.showPerspective(perspective, window);
		}
		if (window == null) {
			window = workbench.getWorkbenchWindows()[0];
		}
		IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null) {
			IWorkbenchPage[] pages = window.getPages();
			for (IWorkbenchPage page : pages) {
				activePage = page;
			}
		}
		return activePage;
	}
}
