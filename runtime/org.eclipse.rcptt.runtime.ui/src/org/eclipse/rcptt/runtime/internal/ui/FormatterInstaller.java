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

import org.eclipse.jface.bindings.keys.formatting.KeyFormatterFactory;
import org.eclipse.rcptt.runtime.features.IFeatureInstaller;
import org.eclipse.rcptt.tesla.workbench.formatter.Q7KeyFormatter;

public class FormatterInstaller implements IFeatureInstaller {

	@Override
	public void installFeatures() {
		if (!(KeyFormatterFactory.getDefault() instanceof Q7KeyFormatter))
			Q7KeyFormatter.installQ7Formatter();
	}

}
