/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ui.editors.ecl.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.source.ISourceViewer;

public class FormatAction extends Action {

	private final TextViewer textViewer;

	public FormatAction(TextViewer sourceViewer) {
		super("Format Source Code");
		this.textViewer = sourceViewer;
	}

	@Override
	public void run() {
		textViewer.doOperation(ISourceViewer.FORMAT);
	}

}
