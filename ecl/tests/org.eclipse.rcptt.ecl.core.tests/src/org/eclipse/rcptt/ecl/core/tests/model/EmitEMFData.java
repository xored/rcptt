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
package org.eclipse.rcptt.ecl.core.tests.model;

import org.eclipse.rcptt.ecl.core.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Emit EMF Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.rcptt.ecl.core.tests.model.EmitEMFData#getParamInt <em>Param Int</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.rcptt.ecl.core.tests.model.ModelPackage#getEmitEMFData()
 * @model
 * @generated
 */
public interface EmitEMFData extends Command {
	/**
	 * Returns the value of the '<em><b>Param Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Param Int</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Int</em>' attribute.
	 * @see #setParamInt(int)
	 * @see org.eclipse.rcptt.ecl.core.tests.model.ModelPackage#getEmitEMFData_ParamInt()
	 * @model
	 * @generated
	 */
	int getParamInt();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.core.tests.model.EmitEMFData#getParamInt <em>Param Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Int</em>' attribute.
	 * @see #getParamInt()
	 * @generated
	 */
	void setParamInt(int value);

} // EmitEMFData
