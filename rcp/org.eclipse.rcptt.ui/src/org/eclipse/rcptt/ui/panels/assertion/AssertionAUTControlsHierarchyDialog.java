package org.eclipse.rcptt.ui.panels.assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy;
import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState;
import org.eclipse.rcptt.core.ecl.core.model.Q7CoreFactory;
import org.eclipse.rcptt.internal.ui.Messages;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.ui.panels.MenuToolbar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

// TODO: other package

public class AssertionAUTControlsHierarchyDialog extends TrayDialog {

	private final AutLaunch aut;

	private final Shell parentShell;

	private Composite treeViewerComposite = null;

	private TreeViewer viewer;

	private AUTControlsHierarchyContentProvider contentProvider = null;

	public AssertionAUTControlsHierarchyDialog(AutLaunch aut, Shell parentShell) {
		super(parentShell);
		this.aut = aut;
		this.parentShell = parentShell;
		setShellStyle(SWT.RESIZE | SWT.CLOSE);
		contentProvider = new AUTControlsHierarchyContentProvider(this.aut);
	}

	class AUTControlsHierarchyContentProvider implements ITreeContentProvider {

		private final AutLaunch aut;

		// <child_id, parent_id>
		private Map<String, String> hierarchy = new HashMap<String, String>();

		// <uiElement_id, uiElement>
		private Map<String, UIElement> uiElementsMap = new HashMap<String, UIElement>();

		public AUTControlsHierarchyContentProvider(AutLaunch aut) {
			this.aut = aut;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (!(inputElement instanceof UIElement)) {
				return null;
			}

			UIElement uiElement = (UIElement) inputElement;
			UIElement[] uiElements = getUIElements(uiElementsMap.get(hierarchy.get(uiElement.getId())));

			List<UIElement> list = new ArrayList<UIElement>(Arrays.asList(uiElements));
			list.remove(inputElement);

			uiElements = list.toArray(uiElements);

			return (UIElement[]) list.toArray(new UIElement[list.size()]);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (!(parentElement instanceof UIElement)) {
				return null;
			}
			UIElement uiElement = (UIElement) parentElement;
			return getUIElements(uiElement);
		}

		@Override
		public Object getParent(Object element) {
			if (!(element instanceof UIElement)) {
				return null;
			}
			UIElement uiElement = (UIElement) element;
			return hierarchy.get(uiElement.getId());
		}

		@Override
		public boolean hasChildren(Object element) {
			if (!(element instanceof UIElement)) {
				return false;
			}
			UIElement uiElement = (UIElement) element;
			return uiElement.isHasChildren();
		}

		public void clear() {
			hierarchy.clear();
			uiElementsMap.clear();

			AssertionAUTControlsHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
					.createAssertionAUTControlsHierarchy();

			assertionAUTControlsHierarchy.setState(AssertionAUTControlsHierarchyState.CLEAR);

			try {
				this.aut.execute(assertionAUTControlsHierarchy);
			} catch (Exception e) {
				Q7UIPlugin.log(e);
			}
		}

		public UIElement[] getUIElements(UIElement parent) {
			List<UIElement> list = new ArrayList<UIElement>();

			AssertionAUTControlsHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
					.createAssertionAUTControlsHierarchy();

			if (null == parent) {
				assertionAUTControlsHierarchy.setState(AssertionAUTControlsHierarchyState.GET_ROOT);
			} else {
				assertionAUTControlsHierarchy.setState(AssertionAUTControlsHierarchyState.GET_NODE);
				assertionAUTControlsHierarchy.setId(parent.getId());
			}

			try {
				Object childAUTObject = this.aut.execute(assertionAUTControlsHierarchy);
				if (childAUTObject instanceof UIHierarchyResponse) {
					UIHierarchyResponse response = (UIHierarchyResponse) childAUTObject;

					for (UIElement uiElement : response.getUiElements()) {

						if (null == parent) {
							hierarchy.put(uiElement.getId(), "-1");
						} else {
							hierarchy.put(uiElement.getId(), parent.getId());
						}

						uiElementsMap.put(uiElement.getId(), uiElement);

						System.out.println(uiElement.getId());
						System.out.println(uiElement.getKind());
						System.out.println(uiElement.isHasChildren());

						list.add(uiElement);
					}
				}
			} catch (Exception e) {
				Q7UIPlugin.log(e);
			}

			return (UIElement[]) list.toArray(new UIElement[list.size()]);
		}
	}

	@Override
	public boolean close() {
		contentProvider.clear();
		return super.close();
	}

	@Override
	protected Shell getParentShell() {
		return parentShell;
	}

	@Override
	protected Control createContents(Composite parent) {
		initializeDialogUnits(parent);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout());

		final Composite toolbarComposite = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(toolbarComposite);
		toolbarComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final MenuToolbar menu = new MenuToolbar() {
			@Override
			protected void fill(MenuManager manager) {
				manager.add(new Action(Messages.AssertionPanelWindow_CloseActionName) {
					@Override
					public void run() {
						close();
					}
				});
			};
		};

		menu.create(toolbarComposite).setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

		createTreeViewer(composite).setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		UIElement el = ProtocolFactory.eINSTANCE.createUIElement();
		el.setId("-1");
		el.setKind("root");
		el.setDescription("root");
		el.setHasChildren(true);

		viewer.setInput(el);

		return composite;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.AssertionAUTControlsHierarchyDialog_Title);
		newShell.setLayout(new FillLayout());
		GridLayoutFactory.fillDefaults().equalWidth(true).spacing(0, 0).margins(0, 0).applyTo(newShell);
	}

	@Override
	protected Point getInitialSize() {
		Rectangle bounds = getShell().getDisplay().getBounds();
		int height = bounds.height / 2 - bounds.height / 10;
		return new Point(450, height);
	}

	protected TreeViewerColumn createNameColumn() {
		final TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText(Messages.AssertionAUTControlsHierarchyDialog_ColumnNameName);
		column.getColumn().setToolTipText(Messages.AssertionAUTControlsHierarchyDialog_ColumnNameToolTip);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof UIElement) {
					UIElement uiElement = (UIElement) element;
					if (null == uiElement.getName()) {
						return uiElement.getKind() + " (" + uiElement.getDescription() + ")";
					}
					return uiElement.getKind() + " : " + uiElement.getName() + " (" + uiElement.getDescription() + ")";
				}
				return null;
			}
		});
		return column;
	}

	protected Rectangle getDisplayBounds(Point loc) {
		Monitor[] ms = getShell().getDisplay().getMonitors();
		if (ms.length > 1) {
			Rectangle tmp;
			for (int i = 0; i < ms.length; i++) {
				tmp = ms[i].getBounds();
				if (tmp.contains(loc)) {
					return tmp;
				}
			}
		}
		return getShell().getDisplay().getBounds();
	}

	protected Control createTreeViewer(Composite parent) {
		treeViewerComposite = new Composite(parent, SWT.NONE);
		treeViewerComposite.setLayout(new GridLayout());

		final Composite treeComposite = new Composite(treeViewerComposite, SWT.NONE);

		final TreeColumnLayout layout = new TreeColumnLayout();
		treeComposite.setLayout(layout);
		treeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Tree tree = new Tree(treeComposite, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setHeaderVisible(true);

		viewer = new TreeViewer(tree);

		layout.setColumnData(createNameColumn().getColumn(), new ColumnWeightData(10));

		viewer.setContentProvider(contentProvider);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
						UIElement uiElement = (UIElement) iterator.next();

						AssertionAUTControlsHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
								.createAssertionAUTControlsHierarchy();

						assertionAUTControlsHierarchy.setState(AssertionAUTControlsHierarchyState.HIGHLIGHT);
						assertionAUTControlsHierarchy.setId(uiElement.getId());


						try {
							getAut().execute(assertionAUTControlsHierarchy);
						} catch (Exception e) {
							Q7UIPlugin.log(e);
						}

						// TODO: open AssertionPanelWindow by doubleclick

						// TODO: add commands recordingSupport
					}
				}
			}
		});

		return treeViewerComposite;
	}

	public AutLaunch getAut() {
		return this.aut;
	}

}
