package org.eclipse.rcptt.ui.panels.assertion.tree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

public class AssertionTreeViewer extends TreeViewer {

	public AssertionTreeViewer(Tree tree) {
		super(tree);
	}

	public boolean isItemExists(Object object) {
		return null != findItem(object);
	}

}
