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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.EclBoolean;
import org.eclipse.rcptt.ecl.core.EclByte;
import org.eclipse.rcptt.ecl.core.EclChar;
import org.eclipse.rcptt.ecl.core.EclDouble;
import org.eclipse.rcptt.ecl.core.EclFloat;
import org.eclipse.rcptt.ecl.core.EclInteger;
import org.eclipse.rcptt.ecl.core.EclLong;
import org.eclipse.rcptt.ecl.core.EclMap;
import org.eclipse.rcptt.ecl.core.EclMapEntry;
import org.eclipse.rcptt.ecl.core.EclShort;
import org.eclipse.rcptt.ecl.core.EclString;
import org.eclipse.rcptt.ecl.data.commands.TreeNode;
import org.eclipse.rcptt.ecl.data.internal.EclDataPlugin;
import org.eclipse.rcptt.ecl.data.objects.Attribute;
import org.eclipse.rcptt.ecl.data.objects.ObjectsFactory;
import org.eclipse.rcptt.ecl.data.objects.Tree;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class TreeNodeService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TreeNode ti = (TreeNode) command;
		String name = ti.getName();
		String text = ti.getText();
		EclMap attrs = ti.getAttrs();
		EList<Tree> childs = ti.getChildren();
		
		if (childs != null && childs.size() > 0
				&& text != null && !text.equals("")) {
			return EclDataPlugin.createErr("Text can be specified only if the node has no children");
		}

		Tree tree = ObjectsFactory.eINSTANCE.createTree();
		tree.setName(name);
		tree.setText(text);
		tree.getChildren().addAll(childs);

		if (attrs != null) {
			for (EclMapEntry attr : attrs.getEntries()) {
				Attribute attribute = ObjectsFactory.eINSTANCE.createAttribute();
				String key = getValue(attr.getKey());
				String value = getValue(attr.getValue());
				if (key == null || key.equals("")) {
					return EclDataPlugin.createErr("Error getting input map. Map key must not be empty");
				}
				if (value == null || value.equals("")) {
					continue;
				}
				attribute.setName(key);
				attribute.setValue(value);
				tree.getAttributes().add(attribute);
			}
		}

		context.getOutput().write(tree);
		return Status.OK_STATUS;
	}

	private String getValue(EObject object) {
		if (object == null) {
			return null;
		}
		if (object instanceof EclBoolean) {
			boolean value = ((EclBoolean) object).isValue();
			return String.valueOf(value);
		} else if (object instanceof EclByte) {
			byte value = ((EclByte) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclChar) {
			char value = ((EclChar) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclDouble) {
			double value = ((EclDouble) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclFloat) {
			float value = ((EclFloat) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclInteger) {
			int value = ((EclInteger) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclLong) {
			long value = ((EclLong) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclShort) {
			short value = ((EclShort) object).getValue();
			return String.valueOf(value);
		} else if (object instanceof EclString) {
			return ((EclString) object).getValue();
		}
		return object.toString();
	}

}
