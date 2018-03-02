/*******************************************************************************
 * Copyright (c) 2009, 2018 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.runtime.e4.ui;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Display;

public class PureE4ModelProcessor {

	private static PureE4ModelProcessor INSTANCE = null;

	@Inject
	private MApplication application;

	private Display display;
	private EPartService partService;
	private EModelService modelService;

	@Execute
	public void execute(Display display, EModelService modelService, EPartService partService) {
		this.display = display;
		this.partService = partService;
		this.modelService = modelService;
		INSTANCE = this;
	}

	public static Display getDisplay() {
		return INSTANCE.display;
	}

	public static EModelService getModelService() {
		return INSTANCE.modelService;
	}

	public static EPartService getPartService() {
		return INSTANCE.partService;
	}

	public static MApplication getApplication() {
		return INSTANCE.application;
	}

	public static IWorkbench getWorkbench() {
		return INSTANCE.application.getContext().get(IWorkbench.class);
	}

	public static List<MWindow> getWorkbenchWindows() {
		return INSTANCE.application.getChildren();
	}

	public static ESelectionService getSelectionService() {
		return INSTANCE.application.getContext().get(ESelectionService.class);
	}

	// TODO (e4 support): remove
	// public static MPart getActivePart() {
	// return INSTANCE.application.getContext().getActiveLeaf().get(MPart.class);
	// }
	//
	// public static Shell getActiveShell() {
	// return INSTANCE.application.getContext().getActiveLeaf().get(Shell.class);
	// }

}
