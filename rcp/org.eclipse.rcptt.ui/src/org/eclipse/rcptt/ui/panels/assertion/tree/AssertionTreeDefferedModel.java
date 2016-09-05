package org.eclipse.rcptt.ui.panels.assertion.tree;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

public class AssertionTreeDefferedModel implements IDeferredWorkbenchAdapter {

	private final AssertionTreeModel model;
	private Object[] cache;

	public AssertionTreeDefferedModel(AssertionTreeModel model) {
		this.model = model;
	}

	@Override
	public Object[] getChildren(Object object) {
		return getCheckedChildren();
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return null;
	}

	@Override
	public String getLabel(Object object) {
		return model.getLabel();
	}

	@Override
	public Object getParent(Object object) {
		return model.getParent();
	}

	@Override
	public void fetchDeferredChildren(Object object, IElementCollector collector, IProgressMonitor monitor) {
		Object[] children = getChildren(object);
		collector.add(children, monitor);
	}

	@Override
	public boolean isContainer() {
		return getCheckedChildren().length > 0;
	}

	@Override
	public ISchedulingRule getRule(Object object) {
		return null;
	}

	private Object[] getCheckedChildren() {
		if (cache == null) {
			cache = model.getChildren();
		}
		return cache;
	}

}