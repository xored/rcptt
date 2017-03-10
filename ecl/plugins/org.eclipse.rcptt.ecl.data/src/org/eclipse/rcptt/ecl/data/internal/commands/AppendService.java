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
import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.data.commands.Append;
import org.eclipse.rcptt.ecl.data.internal.EclDataPlugin;
import org.eclipse.rcptt.ecl.data.objects.Tree;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class AppendService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		Append append = (Append) command;
		Tree tree = append.getTree();
		int index = append.getIndex();
		EList<Tree> newChilds = append.getChildren();
		EList<Tree> treeChilds = tree.getChildren();

		// -1 means max index + 1
		if (index == -1) {
			index = treeChilds.size();
		}
		if (index < 0 || index > treeChilds.size()) {
			return EclDataPlugin.createErr("Invalid value of the index. Index should be in the range [%d, %d].",
					0, treeChilds.size());
		}

		treeChilds.addAll(index, newChilds);
		context.getOutput().write(tree);
		return Status.OK_STATUS;
	}

}
