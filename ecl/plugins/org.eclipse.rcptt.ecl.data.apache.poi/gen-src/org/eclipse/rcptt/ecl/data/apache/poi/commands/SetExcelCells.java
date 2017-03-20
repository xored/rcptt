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
package org.eclipse.rcptt.ecl.data.apache.poi.commands;

import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.EclMap;
import org.eclipse.rcptt.ecl.data.objects.Table;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Excel Cells</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getCells <em>Cells</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage#getSetExcelCells()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Sets cell values to the table' returns='The value of \'table\' argument' example='read-excel-file \"workspace:/excel/devices.xlsx\" \"Supported devices\"\n\t| set-excel-cells [map [entry \"A1\" \"New Device Name\"] [entry \"B1\" \"New Device Target\"]]\n\t| write-excel-file \"workspace:/excel/devices.xlsx\" -append'"
 * @generated
 */
public interface SetExcelCells extends Command {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(Table)
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage#getSetExcelCells_Table()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Table to set cell values to'"
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

	/**
	 * Returns the value of the '<em><b>Cells</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cells</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cells</em>' reference.
	 * @see #setCells(EclMap)
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage#getSetExcelCells_Cells()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ecl/docs description='Map where key is cell name and value is cell value to set'"
	 * @generated
	 */
	EclMap getCells();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getCells <em>Cells</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cells</em>' reference.
	 * @see #getCells()
	 * @generated
	 */
	void setCells(EclMap value);

} // SetExcelCells
