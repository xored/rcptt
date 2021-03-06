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
package org.eclipse.rcptt.tesla.ecl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deactivate Cell Edit</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.rcptt.tesla.ecl.model.TeslaPackage#getDeactivateCellEdit()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Applies cell editing.' returns='value of &lt;code&gt;control&lt;/code&gt; parameter' example='with [get-editor context2 | get-section Parameters | get-table] {\n    select parameter | activate-cell-edit -column 1\n    get-editbox | set-text \"value\"\n    cancel-cell-edit\n    deactivate-cell-edit\n}'"
 * @generated
 */
public interface DeactivateCellEdit extends CellEdit {
} // DeactivateCellEdit
