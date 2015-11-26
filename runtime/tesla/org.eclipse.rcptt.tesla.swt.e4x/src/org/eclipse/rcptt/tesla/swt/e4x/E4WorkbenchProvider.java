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
package org.eclipse.rcptt.tesla.swt.e4x;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.impl.AreaImpl;
import org.eclipse.e4.ui.model.application.ui.advanced.impl.PlaceholderImpl;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.impl.PartImpl;
import org.eclipse.e4.ui.model.application.ui.basic.impl.PartStackImpl;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.workbench.addons.minmax.TrimStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.rcptt.core.scenario.WorkbenchContext;
import org.eclipse.rcptt.ctx.workbench.impl.Activator;
import org.eclipse.rcptt.ctx.workbench.impl.ResourcesSupport;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.tesla.core.TeslaLimits;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.ecl.impl.UIRunnable;
import org.eclipse.rcptt.tesla.ecl.impl.Utils;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.player.TeslaSWTAccess;
import org.eclipse.rcptt.tesla.internal.ui.player.UIJobCollector;
import org.eclipse.rcptt.tesla.internal.ui.player.WorkbenchUIElement;
import org.eclipse.rcptt.tesla.swt.workbench.EclipseWorkbenchProvider;
import org.eclipse.rcptt.tesla.swt.workbench.IEclipseWorkbenchProvider;
import org.eclipse.rcptt.util.ReflectionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.ViewSite;
import org.eclipse.ui.internal.WorkbenchPartReference;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.e4.compatibility.ActionBars;
import org.eclipse.ui.internal.e4.compatibility.SelectionService;
import org.eclipse.ui.internal.quickaccess.SearchField;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

public class E4WorkbenchProvider implements IEclipseWorkbenchProvider {

	private static <T> T as(Class<T> class_, Object object) {
		if (class_.isInstance(object))
			return class_.cast(object);
		return null;
	}

	@Override
	public Menu getViewMenu(IWorkbenchPart workbenchPart, IWorkbenchPartReference reference, boolean create) {

		ViewSite site = as(ViewSite.class, workbenchPart.getSite());
		if (site == null)
			return null;

		ActionBars bars = as(ActionBars.class, site.getActionBars());
		if (bars == null)
			return null;

		MenuManager man = as(MenuManager.class, bars.getMenuManager());
		if (man == null)
			return null;

		Menu menu = man.getMenu();
		if ((menu == null || menu.isDisposed()) && create)
			man.createContextMenu(site.getShell());

		return man.getMenu();
	}

	@SuppressWarnings("restriction")
	public Control getToolbar(IWorkbenchPartReference reference) {
		try {
			return ((ToolBarManager) ((PartSite) ((WorkbenchPartReference) reference).getPart(false).getSite())
					.getActionBars().getToolBarManager()).getControl();
		} catch (Exception e) {
			// II: don't bother with NPE and class casts if internal API will
			// change
			return null;
		}
	}

	@Override
	public boolean canClickView(IWorkbenchPartReference reference) {
		return true;
	}

	@Override
	public Map<Control, SWTUIElement> getWorkbenchReference(SWTUIPlayer player) {

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow[] windows = null;

		windows = workbench.getWorkbenchWindows();
		if (!Display.getCurrent().equals(workbench.getDisplay())) {
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
				IWorkbenchPart refPart = ref.getPart(false);
				if (refPart != null && ref.getPane().getControl() != null) {
					Control control = ref.getPane().getControl();
					references.put(control, player.wrap(ref));
					Control toolbar = getToolbar(ref);
					if (toolbar != null) {
						references.put(toolbar, player.wrap(ref));
					}
				}
			}
			for (IEditorReference iEditorReference : editors) {
				WorkbenchPartReference ref = ((WorkbenchPartReference) iEditorReference);
				IWorkbenchPart refPart = ref.getPart(false);
				if (refPart != null && ref.getPane().getControl() != null) {
					Control control = ref.getPane().getControl();
					references.put(control, player.wrap(ref));
					// Control toolBar = ref.getPane().getToolBar();
					// if (toolBar != null) {
					// references.put(toolBar, player.wrap(ref));
					// }
				}
			}
		}
		return references;
	}

	private CTabFolder getCTabFolder(Widget widget) {
		if (widget instanceof CTabFolder) {
			return (CTabFolder) widget;
		}
		if (widget instanceof Control && widget.getData("modelElement") != null) {
			Composite parent = ((Control) widget).getParent();
			if (parent == null || parent instanceof CTabFolder)
				return (CTabFolder) parent;
			parent = parent.getParent();
			if (parent instanceof CTabFolder)
				return (CTabFolder) parent;
		}
		return null;
	}

	public void processTabFolderButton(Widget widget, int buttonId) {
		CTabFolder tabFolder = getCTabFolder(widget);
		if (tabFolder == null)
			return;

		ToolItem maxItem = null, minItem = null;
		try {
			Field field = CTabFolder.class.getDeclaredField("maxItem");
			field.setAccessible(true);
			maxItem = (ToolItem) field.get(tabFolder);

			field = CTabFolder.class.getDeclaredField("minItem");
			field.setAccessible(true);
			minItem = (ToolItem) field.get(tabFolder);
		} catch (Exception e) {
			TeslaCore.log(e);
		}

		if (maxItem == null || (minItem == null && buttonId == IWorkbenchPage.STATE_MINIMIZED))
			return;

		Event e = new Event();
		switch (buttonId) {
		case IWorkbenchPage.STATE_MAXIMIZED:
			if (tabFolder.getMaximized())
				return;
			e.widget = maxItem;
			e.type = SWT.Selection;
			maxItem.notifyListeners(SWT.Selection, e);
			break;
		case IWorkbenchPage.STATE_MINIMIZED:
			if (tabFolder.getMinimized())
				return;
			e.widget = minItem;
			e.type = SWT.Selection;
			minItem.notifyListeners(SWT.Selection, e);
			break;
		case IWorkbenchPage.STATE_RESTORED:
			if (!tabFolder.getMinimized() && !tabFolder.getMaximized())
				return;
			e.widget = maxItem;
			e.type = SWT.Selection;
			maxItem.notifyListeners(SWT.Selection, e);
			break;
		}
	}

	@Override
	public void processTabShowList(Widget widget) {
		CTabFolder tabFolder = getCTabFolder(widget);
		if (tabFolder == null)
			return;

		ToolItem chevronItem = null;
		try {
			Field field = CTabFolder.class.getDeclaredField("chevronItem");
			field.setAccessible(true);
			chevronItem = (ToolItem) field.get(tabFolder);
		} catch (Exception e) {
			TeslaCore.log(e);
		}

		if (chevronItem == null)
			return;

		if (!chevronItem.getParent().isVisible())
			return;

		Event e = new Event();
		e.widget = chevronItem;
		e.type = SWT.Selection;
		chevronItem.notifyListeners(SWT.Selection, e);
	}

	@Override
	public boolean isVisible(IWorkbenchPartReference reference) {
		IWorkbenchPart part = reference.getPart(false);
		if (part != null) {
			return ((WorkbenchPartReference) reference).getPane().getControl().isVisible();
		}
		return false;
	}

	@Override
	public boolean isActiveContainsView(IWorkbenchPage page, IWorkbenchPartReference reference) {
		// return isVisible(reference);
		return true;
	}

	@Override
	public boolean isViewOrEditorButton(Widget widget) {
		if (!(widget instanceof ToolItem))
			return false;

		ToolItem item = (ToolItem) widget;
		String tooltip = item.getToolTipText();
		if (!(tooltip == null || tooltip.equals("View Menu") || tooltip.equals("Maximize") || tooltip.equals("Minimize")
				|| tooltip.equals("Restore") || tooltip.equals("Show List")))
			return false;

		if (item.getData("theMenu") != null) // view menu
			return true;

		// restore button
		if (TeslaSWTAccess.getThis(TrimStack.class, item, SWT.Selection) != null)
			return true;

		Composite parent = item.getParent().getParent();
		if (!(parent instanceof CTabFolder))
			return false;

		Widget control = extractViewOrEditorControl((CTabFolder) parent);
		return control != null;
	}

	@Override
	public boolean isSupported() {
		Version version = TeslaCore.getPlatformVersion();
		if (!TeslaCore.isE4() && version.getMajor() == 3 && version.getMinor() >= 103) {
			return true;
		}
		return false;
	}

	@Override
	public Widget extractViewOrEditorControl(CTabFolder tabFolder) {
		if (tabFolder.getSelection() == null)
			return null;

		Control selection = tabFolder.getSelection().getControl();

		if (selection instanceof Composite) {

			Object data = selection.getData("modelElement");
			if (data instanceof PartImpl)
				return selection; // editors

			// views
			Control[] children = ((Composite) selection).getChildren();
			for (Control c : children) {
				if (!(c instanceof Composite))
					continue;

				data = c.getData("modelElement");
				if (!(data instanceof PartImpl))
					continue;

				return c;
			}

			return null;
		} else
			return null;
	}

	@Override
	public CTabFolder getTabFolderFromButton(ToolItem button) {
		Composite parent = button.getParent().getParent();
		if (parent instanceof CTabFolder)
			return (CTabFolder) parent;

		// view menu
		if (parent != null && button.getData("theMenu") != null) {
			parent = parent.getParent();
			if (parent instanceof CTabFolder)
				return (CTabFolder) parent;
		}

		// restore button
		TrimStack trimStack = TeslaSWTAccess.getThis(TrimStack.class, button, SWT.Selection);
		if (trimStack != null) {
			try {
				Field field = TrimStack.class.getDeclaredField("minimizedElement");
				field.setAccessible(true);
				Object minimizedElement = field.get(trimStack);

				if (minimizedElement instanceof PartStackImpl) { // views
					Object widget = ((PartStackImpl) minimizedElement).getWidget();
					if (widget instanceof CTabFolder)
						return (CTabFolder) widget;
				} else if (minimizedElement instanceof PlaceholderImpl) { // editors
					MUIElement ref = ((PlaceholderImpl) minimizedElement).getRef();
					if (ref instanceof AreaImpl) {
						Object selected = ((AreaImpl) ref).getSelectedElement();
						if (selected instanceof PartStackImpl) {
							Object widget = ((PartStackImpl) selected).getWidget();
							if (widget instanceof CTabFolder)
								return (CTabFolder) widget;
						}
					}
				}

			} catch (Exception e) {
			}
		}

		return null;
	}

	private static final String JUNO_TEXT_FIELD = "getFilterText";
	private static final String KEPLER_TEXT_FIELD = "getQuickAccessSearchText";

	@Override
	public Text getQuickAccess() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			return null;

		MWindow mWindow = ((WorkbenchWindow) window).getModel();
		if (mWindow == null)
			return null;

		EModelService modelService = mWindow.getContext().get(EModelService.class);
		if (modelService == null)
			return null;

		MToolControl searchField = (MToolControl) modelService.find("SearchField", mWindow);
		if (searchField == null)
			return null;

		if (!(searchField.getObject() instanceof SearchField))
			return null;

		SearchField field = (SearchField) searchField.getObject();
		if (field == null)
			return null;

		Object result = ReflectionUtil.callMethod(field, JUNO_TEXT_FIELD);
		if (result == null) {
			result = ReflectionUtil.callMethod(field, KEPLER_TEXT_FIELD);
		}
		return result == null ? null : (Text) result;
	}

	@Override
	public void updateActiveSelection(List<Object> selectionData, SWTUIElement parent) {
		List<SWTUIElement> parentsList = parent.getPlayer().getParentsList(parent);
		parentsList.add(parent);
		for (SWTUIElement e : parentsList) {
			final GenericElementKind kind = e.getKind();
			if (kind.is(ElementKind.View) || kind.is(ElementKind.Editor)) {
				if (e instanceof WorkbenchUIElement) {
					final IWorkbenchPartReference reference = ((WorkbenchUIElement) e).getReference();
					IWorkbenchWindow window = reference.getPage().getWorkbenchWindow();
					IWorkbenchPart part = reference.getPart(true);
					ISelectionService selectionService = window.getSelectionService();

					((SelectionService) selectionService).updateSelection(part);
				}
			}
		}
	}

	@Override
	public String getViewId(Widget widget) {
		// not supported for now
		return null;
	}

	@Override
	public GenericElementKind getWidgetKind(Widget widget) {
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
				@Override
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
				for (IPerspectiveDescriptor desc : descriptors) {
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
		@Override
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
