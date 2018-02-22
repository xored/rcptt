package org.eclipse.rcptt.tesla.workbench.processors;

import static org.eclipse.rcptt.tesla.swt.util.GetWindowUtil.getShellCreationMethodName;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.tesla.core.Q7WaitUtils;
import org.eclipse.rcptt.tesla.core.context.ContextManagement.Context;
import org.eclipse.rcptt.tesla.core.info.AdvancedInformation;
import org.eclipse.rcptt.tesla.core.info.Q7WaitInfoRoot;
import org.eclipse.rcptt.tesla.core.protocol.Assert;
import org.eclipse.rcptt.tesla.core.protocol.BooleanResponse;
import org.eclipse.rcptt.tesla.core.protocol.Click;
import org.eclipse.rcptt.tesla.core.protocol.ClickAboutMenu;
import org.eclipse.rcptt.tesla.core.protocol.ClickPreferencesMenu;
import org.eclipse.rcptt.tesla.core.protocol.Close;
import org.eclipse.rcptt.tesla.core.protocol.ElementCommand;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GetPropertyValue;
import org.eclipse.rcptt.tesla.core.protocol.IElementProcessorMapper;
import org.eclipse.rcptt.tesla.core.protocol.IsDirty;
import org.eclipse.rcptt.tesla.core.protocol.Maximize;
import org.eclipse.rcptt.tesla.core.protocol.Minimize;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;
import org.eclipse.rcptt.tesla.core.protocol.Restore;
import org.eclipse.rcptt.tesla.core.protocol.Save;
import org.eclipse.rcptt.tesla.core.protocol.SelectCommand;
import org.eclipse.rcptt.tesla.core.protocol.SelectData;
import org.eclipse.rcptt.tesla.core.protocol.SelectResponse;
import org.eclipse.rcptt.tesla.core.protocol.ShowTabList;
import org.eclipse.rcptt.tesla.core.protocol.TypeAction;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.RawFactory;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;
import org.eclipse.rcptt.tesla.core.protocol.raw.ResponseStatus;
import org.eclipse.rcptt.tesla.core.ui.Editor;
import org.eclipse.rcptt.tesla.core.ui.UiFactory;
import org.eclipse.rcptt.tesla.core.ui.View;
import org.eclipse.rcptt.tesla.core.ui.Widget;
import org.eclipse.rcptt.tesla.internal.core.AbstractTeslaClient;
import org.eclipse.rcptt.tesla.internal.core.TeslaCore;
import org.eclipse.rcptt.tesla.internal.core.info.InfoUtils;
import org.eclipse.rcptt.tesla.internal.core.info.InfoUtils.Node;
import org.eclipse.rcptt.tesla.internal.core.processing.ElementGenerator;
import org.eclipse.rcptt.tesla.internal.core.processing.ITeslaCommandProcessor;
import org.eclipse.rcptt.tesla.internal.ui.SWTElementMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTModelMapperExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTUIPlayerExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTModelMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.player.UIJobCollector;
import org.eclipse.rcptt.tesla.internal.ui.processors.SWTUIProcessor;
import org.eclipse.rcptt.tesla.jface.TeslaCellEditorManager;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.rcptt.tesla.swt.events.TeslaEventManager;
import org.eclipse.rcptt.tesla.ui.SWTTeslaActivator;
import org.eclipse.rcptt.tesla.workbench.player.PerspectiveUIElement;
import org.eclipse.rcptt.tesla.workbench.player.WorkbenchUIElement;
import org.eclipse.rcptt.tesla.workbench.player.WorkbenchUIPlayer;
import org.eclipse.rcptt.tesla.workbench.player.WorkbenchUIPlayerExtension;
import org.eclipse.rcptt.tesla.workbench.provider.EclipseWorkbenchProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.handlers.IHandlerService;

public class WorkbenchUIProcessor implements ITeslaCommandProcessor, ISWTModelMapperExtension {

	private static final ElementKind[] allSelectors = {
			ElementKind.EclipseWindow,
			ElementKind.View,
			ElementKind.Editor,
			ElementKind.QuickAccess,
			ElementKind.Button
	};

	private static final EClass[] supportedCommands = {
			ProtocolPackage.Literals.CLICK,
			ProtocolPackage.Literals.CLOSE,
			ProtocolPackage.Literals.TYPE_ACTION,
			ProtocolPackage.Literals.MAXIMIZE,
			ProtocolPackage.Literals.MINIMIZE,
			ProtocolPackage.Literals.RESTORE,
			ProtocolPackage.Literals.SAVE,
			ProtocolPackage.Literals.IS_DIRTY,
			ProtocolPackage.Literals.CLICK_ABOUT_MENU,
			ProtocolPackage.Literals.CLICK_PREFERENCES_MENU,
			ProtocolPackage.Literals.SHOW_TAB_LIST
	};

	private static final Set<String> SKIP_ACTIVATION_FOR_SHELLS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("ContextInformationPopup.createContextInfoPopup()")));

	private AbstractTeslaClient client;
	private String id;
	private Display display;

	private ISWTUIPlayerExtension extension;
	private SWTUIProcessor swtProcessor;
	private SWTUIPlayer swtPlayer;
	private WorkbenchUIPlayer workbenchPlayer;

	@Override
	public int getPriority() {
		return 50;
	}

	@Override
	public String getFeatureID() {
		return "org.eclipse.rcptt.tesla.workbench";
	}

	@Override
	public void initialize(AbstractTeslaClient client) {
		this.client = client;
		this.id = client.getID();

		this.display = PlatformUI.getWorkbench().getDisplay();
		getSWTProcessor().setDisplay(display);

		this.extension = new WorkbenchUIPlayerExtension();
		SWTUIPlayer.addExtension(extension);
	}

	@Override
	public void terminate() {
		this.client = null;
		this.id = null;

		this.display = null;

		SWTUIPlayer.removeExtension(extension);
		this.extension = null;

		this.swtProcessor = null;
		this.swtPlayer = null;
		this.workbenchPlayer = null;
	}

	@Override
	public boolean canProceed(Context context, Q7WaitInfoRoot info) {
		return true;
	}

	@Override
	public PreExecuteStatus preExecute(Command command, PreExecuteStatus previousStatus, Q7WaitInfoRoot info) {
		if (command instanceof ElementCommand) {
			if (!(command instanceof GetPropertyValue)) {
				final ElementCommand cmd = (ElementCommand) command;
				if (!activateViewEditor(cmd.getElement(), false, info)) {
					return new PreExecuteStatus(false);
				}
			}
		} else if (command instanceof SelectCommand) {
			final SelectCommand selectCmd = (SelectCommand) command;
			final SelectData data = selectCmd.getData();
			// XXX no need activate parent part if kind is window
			if (data.getParent() != null
					&& !data.getKind().equals(ElementKind.Window.name())) {
				boolean onlyOpen = !(data.getKind().equals(ElementKind.Menu.name())
						|| data.getKind().equals(ElementKind.Item.name()));
				if (!activateViewEditor(data.getParent(), onlyOpen, info)) {
					return new PreExecuteStatus(false);
				}
			}
		}
		return null;
	}

	public boolean activateViewEditor(final Element cmdElement,
			boolean onlyOpen, Q7WaitInfoRoot info) {
		final SWTUIElement element = getMapper().get(cmdElement);
		if (element != null) {
			return activateViewEditor(element, onlyOpen, info);
		}
		return true;
	}

	public boolean activateViewEditor(final SWTUIElement element,
			boolean onlyOpen, Q7WaitInfoRoot info) {
		SWTUIElement sh = SWTUIPlayer.getShell(element);
		if (sh == null || sh.widget == null) {
			TeslaCore.log("Failed to locate shell for:"
					+ ((element != null) ? element.toString() : ""));
			return true;
		}
		final Shell shell = (Shell) sh.widget;
		if (SKIP_ACTIVATION_FOR_SHELLS.contains(getShellCreationMethodName(shell))) {
			return true;
		}
		Shell activeShell = TeslaEventManager.getActiveShell();
		if (activeShell != shell) {
			Q7WaitUtils.updateInfo("shell.activate",
					shell.getClass().getName(), info);
			getSWTPlayer().exec("Activate shell", new Runnable() {
				@Override
				public void run() {
					// Do deactivate for all other shells

					Display display = shell.getDisplay();
					Shell[] shells = display.getShells();
					for (Shell sh : shells) {
						if (!shell.equals(sh)) {
							if (sh.isVisible()) {
								// getPlayer().getEvents().sendEvent(sh,
								// SWT.FocusOut);
								getSWTPlayer().getEvents().sendEvent(sh,
										SWT.Deactivate);
							}
						}
					}

					shell.setActive();
					// shell.forceActive();
					TeslaEventManager.setActiveShell(shell);

					// seems like for linux systems event triggered by shell.setActive().
					// the following code prevent triggering of the same event twice.
					if (!org.eclipse.jface.util.Util.isLinux()) {
						getSWTPlayer().getEvents().sendEvent(shell, SWT.Activate);
					}

				}
			});
			return false;
		}

		List<SWTUIElement> parentsList = getSWTPlayer().getParentsList(element);
		parentsList.add(element);
		for (SWTUIElement e : parentsList) {
			final GenericElementKind kind = e.getKind();
			if (kind.is(ElementKind.View) || kind.is(ElementKind.Editor)) {
				if (e instanceof WorkbenchUIElement) {
					final IWorkbenchPartReference reference = ((WorkbenchUIElement) e)
							.getReference();
					final boolean visible = EclipseWorkbenchProvider.getProvider().isVisible(reference);
					if (!visible) {
						IWorkbenchPage page = reference.getPage();
						if (!EclipseWorkbenchProvider.getProvider().isActiveContainsView(page, reference)) {
							return true;
						}
						Q7WaitUtils.updateInfo("view.activate",
								reference.getId(), info);
						e.click();
						return false;
					}
					if (!onlyOpen) {
						IWorkbenchPart part = reference.getPart(true);
						if (part != null) {
							IWorkbenchPage page = reference.getPage();
							IWorkbenchPart activePart = page.getActivePart();
							if (!(part.equals(activePart))) {
								page.activate(part);
								part.setFocus();
								break;
							}
							final CellEditor[] editors = TeslaCellEditorManager
									.getInstance().getEditors();
							if (editors.length == 0) {
								if (part != null && !part.equals(activePart)) {
									if (!(part.equals(activePart))) {
										try {
											page.activate(part);
											part.setFocus();
										} catch (RuntimeException re) {
											TeslaCore.log(re);
											return true;
										}
									}
									Q7WaitUtils.updateInfo("editor.activate",
											reference.getId(), info);
									return false; // lets do it in next
									// cycle
								}
							}
						}
					}
				}
				break;
			}
		}
		return true;
	}

	@Override
	public boolean isSelectorSupported(final String kind) {
		for (final ElementKind e : allSelectors) {
			if (e.name().equals(kind)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isCommandSupported(Command cmd) {
		final EClass cl0 = cmd.eClass();
		for (final EClass cl : supportedCommands) {
			if (cl0.equals(cl)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Response executeCommand(Command command, IElementProcessorMapper mapper) {
		if (command.eClass().getEPackage().equals(ProtocolPackage.eINSTANCE)) {
			switch (command.eClass().getClassifierID()) {
			case ProtocolPackage.CLICK:
				return handleClick((Click) command);
			case ProtocolPackage.CLOSE:
				return handleSendClose((Close) command);
			case ProtocolPackage.TYPE_ACTION:
				return handleTypeAction((TypeAction) command);
			case ProtocolPackage.MINIMIZE:
				return handleMinimize((Minimize) command);
			case ProtocolPackage.MAXIMIZE:
				return handleMaximize((Maximize) command);
			case ProtocolPackage.RESTORE:
				return handleRestore((Restore) command);
			case ProtocolPackage.SAVE:
				return handleSave((Save) command);
			case ProtocolPackage.IS_DIRTY:
				return handleIsDirty((IsDirty) command);
			case ProtocolPackage.CLICK_ABOUT_MENU:
				return handleClickAboutMenu((ClickAboutMenu) command);
			case ProtocolPackage.CLICK_PREFERENCES_MENU:
				return handleClickPreferencesMenu((ClickPreferencesMenu) command);
			case ProtocolPackage.SHOW_TAB_LIST:
				return handleShowTabList((ShowTabList) command);
			default:
				return getSWTProcessor().executeCommand(command, mapper);
			}
		}
		return null;
	}

	private Response handleClick(final Click command) {
		final SWTUIElement element = getMapper().get(command.getElement());
		final Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			if (canClickView(element)) {
				getSWTPlayer().click(element, command.isDefault(), false,
						command.isArrow());
			} else {
				response.setMessage(NLS
						.bind(TeslaSWTMessages.SWTUIProcessor_CannotClick_PerspectiveNotContainsView,
								element.getText()));
				response.setStatus(ResponseStatus.FAILED);
				return response;
			}
		}
		if (ElementKind.Perspective.toString().equals(
				command.getElement().getKind())) {
			getWorkbenchPlayer().setPerspective(command.getElement().getId());
		}
		return response;
	}

	private boolean canClickView(SWTUIElement w) {
		if (w instanceof WorkbenchUIElement) {
			IWorkbenchPartReference reference = ((WorkbenchUIElement) w)
					.getReference();
			return EclipseWorkbenchProvider.getProvider().canClickView(reference);
		}
		return true;
	}

	private Response handleSendClose(final Close command) {
		final SWTUIElement element = getMapper().get(command.getElement());
		if (element != null && element instanceof WorkbenchUIElement) {
			getWorkbenchPlayer().close(element);
			getMapper().remove(command.getElement());
			return RawFactory.eINSTANCE.createResponse();
		}
		return null;
	}

	private Response handleRestore(Restore command) {
		SWTUIElement element = getMapper().get(command.getElement());
		Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			getWorkbenchPlayer().restore(element);
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleMaximize(Maximize command) {
		SWTUIElement element = getMapper().get(command.getElement());
		Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			if (element instanceof WorkbenchUIElement) {
				getSWTPlayer().click(element);
			}
			getWorkbenchPlayer().maximize(element);
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleMinimize(Minimize command) {
		SWTUIElement element = getMapper().get(command.getElement());
		Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			getWorkbenchPlayer().minimize(element);
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleSave(Save command) {
		SWTUIElement element = getMapper().get(command.getElement());
		Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			getWorkbenchPlayer().save(element);
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleIsDirty(IsDirty command) {
		SWTUIElement element = getMapper().get(command.getElement());
		BooleanResponse response = ProtocolFactory.eINSTANCE.createBooleanResponse();
		if (element != null) {
			response.setResult(getWorkbenchPlayer().isDirty(element));
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleTypeAction(final TypeAction command) {
		final SWTUIElement element = getMapper().get(command.getElement());
		final Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			getWorkbenchPlayer().typeAction(element, command.getActionId());
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleShowTabList(ShowTabList command) {
		SWTUIElement element = getMapper().get(command.getElement());
		Response response = RawFactory.eINSTANCE.createResponse();
		if (element != null) {
			getWorkbenchPlayer().showTabList(element);
		} else {
			response.setStatus(ResponseStatus.FAILED);
		}
		return response;
	}

	private Response handleClickPreferencesMenu(ClickPreferencesMenu command) {
		runCommand(ActionFactory.PREFERENCES);
		return RawFactory.eINSTANCE.createResponse();
	}

	private Response handleClickAboutMenu(ClickAboutMenu command) {
		runCommand(ActionFactory.ABOUT);
		return RawFactory.eINSTANCE.createResponse();
	}

	private void runCommand(final ActionFactory action) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbench workbench = PlatformUI.getWorkbench();
				IHandlerService service = (IHandlerService) workbench
						.getService(IHandlerService.class);
				try {
					service.executeCommand(action.getCommandId(), null);
				} catch (Exception e) {
					SWTTeslaActivator.log(e);
				}
			}
		});
	}

	@Override
	public SelectResponse select(SelectCommand command, ElementGenerator generator, IElementProcessorMapper mapper) {
		// TODO (e4 support): should we create a new collector instance or use an existing one?
		getSWTPlayer().getCollector().enable();

		final SelectData data = command.getData();
		final SWTUIElement result = getWorkbenchPlayer().select(
				new PlayerSelectionFilter(null, GenericElementKind
						.fromString(data.getKind()), data.getPattern(),
						null, data.getIndex(), null, null,
						data.getClassPattern())); // fill non-used params as null
		if (result != null) {
			final SelectResponse response = ProtocolFactory.eINSTANCE.createSelectResponse();
			response.getElements().add(getMapper().get(result));
			return response;
		}

		final SWTUIElement parent = getMapper().get(data.getParent());
		if (parent != null && isWorkbenchWindow(parent)
				&& data.getKind().equals(ElementKind.Button.toString())
				&& data.getPattern() != null
				&& data.getPattern().endsWith(" perspective")) {
			final String perspectiveName = data.getPattern().substring(0,
					data.getPattern().lastIndexOf(" perspective"));
			final PerspectiveUIElement perspective = new PerspectiveUIElement(perspectiveName);
			if (perspective.isPerspeciveFind()) {
				final Element element = RawFactory.eINSTANCE.createElement();
				element.setKind(perspective.getGenerationKind());
				element.setId(perspective.getPerspectiveId());
				final SelectResponse response = ProtocolFactory.eINSTANCE.createSelectResponse();
				response.getElements().add(element);
				return response;
			}
		}

		// return fail status only if it does not overwrite swt processor select result
		if (result == null && !getSWTProcessor().isSelectorSupported(data.getKind())) {
			final SelectResponse response = ProtocolFactory.eINSTANCE.createSelectResponse();
			response.setMessage(NLS.bind(
					TeslaSWTMessages.SWTUIProcessor_CannotFindControl,
					data.getKind(),
					(data.getPattern() != null ? data.getPattern() : data.getPath())));
			response.setStatus(ResponseStatus.FAILED);
			return response;
		}
		return null;
	}

	private boolean isWorkbenchWindow(SWTUIElement element) {
		if (element.getKind().is(ElementKind.Window)) {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell().equals(element.widget);
		}
		return false;
	}

	@Override
	public void collectInformation(AdvancedInformation information, Command lastCommand) {
		Node root = InfoUtils.newNode("swt.info").add(information);
		Element element = null;
		if (lastCommand instanceof ElementCommand) {
			element = ((ElementCommand) lastCommand).getElement();
		}
		else if (lastCommand instanceof Assert) {
			element = ((Assert) lastCommand).getElement();
		}
		else if (lastCommand instanceof SelectCommand) {
			element = ((SelectCommand) lastCommand).getData().getParent();
		}
		// Required element hierarchy
		SWTUIElement uiElement = null;
		if (element != null) {
			// TODO (e4 support) what mapper should be used here?
			uiElement = getSWTProcessor().getMapper().get(element);
		}
		if (uiElement != null) {
			processChildren(uiElement, root, new HashSet<SWTUIElement>());
		}
		Node root2 = root.child("eclipse.windows");

		// Use all eclipse windows as roots
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench()
				.getWorkbenchWindows();
		for (IWorkbenchWindow win : windows) {
			Set<SWTUIElement> processed = new HashSet<>();
			processChildren(win, root2, processed);
		}

		Node player = InfoUtils.newNode("swt.player").add(information);
		SWTUIPlayer swtuiPlayer = getSWTPlayer();
		UIJobCollector collector = swtuiPlayer.getCollector();
		List<Job> jobs = collector.getJobs();
		if (jobs.size() > 0) {
			Node jobsNode = player.child("ui.job.collector.jobs");
			for (Job job : jobs) {
				Node child = jobsNode.child("job:" + job.getName());
				child.property("job.class", job.getClass().getName());
			}
		}
	}

	private void processChildren(IWorkbenchWindow win, Node root2,
			Set<SWTUIElement> processed) {
		IWorkbenchPage[] pages = win.getPages();
		processed.add(getSWTPlayer().wrap(win.getShell()));
		for (IWorkbenchPage page : pages) {
			Node pageNode = root2.child("page:" + page.getLabel());
			IEditorReference[] references = page.getEditorReferences();
			if (references.length > 0) {
				Node editorsNode = pageNode.child("editors");
				for (IEditorReference ref : references) {
					processEditor(editorsNode, ref, processed);
				}
			}

			IViewReference[] viewReferences = page.getViewReferences();
			if (viewReferences.length > 0) {
				Node viewsNode = pageNode.child("views");
				for (IViewReference ref : viewReferences) {
					processView(viewsNode, ref, processed);
				}
			}
		}
		Shell[] shells = win.getShell().getShells();
		for (Shell shell : shells) {
			Node shellNode = root2.child("shells");
			processChildren(getSWTPlayer().wrap(shell), shellNode, processed);
		}
	}

	private void processEditor(Node editorsNode, IEditorReference ref,
			Set<SWTUIElement> processed) {
		Node child = editorsNode.child("editor:" + ref.getName());
		child.property("id", ref.getId());
		child.property("dirty", ref.isDirty());
		child.property("pinned", ref.isPinned());
		try {
			IEditorInput editorInput = ref.getEditorInput();
			if (editorInput != null) {
				child.property("editorInput.name", editorInput.getName());
			}
		}
		catch (Throwable t) {
			// Ignore
		}
		processChildren(getSWTPlayer().wrap(ref), child, processed);
	}

	private void processView(Node editorsNode, IViewReference ref,
			Set<SWTUIElement> processed) {
		String refName = "";
		try {
			refName = ref.getPartName();
		}
		catch (Exception e) {
			// seems disposed
			return;
		}
		Node child = editorsNode.child("view:" + refName);
		child.property("id", ref.getId());
		child.property("dirty", isViewDirty(ref));
		child.property("fastView", ref.isFastView());
		processChildren(getSWTPlayer().wrap(ref), child, processed);
	}

	/**
	 * Added because of QS-2489: CommonNavigator#isDirty() was throwing a NPE
	 *
	 * @return boolean or error message
	 */
	private static Object isViewDirty(IViewReference ref) {
		try {
			return ref.isDirty();
		}
		catch (NullPointerException e) {
			return e.toString();
		}
	}

	private void processChildren(SWTUIElement uiElement, Node root,
			Set<SWTUIElement> processed) {
		if (processed.contains(uiElement)) {
			return;
		}
		processed.add(uiElement);
		if (uiElement.isDisposed()) {
			return;
		}
		String text = uiElement.getText();
		Node nde = root;
		if (!uiElement.getKind().is(ElementKind.Unknown)) {
			nde = root.child(uiElement.getKind().name() + "("
					+ (text != null ? text.trim() : "") + ")");
			// Adds decorators
			for (ControlDecoration decorator : uiElement.getDecorators()) {
				if (decorator.isVisible())
					nde.child("ControlDecoration(" + decorator.getDescriptionText() + ")");
			}
		}
		try {
			SWTUIElement[] children = getSWTPlayer().children.collectFor(
					uiElement, null, false, null, null);
			for (SWTUIElement swtuiElement : children) {
				processChildren(swtuiElement, nde, processed);
			}
		} catch (Throwable e) {
			nde.property("ERROR", e.getMessage());
		}
	}

	private SWTUIProcessor getSWTProcessor() {
		if (swtProcessor == null) {
			swtProcessor = client.getProcessor(SWTUIProcessor.class);
		}
		return swtProcessor;
	}

	private SWTUIPlayer getSWTPlayer() {
		if (swtPlayer == null) {
			swtPlayer = getSWTProcessor().getPlayer();
		}
		return swtPlayer;
	}

	private WorkbenchUIPlayer getWorkbenchPlayer() {
		if (workbenchPlayer == null) {
			workbenchPlayer = new WorkbenchUIPlayer(getSWTPlayer());
		}
		return workbenchPlayer;
	}

	@Override
	public Widget mapExtraValues(SWTUIElement element, Widget result) {
		switch (element.getKind().kind) {
		case Editor:
			return fillEditor(element);
		case View:
			return fillView(element);
		}
		return result;
	}

	private static Widget fillView(SWTUIElement element) {
		View view = UiFactory.eINSTANCE.createView();
		if (element instanceof WorkbenchUIElement) {
			IWorkbenchPartReference reference = ((WorkbenchUIElement) element)
					.getReference();
			view.setTitle(PlayerTextUtils.unifyMultilines(reference.getPartName()));
			SWTModelMapper.fillControl(view, ((org.eclipse.swt.widgets.Control) element.unwrapWidget()));
		}
		return view;
	}

	private static Widget fillEditor(SWTUIElement element) {
		Editor editor = UiFactory.eINSTANCE.createEditor();
		if (element instanceof WorkbenchUIElement) {
			IWorkbenchPartReference reference = ((WorkbenchUIElement) element)
					.getReference();
			editor.setTitle(PlayerTextUtils.unifyMultilines(reference.getPartName()));
			editor.setDirty(reference.isDirty());
			editor.setActive(reference.getPage().getActivePartReference() == reference);
			SWTModelMapper.fillControl(editor, ((org.eclipse.swt.widgets.Control) element.unwrapWidget()));
		}
		return editor;
	}

	public SWTElementMapper getMapper() {
		return SWTElementMapper.getMapper(id);
	}

	@Override
	public void postSelect(Element element, IElementProcessorMapper mapper) {
	}

	@Override
	public boolean isInactivityRequired() {
		return false;
	}

	@Override
	public void clean() {
	}

	@Override
	public void checkHang() {
	}

	@Override
	public void notifyUI() {
	}

}
