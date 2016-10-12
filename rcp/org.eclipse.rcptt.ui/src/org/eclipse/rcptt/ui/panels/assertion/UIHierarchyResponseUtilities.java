package org.eclipse.rcptt.ui.panels.assertion;

import org.eclipse.rcptt.core.ecl.core.model.ControlHierarchyState;
import org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy;
import org.eclipse.rcptt.core.ecl.core.model.Q7CoreFactory;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;

public class UIHierarchyResponseUtilities {

	public static UIHierarchyResponse getUIHierarchyElement(AutLaunch aut, UIElement uiElement) {

		GetControlHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
				.createGetControlHierarchy();

		if (null != uiElement) {
			setAUTControlsHierarchyCommandByUIElement(assertionAUTControlsHierarchy, uiElement);
		}

		assertionAUTControlsHierarchy.setState(ControlHierarchyState.GET_ELEMENT);

		try {
			Object childAUTObject = aut.execute(assertionAUTControlsHierarchy);
			if (childAUTObject instanceof UIHierarchyResponse) {
				return (UIHierarchyResponse) childAUTObject;
			}
		} catch (Exception e) {
			Q7UIPlugin.log(e);
		}
		return null;
	}

	public static UIHierarchyResponse getUIHierarchyParent(AutLaunch aut, UIElement uiElement) {

		GetControlHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
				.createGetControlHierarchy();

		if (null != uiElement) {
			setAUTControlsHierarchyCommandByUIElement(assertionAUTControlsHierarchy, uiElement);
		}

		assertionAUTControlsHierarchy.setState(ControlHierarchyState.GET_PARENT);

		try {
			Object childAUTObject = aut.execute(assertionAUTControlsHierarchy);
			if (childAUTObject instanceof UIHierarchyResponse) {
				return (UIHierarchyResponse) childAUTObject;
			}
		} catch (Exception e) {
			Q7UIPlugin.log(e);
		}
		return null;
	}

	public static String getUIElementCaption(UIElement uiElement) {
		if (null == uiElement.getName()) {
			return uiElement.getKind() + " (" + uiElement.getDescription() + ")"; //$NON-NLS-1$ $NON-NLS-2$
		}
		return uiElement.getKind() + " : " + uiElement.getName() + //$NON-NLS-1$
				" (" + uiElement.getDescription() + ")"; //$NON-NLS-1$ $NON-NLS-2$
	}

	public static void highlightWidget(AutLaunch aut, UIElement uiElement) {

		GetControlHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
				.createGetControlHierarchy();

		assertionAUTControlsHierarchy.setState(ControlHierarchyState.HIGHLIGHT_WIDGET);
		setAUTControlsHierarchyCommandByUIElement(assertionAUTControlsHierarchy, uiElement);

		try {
			aut.execute(assertionAUTControlsHierarchy);
		} catch (Exception e) {
			Q7UIPlugin.log(e);
		}
	}

	public static void updateAssertionPanelWindow(AutLaunch aut, UIElement uiElement) {

		GetControlHierarchy assertionAUTControlsHierarchy = Q7CoreFactory.eINSTANCE
				.createGetControlHierarchy();

		assertionAUTControlsHierarchy.setState(ControlHierarchyState.UPDATE_ASSERT_WINDOW);
		setAUTControlsHierarchyCommandByUIElement(assertionAUTControlsHierarchy, uiElement);

		try {
			aut.execute(assertionAUTControlsHierarchy);
		} catch (Exception e) {
			Q7UIPlugin.log(e);
		}
	}

	public static void setAUTControlsHierarchyCommandByUIElement(
			GetControlHierarchy autControlsHierarchyCommand, UIElement uiElement) {

		autControlsHierarchyCommand.setId(uiElement.getId());
		autControlsHierarchyCommand.setKind(uiElement.getKind());
		autControlsHierarchyCommand.setDescription(uiElement.getDescription());
	}
}
