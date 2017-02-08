/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ui.panels.assertion;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.rcptt.internal.ui.Messages;
import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.ui.panels.assertion.tree.AssertionTreeContentProvider;
import org.eclipse.rcptt.ui.panels.assertion.tree.AssertionTreeLabelProvider;
import org.eclipse.rcptt.ui.panels.assertion.tree.AssertionTreeModel;
import org.eclipse.rcptt.ui.panels.assertion.tree.AssertionTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class ControlHierarchyView {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.rcptt.ui.panels.assertion.ControlHierarchyView"; //$NON-NLS-1$

	private final AutLaunch aut;

	private AssertionTreeViewer viewer;

	private boolean isUpdated = false;

	private AssertionTreeContentProvider contentProvider;

	private Stack<AssertionTreeModel> parentsStack;

	private IJobChangeListener listener;

	private AtomicBoolean canExpanding = new AtomicBoolean(false);

	public ControlHierarchyView(AutLaunch aut) {
		this.aut = aut;
	}

	public void createPartControl(Composite parent) {

		final Composite treeComposite = new Composite(parent, SWT.NONE);
		final TreeColumnLayout layout = new TreeColumnLayout();
		treeComposite.setLayout(layout);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(treeComposite);

		final Tree tree = new Tree(treeComposite, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setHeaderVisible(true);

		viewer = new AssertionTreeViewer(tree);

		viewer.setComparer(new IElementComparer() {
			@Override
			public int hashCode(Object element) {
				if (element instanceof AssertionTreeModel) {
					AssertionTreeModel model = (AssertionTreeModel) element;
					return model.getUIElement().getId().hashCode();
				}
				return element.hashCode();
			}

			@Override
			public boolean equals(Object object1, Object object2) {
				if (object1 instanceof AssertionTreeModel && object2 instanceof AssertionTreeModel) {
					AssertionTreeModel model1 = (AssertionTreeModel) object1;
					AssertionTreeModel model2 = (AssertionTreeModel) object2;
					if (model1.getUIElement() == null || model2.getUIElement() == null) {
						return model1.getUIElement() == model2.getUIElement();
					}
					if (model1.getUIElement().getId() == null || model2.getUIElement().getId() == null) {
						return model1.getUIElement().getId() == model2.getUIElement().getId();
					}
					return model1.getUIElement().getId().equals(model2.getUIElement().getId());
				}
				return Objects.equals(object1, object2);
			}
		});

		layout.setColumnData(createNameColumn().getColumn(), new ColumnWeightData(10));

		contentProvider = new AssertionTreeContentProvider();

		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new AssertionTreeLabelProvider());

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {

						Object object = iterator.next();

						if (object instanceof AssertionTreeModel) {
							AssertionTreeModel model = (AssertionTreeModel) object;

							UIHierarchyResponseUtilities.highlightWidget(getAut(), model.getUIElement());
							UIHierarchyResponseUtilities.updateAssertionPanelWindow(getAut(), model.getUIElement());

							isUpdated = true;
						}
					}
				}
			}
		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {

						Object object = iterator.next();

						if (object instanceof AssertionTreeModel) {
							AssertionTreeModel model = (AssertionTreeModel) object;
							viewer.setExpandedState(model, !viewer.getExpandedState(model));
						}
					}
				}
			}
		});

		AssertionTreeModel input = new AssertionTreeModel(aut,
				UIHierarchyResponseUtilities.getUIHierarchyElement(aut, null).getUiElement());
		viewer.setInput(input);

		listener = new IJobChangeListener() {
			@Override
			public void aboutToRun(IJobChangeEvent event) {
			}

			@Override
			public void awake(IJobChangeEvent event) {
			}

			@Override
			public void done(IJobChangeEvent event) {
				if (!canExpanding.get()) {
					canExpanding.set(true);
				}
				if (null != parentsStack && !parentsStack.isEmpty()) {
					expand(parentsStack.pop());
				}
			}

			@Override
			public void running(IJobChangeEvent event) {
			}

			@Override
			public void scheduled(IJobChangeEvent event) {
			}

			@Override
			public void sleeping(IJobChangeEvent event) {
			}
		};
		contentProvider.addUpdateCompleteListener(listener);

	}

	protected TreeViewerColumn createNameColumn() {
		final TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText(Messages.ControlHierarchyView_NameColumnText);
		column.getColumn().setToolTipText(Messages.ControlHierarchyView_NameColumnToolTip);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object object) {
				if (object instanceof UIElement) {
					UIElement uiElement = (UIElement) object;
					return UIHierarchyResponseUtilities.getUIElementCaption(uiElement);
				}
				return null;
			}
		});
		return column;
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public AutLaunch getAut() {
		return this.aut;
	}

	public void expandToElement(Element element) {
		if (isUpdated) {
			isUpdated = false;
			return;
		}

		UIElement uiElement = ProtocolFactory.eINSTANCE.createUIElement();

		uiElement.setId(element.getId());
		uiElement.setKind(element.getKind());

		uiElement = UIHierarchyResponseUtilities.getUIHierarchyElement(aut, uiElement).getUiElement();

		AssertionTreeModel model = new AssertionTreeModel(getAut(), uiElement);

		parentsStack = new Stack<AssertionTreeModel>();
		parentsStack.push(model);
		setParentsForExpand(model, parentsStack);

		if (canExpanding.get()) {
			expand(parentsStack.pop());
		}
	}

	private void expand(AssertionTreeModel model) {
		if (parentsStack.empty()) {
			viewer.setSelection(new StructuredSelection(model), true);
		} else {
			viewer.setExpandedState(model, true);
		}
	}

	private void setParentsForExpand(AssertionTreeModel model, Stack<AssertionTreeModel> parents) {
		if (null == model || null == model.getUIElement()) {
			return;
		}

		AssertionTreeModel parent = (AssertionTreeModel) model.getParent();
		if (null == parent || null == parent.getUIElement()) {
			return;
		}

		if (!viewer.isItemExists(parent)) {
			parents.push(parent);
			setParentsForExpand(parent, parents);
			return;
		}

		if (!viewer.isItemExists(model)) {
			parents.push(parent);
			setParentsForExpand(parent, parents);
		}

		if (!viewer.getExpandedState(parent)) {
			viewer.setExpandedState(parent, true);
		}
	}
}