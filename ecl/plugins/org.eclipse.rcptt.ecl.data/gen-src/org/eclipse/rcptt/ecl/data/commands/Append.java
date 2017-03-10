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

import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.data.objects.Tree;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Append</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.Append#getTree <em>Tree</em>}</li>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.Append#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.rcptt.ecl.data.commands.Append#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getAppend()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Adds child tree nodes to the tree attribute from tree. Fails if <code>index</code> parameter is out of range.' returns='The value of <code>tree</code> argument' example='tree-node \"Device\" [tree-node -name \"DeviceName\" -text \"device\"]\n\t| append [tree-node -name \"DeviceTarget\" -text \"target\"] -index 0'"
 * @generated
 */
public interface Append extends Command {
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
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getAppend_Tree()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Tree node to add child nodes to'"
	 * @generated
	 */
	Tree getTree();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.commands.Append#getTree <em>Tree</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tree</em>' reference.
	 * @see #getTree()
	 * @generated
	 */
	void setTree(Tree value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.ecl.data.objects.Tree}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getAppend_Children()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='New children nodes to add to the tree'"
	 * @generated
	 */
	EList<Tree> getChildren();

	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see org.eclipse.rcptt.ecl.data.commands.CommandsPackage#getAppend_Index()
	 * @model default="-1"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Index of the first child node added in the node list. Min value is <code>0</code> and max value is size of node list. Default value is <code>-1</code> and it means size of node list.'"
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.commands.Append#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

} // Append
