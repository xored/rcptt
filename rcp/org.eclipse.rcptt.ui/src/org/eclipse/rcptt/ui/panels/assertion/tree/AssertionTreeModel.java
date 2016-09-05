package org.eclipse.rcptt.ui.panels.assertion.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.ui.panels.assertion.UIHierarchyResponseUtilities;

public class AssertionTreeModel {

	private final AutLaunch aut;
	private final UIElement uiElement;

	public AssertionTreeModel(AutLaunch aut, UIElement uiElement) {
		this.aut = aut;
		this.uiElement = uiElement;
	}

	public UIElement getUIElement() {
		return uiElement;
	}

	public String getLabel() {
		return UIHierarchyResponseUtilities.getUIElementCaption(uiElement);
	}

	public Object[] getChildren() {
		List<AssertionTreeModel> list = new ArrayList<AssertionTreeModel>();
		for (Object object : getChildren(uiElement)) {
			list.add(new AssertionTreeModel(aut, (UIElement) object));
		}
		return (AssertionTreeModel[]) list.toArray(new AssertionTreeModel[list.size()]);
	}

	public Object getParent() {
		return new AssertionTreeModel(aut, (UIElement) getParent(uiElement));
	}

	public boolean hasChildren() {
		return hasChildren(uiElement);
	}

	private Object[] getChildren(Object parent) {
		if (!(parent instanceof UIElement)) {
			return null;
		}
		UIHierarchyResponse response = UIHierarchyResponseUtilities.getUIHierarchyElement(aut, (UIElement) parent);
		List<UIElement> list = new ArrayList<UIElement>();
		for (UIElement uiElement : response.getChilren()) {
			list.add(uiElement);
		}
		return (UIElement[]) list.toArray(new UIElement[list.size()]);
	}

	private Object getParent(Object object) {
		if (!(object instanceof UIElement)) {
			return null;
		}
		UIHierarchyResponse response = UIHierarchyResponseUtilities.getUIHierarchyParent(aut, (UIElement) object);
		return response.getUiElement();
	}

	private boolean hasChildren(Object object) {
		if (!(object instanceof UIElement)) {
			return false;
		}
		UIElement uiElement = (UIElement) object;
		return uiElement.isHasChildren();
	}
}