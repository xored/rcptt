/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.internal.runtime.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy;
import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class AssertionAUTControlsHierarchyService implements ICommandService {

	private Map<String, Object> widgetsMap = new HashMap<String, Object>();

	private int lastIndex = 0;

	private IProcess context;

	private Display display;

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		this.context = context;

		AssertionAUTControlsHierarchy assertionAUTControlsHierarchy = (AssertionAUTControlsHierarchy) command;
		final String id = assertionAUTControlsHierarchy.getId();
		AssertionAUTControlsHierarchyState state = assertionAUTControlsHierarchy.getState();

		if (AssertionAUTControlsHierarchyState.CLEAR == state) {
			widgetsMap.clear();
			lastIndex = 0;
			return Status.OK_STATUS;
		}

		this.display = PlatformUI.getWorkbench().getDisplay();

		if (display.isDisposed()) {
			// TODO: return new Status(IStatus.ERROR, Activator.getUniqueIdentifier(), Activator.INTERNAL_ERROR,
			// "Display disposed", null);
			return Status.OK_STATUS;
		}

		final Exception[] ex = new Exception[1];

		final UIHierarchyResponse response = ProtocolFactory.eINSTANCE.createUIHierarchyResponse();

		if (AssertionAUTControlsHierarchyState.GET_ROOT == state) {

			display.syncExec(new Runnable() {
				@Override
				public void run() {
					SWTUIElement[] swtUIElements = SWTUIPlayer.getPlayer(display).children.collectFor(null, null,
							false, null);

					System.out.println("AssertionAUTControlsHierarchyState.GET_ROOT");
					for (SWTUIElement swtUIElement : swtUIElements) {
						System.out.println("-------------");
						System.out.println(swtUIElement.getText());
						System.out.println(swtUIElement.getGenerationKind());

						UIElement uiElement = ProtocolFactory.eINSTANCE.createUIElement();

						uiElement.setId(String.valueOf(lastIndex));
						uiElement.setKind(swtUIElement.getGenerationKind());
						uiElement.setName(swtUIElement.getText());
						uiElement.setDescription(swtUIElement.widget.getClass().getName());

						SWTUIElement[] childrenSWTUIElements = SWTUIPlayer.getPlayer(display).children.collectFor(
								swtUIElement, null, false, null);
						boolean hasChildren = (0 != childrenSWTUIElements.length);
						uiElement.setHasChildren(hasChildren);

						addWidgetToMap(swtUIElement.unwrap());

						response.getUiElements().add(uiElement);
					}

					try {
						write(response);
					} catch (CoreException e) {
						e.printStackTrace();
						ex[0] = e;
					}
				}
			});

			if (null != ex[0]) {
				ex[0].printStackTrace();
			}
		}

		if (AssertionAUTControlsHierarchyState.GET_NODE == state) {
			if (!widgetsMap.containsKey(id)) {
				return Status.OK_STATUS;
			}

			display.syncExec(new Runnable() {
				@Override
				public void run() {

					Object widgetObject = widgetsMap.get(id);

					SWTUIElement[] swtUIElements = SWTUIPlayer.getPlayer(display).children.collectFor(
							SWTUIPlayer.getPlayer(display).wrap(widgetObject), null, false,
							null);

					System.out.println("AssertionAUTControlsHierarchyState.GET_NODE");
					for (SWTUIElement swtUIElement : swtUIElements) {
						System.out.println("-------------");
						System.out.println(swtUIElement.getText());
						System.out.println(swtUIElement.getGenerationKind());

						UIElement uiElement = ProtocolFactory.eINSTANCE.createUIElement();

						uiElement.setId(String.valueOf(lastIndex));
						uiElement.setKind(swtUIElement.getGenerationKind());
						uiElement.setName(swtUIElement.getText());
						uiElement.setDescription(swtUIElement.widget.getClass().getName());

						SWTUIElement[] childrenSWTUIElements = SWTUIPlayer.getPlayer(display).children.collectFor(
								swtUIElement, null, false, null);
						boolean hasChildren = (0 != childrenSWTUIElements.length);
						uiElement.setHasChildren(hasChildren);

						addWidgetToMap(swtUIElement.unwrap());

						response.getUiElements().add(uiElement);
					}

					try {
						write(response);
					} catch (CoreException e) {
						e.printStackTrace();
						ex[0] = e;
					}
				}
			});

			if (null != ex[0]) {
				ex[0].printStackTrace();
			}
		}

		return Status.OK_STATUS;
	}

	private void write(Object object) throws CoreException {
		this.context.getOutput().write(object);
	}

	private void addWidgetToMap(Object object) {
		widgetsMap.put(String.valueOf(lastIndex), object);
		lastIndex++;
	}
}
