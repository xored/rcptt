package org.eclipse.rcptt.tesla.workbench;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils.safeMatches;
import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrap;
import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrapWidget;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.ChildrenCollectingSession;
import org.eclipse.rcptt.tesla.internal.ui.player.IChildrenCollectingExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTUIPlayerExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerWidgetUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPartReference;
import org.eclipse.ui.internal.registry.EditorRegistry;

@SuppressWarnings("restriction")
public class WorkbenchUIPlayerExtension implements ISWTUIPlayerExtension {
	
	// TODO (e4 support): is it needed?
	protected static Map<Class<?>, ElementKind> elementKinds = new LinkedHashMap<Class<?>, ElementKind>();
	
	static {
		elementKinds.put(IViewReference.class, ElementKind.View);
		elementKinds.put(IEditorReference.class, ElementKind.Editor);
	}

	@Override
	public SWTUIElement wrap(Object s, SWTUIPlayer p) {
		if (s instanceof IWorkbenchPart) {
			IWorkbenchPart part = (IWorkbenchPart) s;
			IWorkbenchPartSite site = part.getSite();
			if (site != null) {
				// System.out.println("Site is not null");
				IWorkbenchWindow window = site.getWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					IWorkbenchPartReference reference = page.getReference(part);
					if (reference != null) {
						return new WorkbenchUIElement(reference, p);
					}
				}
				IWorkbenchPage[] pages = window.getPages();
				for (IWorkbenchPage wp : pages) {
					IWorkbenchPartReference ref = wp.getReference(part);
					if (ref != null) {
						return new WorkbenchUIElement(ref, p);
					}
				}
			} else {
				// System.out.println("Site is null");
			}
			// Obtain using initialization
			IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
			for (IWorkbenchWindow win : windows) {
				IWorkbenchPage[] pages = win.getPages();
				for (IWorkbenchPage wp : pages) {
					IWorkbenchPartReference reference = wp.getReference(part);
					if (reference != null) {
						return new WorkbenchUIElement(reference, p);
					}
				}
			}
			// System.out.println("Failed to create wrap for item:"
			// + part.getTitle());
		}
		if (s instanceof IWorkbenchPartReference) {
			return new WorkbenchUIElement((IWorkbenchPartReference) s, p);
		}
		return null;
	}

	@Override
	public boolean canClick(SWTUIElement widget, boolean isDefault, boolean doubleClick, boolean arrow) {
		if (unwrap(widget) instanceof IWorkbenchPartReference) {
			if (!canClickView(widget)) {
				return false;
			}
			return true;
		} else {
			boolean visible = true;
			Widget control = unwrapWidget(widget);
			if (control instanceof Control) {
				visible = !control.isDisposed() && PlayerWidgetUtils.isVisible((Control) control);
			}
			return !PlayerWidgetUtils.isDisabled(widget) && visible;
		}
	}

	private boolean canClickView(SWTUIElement w) {
		if (w instanceof WorkbenchUIElement) {
			IWorkbenchPartReference reference = ((WorkbenchUIElement) w).getReference();
			return EclipseWorkbenchProvider.getProvider().canClickView(reference);
		}
		return true;
	}

	@Override
	public void click(SWTUIElement widget, boolean isDefault, boolean doubleClick, boolean arrow) {
		IWorkbenchPage page = getTargetPage();
		switch (widget.getKind().kind) {
		case View:
			clickView(widget, page);
			break;
		case Editor:
			clickEditor(widget, page);
			break;
		}
	}

	private IWorkbenchPage getTargetPage() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page == null) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
			page = window.getActivePage();
			if (page == null) {
				page = window.getPages()[0];
			}
		}
		return page;
	}

	private void clickView(final SWTUIElement w, IWorkbenchPage page) {
		IViewReference view = (IViewReference) (((WorkbenchUIElement) w).reference);

		IWorkbenchPart part = view.getPart(true);
		if (part == null) {
			return;
		}
		page.activate(part);
		IWorkbenchPart activePart = page.getActivePart();
		if (!part.equals(activePart)) {
			throw new RuntimeException(NLS.bind(TeslaSWTMessages.SWTUIPlayer_WorkbenchPartNotActivated, w.toString()));
		}
	}

	private void clickEditor(final SWTUIElement w, IWorkbenchPage page) {
		IEditorReference editor = (IEditorReference) (((WorkbenchUIElement) w).reference);
		IWorkbenchPart editorPart = editor.getPart(true);
		page.bringToTop(editorPart);
		page.activate(editorPart);
	}

	@Override
	public SWTUIElement select(SWTUIPlayer player, PlayerSelectionFilter filter) {
		SWTUIElement result = null;

		switch (filter.kind.kind) {
		case EclipseWindow:
			result = selectEclipseWindow(player, filter.index);
			break;
		case View:
			result = selectView(player, filter);
			break;
		case Editor:
			result = selectEditor(player, filter);
			break;
		case QuickAccess:
			result = selectQuickAccess(player);
			break;
		}
		return result;
	}

	private SWTUIElement selectEclipseWindow(SWTUIPlayer player, Integer index) {
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		if (index == null) {
			return player.wrap(windows[0].getShell());
		} else if (index.intValue() < windows.length) {
			return player.wrap(windows[index.intValue()].getShell());
		}
		return null;
	}

	// stable view is a view that do not change its title, so
	// we can skip it while gathering actual titles of views
	private static final Set<String> stableViews = new HashSet<String>();
	static {
		stableViews.add("org.eclipse.ui.views.PropertySheet");
		stableViews.add("org.eclipse.ui.views.ProblemView");
	}

	// @SuppressWarnings("restriction")
	public SWTUIElement selectView(SWTUIPlayer player, PlayerSelectionFilter f) {
		final String pattern = f.pattern;

		// IViewDescriptor[] views =
		// PlatformUI.getWorkbench().getViewRegistry().getViews();
		IViewReference[] views = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getViewReferences();
		int currIdx = 0;
		for (IViewReference iViewRef : views) {
			try {
				String label = iViewRef.getPartName();
				String id = iViewRef.getId();

				if ((label != null && (label.equals(pattern) || safeMatches(label, pattern)))
						|| (id != null && (id.equals(pattern) || safeMatches(id, pattern)))) {
					if (f.index == null || f.index.equals(currIdx))
						return player.wrap(iViewRef);
					currIdx++;
				}
			} catch (Exception e) {
				// Skip brokeb parts.
				TeslaCore.log(e);
			}
		}

		TeslaCore.log("Can not find view by pattern \"" + pattern + "\". Activating views...");

		// Not found, lets go with resolve of view parts, it will initialize
		// titles.
		currIdx = 0;
		for (IViewReference iViewRef : views) {
			// try to skip well-known buggy views with immutable titles
			if (stableViews.contains(iViewRef.getId()))
				continue;

			IWorkbenchPart part = iViewRef.getPart(true);
			String title = part != null ? part.getTitle() : null;

			if ((title != null && (title.equals(pattern) || safeMatches(title, pattern)))) {
				if (f.index == null || f.index.equals(currIdx))
					return player.wrap(iViewRef);
				currIdx++;
			}
		}
		return null;
	}

	public SWTUIElement selectEditor(SWTUIPlayer player, PlayerSelectionFilter f) {
		String title = f.pattern;
		if (title != null && title.length() == 0)
			title = null;

		String type = f.classPattern;
		if (type != null && type.length() == 0)
			type = null;

		//

		IEditorReference[] refs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();

		if (f.index != null && f.index < 0)
			return null;

		// -- old style

		if (title != null && type == null && f.index == null) {
			for (IEditorReference ref : refs)
				if (matches(ref.getPartName(), title) || matches(ref.getId(), title))
					return player.wrap(ref);
			return null;
		}

		// -- new style

		String id = null;
		if (type != null)
			for (IEditorDescriptor desc : ((EditorRegistry) PlatformUI.getWorkbench().getEditorRegistry())
					.getSortedEditorsFromPlugins())
				if (matches(desc.getLabel(), type))
					id = desc.getId();

		int counter = 0;
		for (IEditorReference ref : refs)
			if (matches(ref.getPartName(), title) && matches(ref.getId(), id))
				if (matches(counter++, f.index))
					return player.wrap(ref);

		return null;
	}

	private static boolean matches(String value, String pattern) {
		return pattern == null || (value != null && (value.equals(pattern) || safeMatches(value, pattern)));
	}

	private static boolean matches(Integer value, Integer pattern) {
		return pattern == null || (value != null && value.equals(pattern));
	}

	private SWTUIElement selectQuickAccess(SWTUIPlayer player) {
		Text quickAccess = EclipseWorkbenchProvider.getProvider().getQuickAccess();
		return quickAccess == null ? null : player.wrap(quickAccess);
	}

	@Override
	public IChildrenCollectingExtension getChildrenCollectingExtension(final ChildrenCollectingSession session) {
		return new IChildrenCollectingExtension() {

			@Override
			public void collect() {
				if (session.w instanceof WorkbenchUIElement) {
					return;
				}
				WorkbenchUIElement wbelement = (WorkbenchUIElement) session.w;
				if (session.needToCollectMenuItems() && session.w.isView()) {
					session.collectMenuItems(wbelement.getViewMenu(), null);
				}

				WorkbenchPartReference reference = (WorkbenchPartReference) wbelement.getReference();
				Control toolBar = EclipseWorkbenchProvider.getProvider().getToolbar(reference);
				if (toolBar != null) {
					session.addItem(session.wrap(toolBar));
					SWTUIElement[] children = session.collector.collectFor(session.wrap(toolBar), session.ignores,
							session.goIntoComposites, session.classes);
					session.addResults(Arrays.asList(children));
				}
			}

		};
	}

	@Override
	public String getRawText(SWTUIElement element) {
		Object rawElement = PlayerWrapUtils.unwrap(element);
		if (rawElement instanceof IWorkbenchPartReference) {
			return ((IWorkbenchPartReference) rawElement).getPartName();
		}
		return null;
	}

	@Override
	public Object getIndirectParent(Widget current) {
		if (current instanceof ToolBar) {
			// Check work view/editor toolbars, they have different parent
			Map<Control, Object> references = EclipseWorkbenchProvider.getProvider()
					.getWorkbenchReference();
			if (references != null && references.containsKey(current)) {
				return references.get(current);
			}
		}
		return null;
	}

	@Override
	public GenericElementKind getKind(Object w) {
		return null;
	}

	@Override
	public SWTUIElement getShell(SWTUIElement element) {
		return null;
	}

	@Override
	public Class<?> getSearchableClass(Object widget) {
		return null;
	}

	@Override
	public boolean isCollectable(SWTUIElement element, Class<?>[] collectableTypes) {
		return false;
	}

}
