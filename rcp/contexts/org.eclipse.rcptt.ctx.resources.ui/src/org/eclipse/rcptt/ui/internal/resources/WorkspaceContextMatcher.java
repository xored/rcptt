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
package org.eclipse.rcptt.ui.internal.resources;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.core.model.search.Q7SearchCore;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.rcptt.resources.ui.WSSearchSwitch;
import org.eclipse.rcptt.ui.search.Matcher;
import org.eclipse.rcptt.workspace.WorkspaceContext;

public class WorkspaceContextMatcher implements Matcher {

	public boolean matches(IQ7NamedElement object, String query,
			SubMonitor monitor) {
		if (!(object instanceof IContext)) {
			return false;
		}
		String type = Q7SearchCore.findContextTypeByDocument((IContext)object);
		if (type == null && object instanceof IContext) {
			try {
				type = ((IContext) object).getType().getId();
			} catch (ModelException e) {
				RcpttPlugin.log(e);
			}
		}
		if (type != null && !type.equals("org.eclipse.rcptt.ctx.workspace")) {
			return false;
		}
		try {
			NamedElement ctx = object.getNamedElement();
			if (ctx instanceof WorkspaceContext) {
				final WorkspaceContext context = (WorkspaceContext) ctx;
				final WSSearchSwitch sw = new WSSearchSwitch(query, monitor);
				try {
					return sw.doSwitch(context.getContent());
				} catch (OperationCanceledException oce) {
					return false;
				}
			}
			return false;
		} catch (ModelException e) {
			Q7UIPlugin.log(e);
			return false;
		}
	}

}
