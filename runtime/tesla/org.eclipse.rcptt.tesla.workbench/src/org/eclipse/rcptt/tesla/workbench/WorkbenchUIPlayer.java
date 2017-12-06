package org.eclipse.rcptt.tesla.workbench;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrapWidget;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.rcptt.util.ShellUtilsProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

public class WorkbenchUIPlayer {

	private SWTUIPlayer swtPlayer;

	public WorkbenchUIPlayer(SWTUIPlayer swtPlayer) {
		this.swtPlayer = swtPlayer;
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
