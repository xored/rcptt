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
package org.eclipse.rcptt.internal.runtime.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.recording.aspects.SWTEventManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class GetControlHierarchyService implements ICommandService {

	private IProcess context = null;

	private Display display = null;

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		this.context = context;

		GetControlHierarchy autControlsHierarchy = (GetControlHierarchy) command;

		switch (autControlsHierarchy.getState()) {

		case HIGHLIGHT_WIDGET:
			return highlightWidget(autControlsHierarchy);

		case UPDATE_ASSERT_WINDOW:
			return updateAssertionPanelWindow(autControlsHierarchy);

		case GET_ELEMENT:
			return getElement(autControlsHierarchy);

		case GET_PARENT:
			return getParent(autControlsHierarchy);

		default:
			return Status.OK_STATUS;
		}
	}

	private void write(Object object) throws CoreException {
		context.getOutput().write(object);
	}

	private Display getDisplay() {
		if (null == display) {
			display = PlatformUI.getWorkbench().getDisplay();
		}
		return display;
	}

	private IStatus highlightWidget(GetControlHierarchy autControlsHierarchy) throws CoreException {

		final Element element = GetControlHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (null == swtUIElement) {
			return Status.OK_STATUS;
		}

		SWTEventManager.highlightWidget(swtUIElement.unwrap());

		return Status.OK_STATUS;
	}

	private IStatus updateAssertionPanelWindow(GetControlHierarchy autControlsHierarchy)
			throws CoreException {

		final Element element = GetControlHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (null == swtUIElement) {
			return Status.OK_STATUS;
		}

		getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				SWTEventManager.updateAssertionPanelWindow(swtUIElement.unwrap());
			}
		});

		return Status.OK_STATUS;
	}

	private IStatus getElement(GetControlHierarchy autControlsHierarchy) throws CoreException {

		final CoreException[] ex = new CoreException[1];
		final UIHierarchyResponse response = ProtocolFactory.eINSTANCE.createUIHierarchyResponse();

		final Element element = GetControlHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (getDisplay().isDisposed()) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Display disposed");
		}

		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				GetControlHierarchyUtilities.initResponse(getDisplay(), element, swtUIElement, response);

				try {
					write(response);
				} catch (CoreException e) {
					ex[0] = e;
				}
			}
		});

		if (null != ex[0]) {
			throw ex[0];
		}

		return Status.OK_STATUS;

	}

	private IStatus getParent(GetControlHierarchy autControlsHierarchy) throws CoreException {

		final CoreException[] ex = new CoreException[1];
		final UIHierarchyResponse response = ProtocolFactory.eINSTANCE.createUIHierarchyResponse();

		final Element element = GetControlHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (getDisplay().isDisposed()) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Display disposed");
		}

		if (null == swtUIElement) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "swtUIElement is null");
		}

		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				SWTUIElement swtUIParent = GetControlHierarchyUtilities.getSWTUIParent(getDisplay(), swtUIElement);

				GetControlHierarchyUtilities.initResponse(getDisplay(),
						GetControlHierarchyUtilities.getElement(swtUIParent), swtUIParent, response);

				try {
					write(response);
				} catch (CoreException e) {
					ex[0] = e;
				}
			}
		});

		if (null != ex[0]) {
			throw ex[0];
		}

		return Status.OK_STATUS;
	}
}
