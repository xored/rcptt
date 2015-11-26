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
package org.eclipse.rcptt.tesla.swt.e4pure;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils.safeMatches;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.impl.AreaImpl;
import org.eclipse.e4.ui.model.application.ui.advanced.impl.PlaceholderImpl;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.impl.PartImpl;
import org.eclipse.e4.ui.model.application.ui.basic.impl.PartStackImpl;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.workbench.addons.minmax.TrimStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.core.scenario.WorkbenchContext;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.runtime.ui.model.e4x.E4ModelProcessor;
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
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
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
import org.eclipse.ui.ISelectionService;
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

@SuppressWarnings("restriction")
public class PureE4WorkbenchProvider implements IEclipseWorkbenchProvider {
	// TODO: Deduplicate the functionality in common with E4WorkbenchProvider.

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

	@Override
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
		Map<Control, SWTUIElement> references = new HashMap<Control, SWTUIElement>();

		if (!Display.getCurrent().equals(E4ModelProcessor.getDisplay())) {
			return references;
		}

		// get all parts (visible and active parts) from THIS window
		try {
			Collection<MPart> parts = E4ModelProcessor.getPartService().getParts();
			for (MPart part : parts) {
				if (part.isVisible()) {
					SWTUIElement swtUIElement = player.wrap(part.getWidget());
					references.put((Control) part.getWidget(), swtUIElement);
				}
			}
			return references;
		} catch (IllegalStateException e) {
			// There are no active parts.
			return references;
		}
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

	@Override
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
		return TeslaCore.isE4();
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
		Object data = widget.getData("modelElement");
		if (data instanceof MPart) {
			return E4ModelProcessor.getPartKind((MPart) data);
		}
		return GenericElementKind.Unknown;
	}

	@Override
	public String getWidgetRawText(Widget widget) {
		Object partObject = widget.getData("modelElement");
		if (partObject instanceof MPart) {
			MPart part = (MPart) partObject;
			return part.getLabel();
		}
		return null;
	}

	@Override
	public void activatePart(SWTUIElement element) {
		MPart part = (MPart) element.widget.getData("modelElement");

		if (part == null) {
			return;
		}

		E4ModelProcessor.getPartService().activate(part);
		E4ModelProcessor.getModelService().bringToTop(part);
		E4ModelProcessor.getSelectionService().setSelection(part);

		MUIElement activePart = (MUIElement) E4ModelProcessor.getSelectionService().getSelection();
		if (!part.equals(activePart)) {
			throw new RuntimeException(
					NLS.bind(TeslaSWTMessages.SWTUIPlayer_WorkbenchPartNotActivated, element.toString()));
		}

	}

	@Override
	public Widget selectPart(PlayerSelectionFilter f) {
		final String pattern = f.pattern;
		final GenericElementKind kind = f.kind;

		Collection<MPart> parts = E4ModelProcessor.getPartService().getParts();
		for (MPart part : parts) {
			if (E4ModelProcessor.getPartKind(part).is(kind) && matches(part.getLabel(), pattern)) {
				E4ModelProcessor.getModelService().bringToTop(part);
				return (Widget) part.getWidget();
			}
		}

		return null;
	}

	private static boolean matches(String value, String pattern) {
		return pattern == null || (value != null && (value.equals(pattern) || safeMatches(value, pattern)));
	}

	@Override
	public IWorkbenchWindow[] getWorkbenchWindows() {
		List<IWorkbenchWindow> windows = new ArrayList<IWorkbenchWindow>();
		List<MWindow> children = E4ModelProcessor.getApplication().getChildren();
		for (MWindow window : children) {
			IEclipseContext context = window.getContext();
			if (context != null) {
				IWorkbenchWindow wwindow = (IWorkbenchWindow) context.get(IWorkbenchWindow.class.getName());
				if (wwindow != null) {
					windows.add(wwindow);
				}
			}
		}
		return windows.toArray(new IWorkbenchWindow[windows.size()]);
	}

	@Override
	public Display getDisplay() {
		return E4ModelProcessor.getDisplay();
	}

	@Override
	public int getWorkbenchWindowCount() {
		return E4ModelProcessor.getApplication().getChildren().size();
	}

	@Override
	public Shell getActiveShell() {
		Display display = getDisplay();
		if (display == Display.getCurrent()) {
			return getDisplay().getActiveShell();
		}
		List<MWindow> windows = E4ModelProcessor.getApplication().getChildren();
		if (!windows.isEmpty()) {
			return (Shell) windows.get(0).getWidget();
		}
		return null;
	}

	@Override
	public void applyContext(org.eclipse.rcptt.core.scenario.Context context) throws CoreException {
		final WorkbenchContext ctx = (WorkbenchContext) context;
		final UIJobCollector collector = new UIJobCollector();
		Job.getJobManager().addJobChangeListener(collector);
		try {
			Display display = E4ModelProcessor.getDisplay();
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					collector.enable();
				}
			});
			if (ctx.isNoModalDialogs()) {
				// e4 quickfix
				// display.asyncExec(closeModalDialogsAsync);
				IStatus status = UIRunnable.exec(closeModalDialogs);
				if (!status.isOK())
					throw new CoreException(status);
			}
			UIRunnable.exec(closeIntro);

			UIRunnable<Object> activatePerspective = new UIRunnable<Object>() {

				@Override
				public Object run() throws CoreException {
					MPart activePart = E4ModelProcessor.getActivePart();
					boolean noActivePerspective = activePart == null;

					MPerspective currentPerspective = noActivePerspective ? null
							: E4ModelProcessor.getModelService().getPerspectiveFor(activePart);
					String ctxPerspectiveId = ctx.getPerspectiveId();

					boolean areWeOnDifferentPerspective = ctxPerspectiveId != null
							&& !ctxPerspectiveId.equals(currentPerspective.getElementId());
					if (noActivePerspective || areWeOnDifferentPerspective) {
						MApplication app = E4ModelProcessor.getApplication();
						List<MPerspective> perspectives = E4ModelProcessor.getModelService().findElements(app,
								ctxPerspectiveId, MPerspective.class, null);
						if (!perspectives.isEmpty()) {
							MPerspective perspective = perspectives.get(0);
							E4ModelProcessor.getPartService().switchPerspective(perspective);
						}
					}
					return null;
				}

			};
			UIRunnable.exec(activatePerspective);

			if (ctx.isCloseEditors()) {
				UIRunnable.exec(hideAllEditorParts);
			}

			if (ctx.isClearClipboard()) {
				UIRunnable.exec(clearClipboard);
			}

			final String ctxPerspectiveId = ctx.getPerspectiveId();

			if (ctxPerspectiveId != null && ctx.isResetPerspective()) {
				UIRunnable<Object> resetPerspective = new UIRunnable<Object>() {

					@Override
					public Object run() throws CoreException {
						MApplication app = E4ModelProcessor.getApplication();
						List<MPerspective> perspectives = E4ModelProcessor.getModelService().findElements(app,
								ctxPerspectiveId, MPerspective.class, null);
						List<MWindow> windows = E4ModelProcessor.getApplication().getChildren();

						if (!perspectives.isEmpty() && !windows.isEmpty()) {
							MPerspective perspective = perspectives.get(0);
							MWindow currWindow = windows.get(0);
							E4ModelProcessor.getModelService().resetPerspectiveModel(perspective, currWindow);

						}

						return null;
					}

				};
				UIRunnable.exec(resetPerspective);

				UIRunnable<Object> cleanOtherPerspectives = new UIRunnable<Object>() {

					@Override
					public Object run() throws CoreException {
						MPart activePart = E4ModelProcessor.getPartService().getActivePart();
						MElementContainer<MUIElement> perspectiveStack = E4ModelProcessor.getModelService()
								.getPerspectiveFor(activePart).getParent();
						for (MUIElement persp_ : perspectiveStack.getChildren()) {
							if (persp_ instanceof MPerspective) {
								MPerspective persp = (MPerspective) persp_;
								IPreferenceStore store = org.eclipse.ui.internal.WorkbenchPlugin.getDefault()
										.getPreferenceStore();

								store.setToDefault(persp.getElementId() + "_persp");

							}
						}
						return null;

					}

				};
				UIRunnable.exec(cleanOtherPerspectives);

			}

			UIRunnable<Object> setPerspective = new UIRunnable<Object>() {

				@Override
				public Object run() throws CoreException {
					MApplication app = E4ModelProcessor.getApplication();
					EModelService modelService = E4ModelProcessor.getModelService();

					List<MPerspective> perspectives = modelService.findElements(app, ctxPerspectiveId,
							MPerspective.class, null);
					if (perspectives.isEmpty()) {
						perspectives = modelService.findElements(app, null, MPerspective.class, null);
					}
					if (!perspectives.isEmpty()) {
						E4ModelProcessor.getPartService().switchPerspective(perspectives.get(0));
					}

					return null;
				}

			};

			UIRunnable.exec(setPerspective);
			openPartsE4(ctx);

			// update selection
			Collection<MPart> parts = E4ModelProcessor.getPartService().getParts();
			for (final MPart part : parts) {
				if (part.isVisible()) {
					UIRunnable.exec(new UIRunnable<Object>() {

						@Override
						public Object run() throws CoreException {
							E4ModelProcessor.getSelectionService().setSelection(part);
							return null;
						}

					});
				}
			}

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
	
	private final UIRunnable<Object> hideAllEditorParts = new UIRunnable<Object>() {
		@Override
		public Object run() throws CoreException {

			for (MPart part : E4ModelProcessor.getPartService().getParts()) {
				if (E4ModelProcessor.isEditor(part)) {
					E4ModelProcessor.getPartService().hidePart(part);
				}
			}
			return null;
		}
	};
	
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
	
	private void openPartsE4(final WorkbenchContext context) throws CoreException {

		Collection<MPart> parts = E4ModelProcessor.getPartService().getParts();

		if (context.isCloseEditors()) {
			UIRunnable.exec(hideAllEditorParts);
		}

		for (MPart part : parts) {
			final MPart part_ = part;

			boolean isEditorFromContext = context.getEditors().contains(part_);
			boolean isEditor = E4ModelProcessor.isEditor(part_);

			if (!isEditor || isEditorFromContext) {
				UIRunnable.exec(new UIRunnable<Object>() {
					@Override
					public Object run() throws CoreException {
						// visible but not active
						E4ModelProcessor.getPartService().showPart(part_, PartState.VISIBLE);
						return null;
					}
				});
			}

		}

	}
	
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
}
