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
package org.eclipse.rcptt.internal.runtime.ui.rap;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils.unwrapWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.rcptt.core.ecl.core.model.AUTControlsHierarchy;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.RawFactory;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.recording.aspects.swt.rap.SWTEventManager;
import org.eclipse.rcptt.tesla.swt.workbench.EclipseWorkbenchProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public class AUTControlsHierarchyUtilities {

	public static void initResponse(Display display, Element element, SWTUIElement swtUIElement,
			UIHierarchyResponse response) {

		if (null == element) {

			SWTUIElement swtUIParent = getSWTUIParent(display, swtUIElement);

			if (null != swtUIParent) {
				initResponse(display, getElement(swtUIParent), swtUIParent, response);
			}

		} else {

			response.setUiElement(getUIElement(display, element, swtUIElement));
			initResponseChildren(display, swtUIElement, response);
		}
	}

	public static void initResponseChildren(Display display, SWTUIElement swtUIElement, UIHierarchyResponse response) {

		SWTUIElement[] swtUIChildren = SWTUIPlayer.getPlayer(display).children.collectFor(
				swtUIElement, null, false, null);

		for (SWTUIElement swtUIChild : swtUIChildren) {

			if (null == swtUIChild.unwrap() || swtUIChild.unwrap().isDisposed()) {
				continue;
			}

			if (null != swtUIChild.unwrap().getData("isHover")) {
				continue;
			}

			if (!isGoodKind(swtUIChild.getKind())) {
				initResponseChildren(display, swtUIChild, response);
				continue;
			}

			Element elementChild = getElement(swtUIChild);

			if (null == elementChild) {
				continue;
			}

			response.getChildren().add(getUIElement(display, getElement(swtUIChild), swtUIChild));
		}
	}

	public static Element getElement(AUTControlsHierarchy autControlsHierarchy) {
		Element element = RawFactory.eINSTANCE.createElement();
		element.setId(autControlsHierarchy.getId());
		element.setKind(autControlsHierarchy.getKind());
		return element;
	}

	public static Element getElement(SWTUIElement swtUIElement) {
		return SWTEventManager.getElement(swtUIElement);
	}

	public static UIElement getUIElement(Display display, Element element, SWTUIElement swtUIElement) {
		UIElement uiElement = ProtocolFactory.eINSTANCE.createUIElement();

		if (null == element) {
			return uiElement;
		}

		uiElement.setId(element.getId());
		uiElement.setKind(element.getKind());

		if (null == swtUIElement) {
			return uiElement;
		}

		uiElement.setGenerationKind(swtUIElement.getGenerationKind());
		uiElement.setName(swtUIElement.getText());
		uiElement.setDescription(swtUIElement.getClassName());

		SWTUIElement[] swtUIChildChildren = SWTUIPlayer.getPlayer(display).children.collectFor(
				swtUIElement, null, false, null);
		boolean hasChildren = (0 != swtUIChildChildren.length);
		uiElement.setHasChildren(hasChildren);

		return uiElement;
	}

	// Dirty fix of the method getParentsList in SWTUIPlayer
	public static List<SWTUIElement> getParentsList(Display display, SWTUIElement swtuiElement) {
		Map<Control, SWTUIElement> references = EclipseWorkbenchProvider.getProvider()
				.getWorkbenchReference(SWTUIPlayer.getPlayer(display));
		List<Widget> parents = SWTUIPlayer.getPlayer(display).collectParents(unwrapWidget(swtuiElement), references);
		List<SWTUIElement> elements = new ArrayList<SWTUIElement>();
		for (Widget widget : parents) {
			SWTUIElement e = null;
			if (references.containsKey(widget)) {
				e = references.get(widget);
			} else {
				e = SWTUIPlayer.getPlayer(display).wrap(widget);
			}
			if (e != null) {
				if (!isGoodKind(e.getKind())) {
					continue;
				}
				if (SWTUIPlayer.getPlayer(display).isVisible(e)) {
					elements.add(e);
				}
			}
		}
		return elements;
	}

	public static SWTUIElement getSWTUIParent(Display display, SWTUIElement uiElement) {
		List<SWTUIElement> parentsList = getParentsList(display, uiElement);
		if (parentsList.size() > 0) {
			return parentsList.get(0);
		}
		return null;
	}

	public static boolean isGoodKind(GenericElementKind kind) {
		if (kind.is(ElementKind.Unknown) || kind.is(ElementKind.Editor) || kind.is(ElementKind.View)) {
			return false;
		}
		return true;
	}
}
