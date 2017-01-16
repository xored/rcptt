package org.eclipse.rcptt.ui.panels.assertion.tree;

import org.eclipse.jface.viewers.LabelProvider;

public class AssertionTreeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object object) {
		if (object instanceof AssertionTreeModel) {
			return ((AssertionTreeModel) object).getLabel();
		}
		return super.getText(object);
	}
}