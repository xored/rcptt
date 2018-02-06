package org.eclipse.rcptt.tesla.workbench.player;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrap;
import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrapWidget;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.internal.ui.player.AbstractSWTUIPlayerExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.ChildrenCollectingSession;
import org.eclipse.rcptt.tesla.internal.ui.player.IChildrenCollectingExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerWidgetUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPartReference;

@SuppressWarnings("restriction")
public class WorkbenchUIPlayerExtension extends AbstractSWTUIPlayerExtension {
	
	protected static Map<Class<?>, ElementKind> elementKinds = new LinkedHashMap<Class<?>, ElementKind>();
	
	static {
		elementKinds.put(IViewReference.class, ElementKind.View);
		elementKinds.put(IEditorReference.class, ElementKind.Editor);
	}

	@Override
	public GenericElementKind getKind(Object w) {
		ElementKind kind = elementKinds.get(w.getClass());
		if (kind == null) {
			for (Map.Entry<Class<?>, ElementKind> entry : elementKinds.entrySet()) {
				Class<?> key = entry.getKey();
				if (key.isInstance(w)) {
					return new GenericElementKind(entry.getValue());
				}
			}
		}
		if (kind != null) {
			return new GenericElementKind(kind);
		}
		return null;
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
		}

		ElementKind kind = widget.getKind().kind;
		if (!ElementKind.View.equals(kind) && !ElementKind.Editor.equals(kind)) {
			return false;
		}

		boolean visible = true;
		Widget control = unwrapWidget(widget);
		if (control instanceof Control) {
			visible = !control.isDisposed() && PlayerWidgetUtils.isVisible((Control) control);
		}
		return !PlayerWidgetUtils.isDisabled(widget) && visible;
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
		return null;
	}

	@Override
	public IChildrenCollectingExtension getChildrenCollectingExtension(final ChildrenCollectingSession session) {
		return new IChildrenCollectingExtension() {

			@Override
			public void collect() {
				if (!(session.w instanceof WorkbenchUIElement)) {
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
	public void updateActiveSelection(List<Object> selectionData, SWTUIElement element) {
		EclipseWorkbenchProvider.getProvider().updateActiveSelection(selectionData, element);
	}

	@Override
	public Object getReference(Widget widget) {
		Map<Control, Object> references = EclipseWorkbenchProvider.getProvider()
				.getWorkbenchReference();
		if (references != null && references.containsKey(widget)) {
			return references.get(widget);
		}
		return null;
	}

}
