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
package org.eclipse.rcptt.ecl.data.commands;

import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.data.objects.Tree;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Attributes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.GetAttrs#getTree <em>Tree</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getGetAttrs()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Gets attributes from the tree node' returns='Map with tree node attributes' example='tree-node \"Device\" [map [entry \"id\" \"1\"] [entry \"name\" \"first\"]]\n\t| get-attrs | get \"name\" | log'"
 * @generated
 */
public interface GetAttrs extends Command {
	/**
	 * Returns the value of the '<em><b>Tree</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tree</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tree</em>' reference.
	 * @see #setTree(Tree)
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getGetAttrs_Tree()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Tree node to get attributes from'"
	 * @generated
	 */
	Tree getTree();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.commands.GetAttrs#getTree <em>Tree</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tree</em>' reference.
	 * @see #getTree()
	 * @generated
	 */
	void setTree(Tree value);

} // GetAttributes
