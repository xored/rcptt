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
 * A representation of the model object '<em><b>Get Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.GetAttr#getTree <em>Tree</em>}</li>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.GetAttr#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getGetAttr()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Gets attribute from the tree node by name. Fails if attribute is not found.' returns='Value of the attribute' example='tree-node \"Device\" [map [entry \"id\" \"1\"] [entry \"name\" \"first\"]]\n\t| get-attr \"name\" | log'"
 * @generated
 */
public interface GetAttr extends Command {
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
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getGetAttr_Tree()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Tree node to get attribute from'"
	 * @generated
	 */
	Tree getTree();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.commands.GetAttr#getTree <em>Tree</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tree</em>' reference.
	 * @see #getTree()
	 * @generated
	 */
	void setTree(Tree value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getGetAttr_Name()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Name of the node attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.commands.GetAttr#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // GetAttribute
