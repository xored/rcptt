package org.eclipse.rcptt.ui.panels.assertion.tree;

import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.DeferredTreeContentManager;

public class AssertionTreeContentProvider implements ITreeContentProvider {

	private DeferredTreeContentManager manager = null;

	private Object input = null;

	public AssertionTreeContentProvider() {
	}

	public void addUpdateCompleteListener(IJobChangeListener listener) {
		manager.addUpdateCompleteListener(listener);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		input = newInput;
		manager = new DeferredTreeContentManager((AbstractTreeViewer) viewer);
	}

	@Override
	public Object[] getElements(Object object) {
		return getChildren(object);
	}

	@Override
	public Object[] getChildren(Object object) {
		return manager.getChildren(object);
	}

	@Override
	public Object getParent(Object object) {
		if (object instanceof AssertionTreeModel) {
			return input;
		}

		AssertionTreeModel model = (AssertionTreeModel) object;
		if (null == model || null == model.getUIElement()) {
			return input;
		}
		AssertionTreeModel parent = (AssertionTreeModel) model.getParent();
		if (null == parent || null == parent.getUIElement()) {
			return input;
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object object) {
		return manager.mayHaveChildren(object);
	}
}