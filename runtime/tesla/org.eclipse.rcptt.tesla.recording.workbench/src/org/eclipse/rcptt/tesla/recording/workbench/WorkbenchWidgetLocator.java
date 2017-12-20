package org.eclipse.rcptt.tesla.recording.workbench;

import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.IWindowProvider;
import org.eclipse.rcptt.tesla.core.protocol.PartUIElement;
import org.eclipse.rcptt.tesla.core.protocol.WindowUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.FindResult;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTModelMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.recording.core.TeslaRecorder;
import org.eclipse.rcptt.tesla.recording.core.swt.BasicRecordingHelper.ElementEntry;
import org.eclipse.rcptt.tesla.recording.core.swt.IWidgetClassifierExtension;
import org.eclipse.rcptt.tesla.recording.core.swt.IWidgetLocatorExtension;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTRecordingHelper;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTWidgetLocator;
import org.eclipse.rcptt.tesla.swt.util.GetWindowUtil;
import org.eclipse.rcptt.tesla.swt.util.IndexUtil;
import org.eclipse.rcptt.tesla.workbench.WorkbenchUIElement;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchPartReference;
import org.eclipse.ui.part.WorkbenchPart;

@SuppressWarnings("restriction")
public class WorkbenchWidgetLocator implements IWidgetLocatorExtension {

	private TeslaRecorder recorder;
	private SWTWidgetLocator swtLocator;
	private SWTUIPlayer swtPlayer;

	public WorkbenchWidgetLocator(TeslaRecorder recorder, SWTWidgetLocator swtLocator, SWTUIPlayer swtPlayer) {
		this.recorder = recorder;
		this.swtLocator = swtLocator;
		this.swtPlayer = swtPlayer;
	}

	@Override
	public IWidgetClassifierExtension getWidgetClassifierExtension() {
		return null;
	}

	@Override
	public FindResult findElement(SWTUIElement widget, boolean unknownAllowed, boolean alwaysFindLeaf,
			boolean supportEclipseWorkbench) {
		if (widget == null) {
			return null;
		}
		if (widget instanceof WorkbenchUIElement) {
			WorkbenchUIElement ee = (WorkbenchUIElement) widget;
			IWorkbenchPart part = ee.reference.getPart(true);
			PartUIElement element = findPartElement(part, alwaysFindLeaf);
			if (element != null) {
				return new FindResult(widget, element.getElement());
			}
		}
		if (widget.unwrapWidget() instanceof Control) {
			Control control = (Control) widget.unwrapWidget();
			Shell shell = control.getShell();

			SWTUIElement uiElement = getSWTPlayer().wrap(control);
			SWTUIElement parentElement = getSWTPlayer().getParentElement(uiElement);

			// TODO (e4 support): check findElement() from swt locator usage
			FindResult parentResult = getSWTLocator().findElement(parentElement, unknownAllowed, alwaysFindLeaf,
					supportEclipseWorkbench);

			if (parentResult != null && parentResult.element != null) {
				// Check for editor/view element itself
				if (checkForViewEditor(control, shell, parentElement)) {
					return new FindResult(parentElement, parentResult.element);
				}
			}
		}
		return null;
	}

	@Override
	public boolean isSkippedControl(boolean supportEclipseWorkbench, Control control, Shell shell) {
		if (control instanceof CTabFolder) {
			// Check for tab folder activation(click) for Workbench.
			// List<Widget> parents = SWTUIPlayer.collectParents(widget);
			// Check for workbench internal element click
			IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();
			for (IWorkbenchWindow iWorkbenchWindow : workbenchWindows) {
				Shell wshell = iWorkbenchWindow.getShell();
				if (wshell == shell) {
					WorkbenchPage page = (WorkbenchPage) iWorkbenchWindow.getActivePage();
					Composite composite = page.getClientComposite();
					if (control.getParent().equals(composite)) {
						// Skip click on views/editors tab folder
						// hasViewEditorCTabFolderClick = true;
						if (!supportEclipseWorkbench) {
							return true;
						}
					}
					// e46 checks.
					Object o = control.getData("modelElement");
					if (o != null && o.getClass().getName()
							.contains("org.eclipse.e4.ui.model.application.ui.basic.impl.PartStackImpl")) {
						return true;
					}
					break;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isAfterSkippedForWidget(Widget widget, SWTUIElement lowerParent) {
		// don't try search -after for top level tab folders
		boolean isTopLevelTabFolder = widget instanceof CTabFolder && isWorkbenchWindow(lowerParent);
		return isTopLevelTabFolder;
	}

	private boolean isWorkbenchWindow(SWTUIElement element) {
		if (element.getKind().is(ElementKind.Window)) {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell().equals(element.widget);
		}
		return false;
	}

	@Override
	public boolean isMenuSourceFiltered(Widget widget, Menu upperMenu) {
		// Filter toolbars as menu source for menu from toolbar buttons with drop down arrows
		return widget instanceof ToolBar && !upperMenu.equals(((ToolBar) widget).getMenu())
				&& isParentEclipseWindow(upperMenu);
	}

	private boolean isParentEclipseWindow(Menu menu) {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().equals(menu.getParent());
	}

	@Override
	public Object findMenuSource(Menu menu) {
		IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();
		for (IWorkbenchWindow iWorkbenchWindow : workbenchWindows) {
			WorkbenchPage page = (WorkbenchPage) iWorkbenchWindow.getActivePage();
			IViewReference[] viewReferences = page.getViewReferences();
			for (IViewReference viewReference : viewReferences) {
				IWorkbenchPart workbenchPart = viewReference.getPart(false);
				if (workbenchPart != null) {
					PartSite site = (PartSite) workbenchPart.getSite();
					if (site != null) {
						Menu viewMenu = EclipseWorkbenchProvider.getProvider().getViewMenu(workbenchPart, viewReference,
								false);
						if (viewMenu != null && viewMenu.equals(menu)) {
							return workbenchPart;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public void fillElementEntry(ElementEntry result, Widget widget) {
		if (widget instanceof Text && widget == EclipseWorkbenchProvider.getProvider().getQuickAccess()) {
			result.getElement().setDescription("quick-access");
		}
	}

	@Override
	public WindowUIElement getShell(Shell shell, boolean alwaysFindLeaf) {
		if (shell == null) {
			return null;
		}
		if (!shell.isVisible()) {
			return null;
		}
		String pattern = shell.getText();
		SWTUIElement wrappedShell = getSWTPlayer().wrap(shell);

		// Check this is SDK window and only one window.
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		int ind = 0;
		boolean found = false;
		for (IWorkbenchWindow iWorkbenchWindow : windows) {
			if (shell.equals(iWorkbenchWindow.getShell())) {
				int sdkIndex = pattern.indexOf("- Eclipse SDK");
				if (sdkIndex != -1) {
					pattern = ".*- Eclipse SDK";
				}
				found = true;
				break; // We found correct window
			}
			ind++;
		}
		if (!found) {
			return null;
		}

		// Iterate shell parents
		Composite parent = shell.getParent();
		pattern = pattern.trim();
		WindowUIElement win = null;
		if (parent == null) {
			recorder.setControls(wrappedShell.getModel());
			win = ind == 0 ? recorder.eclipseWindow() : recorder.eclipseWindow(ind);
		} else {
			IWindowProvider parentWindow = getSWTLocator().getShell((Shell) parent, alwaysFindLeaf);
			if (parentWindow == null) {
				parentWindow = recorder;
			}
			recorder.setControls(wrappedShell.getModel());

			// by title text
			if (pattern.length() != 0) {
				int i = getSWTLocator().calculateIndex(wrappedShell, null);
				if (i != 0) {
					win = parentWindow.window(pattern, i);
				} else {
					win = parentWindow.window(pattern);
				}
			}

			// by -class
			if (win == null) {
				String className = GetWindowUtil.getWindowClassName(shell);
				if (className != null) {
					SWTUIElement[] children = getSWTPlayer().children.collectFor(getSWTPlayer().wrap(parent), null,
							true,
							Shell.class);
					int index = IndexUtil.calcIndexFor(shell, children, GetWindowUtil.byClass(className));

					if (index != 0) {
						win = parentWindow.classedWindow(className, index);
					} else {
						win = parentWindow.classedWindow(className);
					}
				}
			}

			// by -from
			if (win == null) {
				String methodName = GetWindowUtil.getShellCreationMethodName(shell);
				if (methodName != null) {
					SWTUIElement[] children = getSWTPlayer().children.collectFor(getSWTPlayer().wrap(parent), null,
							true,
							Shell.class);
					int index = IndexUtil.calcIndexFor(shell, children, GetWindowUtil.byFrom(methodName));

					if (index != 0) {
						win = parentWindow.fromedWindow(methodName, index);
					} else {
						win = parentWindow.fromedWindow(methodName);
					}
				}
			}

			if (win == null) {
				throw new RuntimeException("Failed to locate shell element:" + shell.toString());
			}
		}

		ElementEntry window = new ElementEntry(win.getElement());
		if (parent != null) {
			window.set(SWTWidgetLocator.ATTR_TEXT, pattern);
		}
		window.getElement().setDescription(pattern);
		SWTRecordingHelper.getHelper().put(wrappedShell, window);
		return new WindowUIElement(window.getElement(), recorder);
	}

	public PartUIElement findPartElement(IWorkbenchPart part, boolean alwaysFindLeaf) {
		SWTUIElement wrap = getSWTPlayer().wrap(part);
		ElementEntry result = SWTRecordingHelper.getHelper().get(wrap);
		result = getSWTLocator().checkTextFieldChange(wrap, result);
		if (result != null && !alwaysFindLeaf) {
			return new PartUIElement(result.getElement(), getRecorder());
		}
		IWorkbenchWindow window = part.getSite().getWorkbenchWindow();
		if (part instanceof IViewPart) {
			IViewPart viewPart = (IViewPart) part;
			String title = ((WorkbenchPart) viewPart).getPartName();
			WindowUIElement shellElement = getSWTLocator().getShell(window.getShell(), false);
			if (shellElement == null) {
				return null;
			}
			getRecorder().setControls(SWTModelMapper.map(wrap));
			int ViewIdx = calcViewIndex(part);
			if (ViewIdx > 0) {
				result = new ElementEntry(shellElement.view(title, ViewIdx).getElement());
			} else {
				result = new ElementEntry(shellElement.view(title).getElement());
			}
			result.set(SWTWidgetLocator.ATTR_TEXT, title);
		}
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			String title = ((WorkbenchPart) editorPart).getPartName();
			// TODO (e4 support): check findElement() from swt locator usage
			FindResult element = getSWTLocator().findElement(getSWTPlayer().wrap(window.getShell()), false, false,
					false);
			WindowUIElement windowElement = new WindowUIElement(element.element, getRecorder());
			getRecorder().setControls(SWTModelMapper.map(wrap));

			EditorLocation location = getEditorLocation(editorPart);
			result = new ElementEntry(
					windowElement.editor(location.title, location.label, location.index).getElement());

			result.set(SWTWidgetLocator.ATTR_TEXT, title);
		}
		if (result != null) {
			SWTRecordingHelper.getHelper().put(wrap, result);
			return new PartUIElement(result.getElement(), getRecorder());
		}
		return null;
	}

	private static class EditorLocation {
		public String title;
		public String label;
		public Integer index;

		public EditorLocation(String title, String label, Integer index) {
			this.title = title;
			this.label = label;
			this.index = index;
		}
	}

	private EditorLocation getEditorLocation(IEditorPart editor) {
		IEditorReference[] refs = editor.getSite().getWorkbenchWindow().getActivePage().getEditorReferences();

		String name = ((WorkbenchPart) editor).getPartName();
		String id = editor.getSite().getId();
		String label = editor.getSite().getWorkbenchWindow().getWorkbench().getEditorRegistry().findEditor(id)
				.getLabel();

		// -- locate by name only

		int counter = 0;
		for (IEditorReference ref : refs) {
			if (ref.getPartName() != null && ref.getPartName().equals(name))
				++counter;
		}
		if (counter < 2) {
			return new EditorLocation(name, null, null);
		}

		// -- by name, editor type and maybe by index

		counter = 0;
		int index = 0; // this will select first editor
		int globalIndex = 0;
		boolean typeDiversity = false;
		for (int i = 0; i < refs.length; ++i) {
			IEditorReference ref = refs[i];
			if (ref.getPartName() != null && ref.getPartName().equals(name)) {
				if (ref.getId() != null && ref.getId().equals(id)) {
					if (editor == ref.getEditor(false)) {
						index = counter;
						globalIndex = i;
					}
					++counter;
				} else
					typeDiversity = true;
			}
		}
		if (counter < 2)
			return new EditorLocation(name, label, null);

		// if all editors of a resource are of same type, use only name and
		// index
		if (!typeDiversity)
			return new EditorLocation(name, null, index == 0 ? null : index);

		return new EditorLocation(name, globalIndex == 0 ? null : label, index == 0 || globalIndex == 0 ? null : index);
	}

	private int calcViewIndex(IWorkbenchPart part) {
		String title = ((WorkbenchPart) part).getPartName();
		int currIdx = 0;
		IViewReference[] views = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getViewReferences();

		for (IViewReference iViewRef : views) {
			String label = iViewRef.getPartName();

			if (label != null && label.equals(title)) {
				if (iViewRef.getPart(false).equals(part))
					return currIdx;
				currIdx++;
			}
		}
		return -1;
	}

	private boolean checkForViewEditor(Control control, Shell shell, SWTUIElement lowerParent) {
		IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();
		if (lowerParent instanceof WorkbenchUIElement) {
			IWorkbenchPage lowerParentPage = ((WorkbenchUIElement) lowerParent).getReference().getPage();
			for (IWorkbenchWindow iWorkbenchWindow : workbenchWindows) {
				Shell wshell = iWorkbenchWindow.getShell();
				if (wshell == shell) {
					WorkbenchPage page = (WorkbenchPage) iWorkbenchWindow.getActivePage();
					if (page.equals(lowerParentPage)) {
						IViewReference[] views = page.getViewReferences();
						for (IViewReference iViewPart : views) {
							Control composite = ((WorkbenchPartReference) iViewPart).getPane().getControl();
							if (control.equals(composite)) {
								return true;
							}
						}
						IEditorReference[] editors = page.getEditorReferences();
						for (IEditorReference iEditorPart : editors) {
							Control composite = ((WorkbenchPartReference) iEditorPart).getPane().getControl();
							if (control.equals(composite)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private TeslaRecorder getRecorder() {
		return recorder;
	}

	private SWTUIPlayer getSWTPlayer() {
		return swtPlayer;
	}

	private SWTWidgetLocator getSWTLocator() {
		return swtLocator;
	}

}
