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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.core.ecl.core.model.AUTControlsHierarchy;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.recording.aspects.swt.rap.SWTEventManager;
import org.eclipse.rcptt.tesla.ui.RWTUtils;
import org.eclipse.swt.widgets.Display;

public class AUTControlsHierarchyService implements ICommandService {

	private IProcess context = null;

	private Display display = null;

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		this.context = context;

		AUTControlsHierarchy autControlsHierarchy = (AUTControlsHierarchy) command;

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
			display = RWTUtils.findDisplay();
		}
		return display;
	}

	private IStatus highlightWidget(AUTControlsHierarchy autControlsHierarchy) throws CoreException {

		final Element element = AUTControlsHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (null == swtUIElement) {
			return Status.OK_STATUS;
		}

		SWTEventManager.highlightWidget(swtUIElement.unwrap());

		return Status.OK_STATUS;
	}

	private IStatus updateAssertionPanelWindow(AUTControlsHierarchy autControlsHierarchy)
			throws CoreException {

		final Element element = AUTControlsHierarchyUtilities.getElement(autControlsHierarchy);
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

	private IStatus getElement(AUTControlsHierarchy autControlsHierarchy) throws CoreException {

		final CoreException[] ex = new CoreException[1];
		final UIHierarchyResponse response = ProtocolFactory.eINSTANCE.createUIHierarchyResponse();

		final Element element = AUTControlsHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (getDisplay().isDisposed()) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Display disposed");
		}

		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				AUTControlsHierarchyUtilities.initResponse(getDisplay(), element, swtUIElement, response);

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

	private IStatus getParent(AUTControlsHierarchy autControlsHierarchy) throws CoreException {

		final CoreException[] ex = new CoreException[1];
		final UIHierarchyResponse response = ProtocolFactory.eINSTANCE.createUIHierarchyResponse();

		final Element element = AUTControlsHierarchyUtilities.getElement(autControlsHierarchy);
		final SWTUIElement swtUIElement = SWTEventManager.getSWTUIElement(element);

		if (getDisplay().isDisposed()) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Display disposed");
		}

		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				SWTUIElement swtUIParent = AUTControlsHierarchyUtilities.getSWTUIParent(getDisplay(), swtUIElement);

				// if (null != swtUIParent) {
					AUTControlsHierarchyUtilities.initResponse(getDisplay(),
							AUTControlsHierarchyUtilities.getElement(swtUIParent), swtUIParent, response);
				// } else {
				// AUTControlsHierarchyUtilities.initResponse(getDisplay(), null, null, response);
				// }

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
