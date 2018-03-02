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
package org.eclipse.rcptt.runtime.internal.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.bindings.keys.formatting.KeyFormatterFactory;
import org.eclipse.rcptt.core.ecl.core.model.SetQ7Features;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.runtime.RcpttFeatureUtils;
import org.eclipse.rcptt.tesla.workbench.formatter.Q7KeyFormatter;

public class SetQ7FeaturesService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		SetQ7Features service = (SetQ7Features) command;
		EList<String> features = service.getFeatures();
		RcpttFeatureUtils.setFeatures(features);

		checkShortcutsFormatter();

		return Status.OK_STATUS;
	}

	private void checkShortcutsFormatter() {
		if (!(KeyFormatterFactory.getDefault() instanceof Q7KeyFormatter))
			Q7KeyFormatter.installQ7Formatter();
	}

}
