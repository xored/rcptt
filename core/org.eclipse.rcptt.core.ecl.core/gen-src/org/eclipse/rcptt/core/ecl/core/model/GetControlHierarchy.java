/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.core.ecl.core.model;

import org.eclipse.rcptt.ecl.core.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Control Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getGetControlHierarchy()
 * @model
 * @generated
 */
public interface GetControlHierarchy extends Command {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getGetControlHierarchy_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.core.ecl.core.model.ControlHierarchyState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.core.ecl.core.model.ControlHierarchyState
	 * @see #setState(ControlHierarchyState)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getGetControlHierarchy_State()
	 * @model
	 * @generated
	 */
	ControlHierarchyState getState();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.core.ecl.core.model.ControlHierarchyState
	 * @see #getState()
	 * @generated
	 */
	void setState(ControlHierarchyState value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getGetControlHierarchy_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getGetControlHierarchy_Kind()
	 * @model default=""
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.GetControlHierarchy#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

} // GetControlHierarchy
