package org.eclipse.rcptt.tesla.workbench.player;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils.safeMatches;
import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrapWidget;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.rcptt.util.ShellUtilsProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.registry.EditorRegistry;

@SuppressWarnings("restriction")
public class WorkbenchUIPlayer {

	private SWTUIPlayer swtPlayer;

	public WorkbenchUIPlayer(SWTUIPlayer swtPlayer) {
		this.swtPlayer = swtPlayer;
	}

	public SWTUIElement select(PlayerSelectionFilter filter) {
		switch (filter.kind.kind) {
		case EclipseWindow:
			return selectEclipseWindow(swtPlayer, filter.index);
		case View:
			return selectView(swtPlayer, filter);
		case Editor:
			return selectEditor(swtPlayer, filter);
		case QuickAccess:
			return selectQuickAccess(swtPlayer);
		}
		return null;
	}

	public SWTUIElement selectEclipseWindow(SWTUIPlayer player, Integer index) {
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

	public SWTUIElement selectQuickAccess(SWTUIPlayer player) {
		Text quickAccess = EclipseWorkbenchProvider.getProvider().getQuickAccess();
		return quickAccess == null ? null : player.wrap(quickAccess);
	}

	public void typeAction(final SWTUIElement element, final String actionId) {
		getSWTPlayer().exec("typeAction", new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("cast") // IServiceLocator.getService was not
											// generic in Eclipse 4.4 and older.
				IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench()
						.getService(IHandlerService.class);
				try {
					handlerService.executeCommand(actionId, null);
				} catch (Exception e) {
					TeslaCore.log(e);
				}
			}
		});
	}

	public void save(final SWTUIElement w) {
		getSWTPlayer().exec("save", new Runnable() {
			@Override
			public void run() {
				if (w.getKind().kind == ElementKind.Editor) {
					IEditorReference editor = (IEditorReference) (((WorkbenchUIElement) w).reference);
					IEditorPart editorPart = editor.getEditor(false);
					if (editorPart != null) {
						editorPart.doSave(new NullProgressMonitor());
					}
				}

			}
		});

	}

	public void close(final SWTUIElement uiElement) {
		getSWTPlayer().exec("close", new Runnable() {
			@Override
			public void run() {
				if (uiElement instanceof WorkbenchUIElement) {
					IWorkbenchPartReference reference = ((WorkbenchUIElement) uiElement).getReference();
					if (reference == null) {
						return;
					}
					IWorkbenchPart part = reference.getPart(false);
					if (part != null) {
						IWorkbenchPage page = part.getSite().getPage();
						if (part instanceof IEditorPart) {
							page.closeEditor((IEditorPart) part, true);
						} else if (part instanceof IViewPart) {
							IViewPart vp = (IViewPart) part;
							page.hideView(vp);
							// hideView already call dispose for ViewPart
							// vp.dispose();
						}
					}
				}
			}
		});
	}

	public boolean isDirty(final SWTUIElement w) {
		if (w.getKind().kind == ElementKind.Editor) {
			IEditorReference editor = (IEditorReference) (((WorkbenchUIElement) w).reference);
			return editor.isDirty();
		}
		return false;
	}

	public void minimize(SWTUIElement uiElement) {
		final Widget widget = unwrapWidget(uiElement);
		getSWTPlayer().exec("minimize", new Runnable() {
			@Override
			public void run() {
				processTabFolderButton(widget, IWorkbenchPage.STATE_MINIMIZED);
			}
		});
	}

	public void maximize(SWTUIElement uiElement) {
		final Widget widget = unwrapWidget(uiElement);
		getSWTPlayer().exec("maximize", new Runnable() {
			@Override
			public void run() {
				if (widget instanceof Shell) {
					((Shell) widget).setMaximized(true);
					try {
						ShellUtilsProvider.getShellUtils().forceActive((Shell) widget);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				}
				processTabFolderButton(widget, IWorkbenchPage.STATE_MAXIMIZED);
			}
		});
	}

	public void restore(SWTUIElement uiElement) {
		final Widget widget = unwrapWidget(uiElement);
		getSWTPlayer().exec("restore", new Runnable() {
			@Override
			public void run() {
				processTabFolderButton(widget, IWorkbenchPage.STATE_RESTORED);
			}
		});
	}

	private void processTabFolderButton(Widget widget, int buttonId) {
		EclipseWorkbenchProvider.getProvider().processTabFolderButton(widget, buttonId);
	}

	public void showTabList(SWTUIElement uiElement) {
		final Widget widget = unwrapWidget(uiElement);
		getSWTPlayer().exec("showTabList", new Runnable() {
			@Override
			public void run() {
				processTabShowList(widget);
			}
		});
	}

	private void processTabShowList(Widget widget) {
		EclipseWorkbenchProvider.getProvider().processTabShowList(widget);
	}

	public void setPerspective(final String perspectiveId) {
		getSWTPlayer().exec("setPerspective", new Runnable() {
			@Override
			public void run() {
				IPerspectiveDescriptor persectiveDescriptor = PlatformUI.getWorkbench().getPerspectiveRegistry()
						.findPerspectiveWithId(perspectiveId);
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.setPerspective(persectiveDescriptor);
			}
		});
	}

	public SWTUIPlayer getSWTPlayer() {
		return swtPlayer;
	}

}
