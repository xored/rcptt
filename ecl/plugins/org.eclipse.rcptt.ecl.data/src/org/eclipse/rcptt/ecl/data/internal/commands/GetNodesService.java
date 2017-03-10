/*******************************************************************************
 * Copyright (c) 2009, 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ecl.data.internal.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.data.commands.GetNodes;
import org.eclipse.rcptt.ecl.data.internal.EclDataPlugin;
import org.eclipse.rcptt.ecl.data.objects.Tree;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class GetNodesService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		GetNodes gn = (GetNodes) command;
		Tree tree = gn.getTree();
		String name = gn.getName();
		
		EList<Tree> nodes = tree.getChildren();
		if (name != null && !name.equals("")) {
			nodes = new BasicEList<Tree>();
			for (Tree child : tree.getChildren()) {
				if (child.getName().equals(name)) {
					nodes.add(child);
				}
			}
		}

		int pos = gn.getPos();
		if (pos < 0 || pos >= nodes.size()) {
			return EclDataPlugin.createErr("Invalid value of the 'pos' parameter. It should be in the range [%d, %d].",
					0, nodes.size() - 1);
		}

		int len = gn.getLen();
		// -1 means max length
		int maxLen = nodes.size() - pos;
		if (len == -1) {
			len = maxLen;
		}
		if (len < 0 || len > maxLen) {
			return EclDataPlugin.createErr("Invalid value of the 'len' parameter. It should be in the range [%d, %d].",
					0, maxLen);
		}

		int maxPos = len + pos;
		for (; pos < maxPos; pos++) {
			context.getOutput().write(nodes.get(pos));
		}
		return Status.OK_STATUS;
	}

}
