package org.eclipse.rcptt.ui.panels.assertion.tree;

import org.eclipse.core.runtime.IAdapterFactory;

public class AssertionTreeDefferedAdapter implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof AssertionTreeModel) {
			return new AssertionTreeDefferedModel((AssertionTreeModel) adaptableObject);
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { AssertionTreeModel.class };
	}

}
