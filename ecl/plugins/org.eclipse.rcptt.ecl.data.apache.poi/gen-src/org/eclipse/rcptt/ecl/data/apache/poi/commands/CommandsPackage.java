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
package org.eclipse.rcptt.ecl.data.apache.poi.commands;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.rcptt.ecl.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsFactory
 * @model kind="package"
 * @generated
 */
public interface CommandsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "commands";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ecl/data/apache/poi/commands.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dataCommands";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommandsPackage eINSTANCE = org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.WriteExcelFileImpl <em>Write Excel File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.WriteExcelFileImpl
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getWriteExcelFile()
	 * @generated
	 */
	int WRITE_EXCEL_FILE = 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE__TABLES = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE__URI = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Append</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE__APPEND = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Write Excel File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_EXCEL_FILE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.ReadExcelFileImpl <em>Read Excel File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.ReadExcelFileImpl
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getReadExcelFile()
	 * @generated
	 */
	int READ_EXCEL_FILE = 1;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_EXCEL_FILE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_EXCEL_FILE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_EXCEL_FILE__URI = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sheets</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_EXCEL_FILE__SHEETS = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Read Excel File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_EXCEL_FILE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelCellsImpl <em>Get Excel Cells</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelCellsImpl
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getGetExcelCells()
	 * @generated
	 */
	int GET_EXCEL_CELLS = 2;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_CELLS__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_CELLS__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_CELLS__TABLE = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cells</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_CELLS__CELLS = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Get Excel Cells</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_CELLS_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelRangeImpl <em>Get Excel Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelRangeImpl
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getGetExcelRange()
	 * @generated
	 */
	int GET_EXCEL_RANGE = 3;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_RANGE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_RANGE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_RANGE__TABLE = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_RANGE__RANGE = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Get Excel Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EXCEL_RANGE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.SetExcelCellsImpl <em>Set Excel Cells</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.SetExcelCellsImpl
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getSetExcelCells()
	 * @generated
	 */
	int SET_EXCEL_CELLS = 4;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXCEL_CELLS__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXCEL_CELLS__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXCEL_CELLS__TABLE = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cells</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXCEL_CELLS__CELLS = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Set Excel Cells</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXCEL_CELLS_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile <em>Write Excel File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Write Excel File</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile
	 * @generated
	 */
	EClass getWriteExcelFile();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#getTables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tables</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#getTables()
	 * @see #getWriteExcelFile()
	 * @generated
	 */
	EReference getWriteExcelFile_Tables();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#getUri()
	 * @see #getWriteExcelFile()
	 * @generated
	 */
	EAttribute getWriteExcelFile_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#isAppend <em>Append</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Append</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile#isAppend()
	 * @see #getWriteExcelFile()
	 * @generated
	 */
	EAttribute getWriteExcelFile_Append();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile <em>Read Excel File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Read Excel File</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile
	 * @generated
	 */
	EClass getReadExcelFile();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile#getUri()
	 * @see #getReadExcelFile()
	 * @generated
	 */
	EAttribute getReadExcelFile_Uri();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile#getSheets <em>Sheets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sheets</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile#getSheets()
	 * @see #getReadExcelFile()
	 * @generated
	 */
	EAttribute getReadExcelFile_Sheets();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells <em>Get Excel Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Excel Cells</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells
	 * @generated
	 */
	EClass getGetExcelCells();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells#getTable()
	 * @see #getGetExcelCells()
	 * @generated
	 */
	EReference getGetExcelCells_Table();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cells</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells#getCells()
	 * @see #getGetExcelCells()
	 * @generated
	 */
	EAttribute getGetExcelCells_Cells();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange <em>Get Excel Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Excel Range</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange
	 * @generated
	 */
	EClass getGetExcelRange();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange#getTable()
	 * @see #getGetExcelRange()
	 * @generated
	 */
	EReference getGetExcelRange_Table();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange#getRange <em>Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Range</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange#getRange()
	 * @see #getGetExcelRange()
	 * @generated
	 */
	EAttribute getGetExcelRange_Range();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells <em>Set Excel Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Excel Cells</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells
	 * @generated
	 */
	EClass getSetExcelCells();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getTable()
	 * @see #getSetExcelCells()
	 * @generated
	 */
	EReference getSetExcelCells_Table();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cells</em>'.
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells#getCells()
	 * @see #getSetExcelCells()
	 * @generated
	 */
	EReference getSetExcelCells_Cells();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommandsFactory getCommandsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.WriteExcelFileImpl <em>Write Excel File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.WriteExcelFileImpl
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getWriteExcelFile()
		 * @generated
		 */
		EClass WRITE_EXCEL_FILE = eINSTANCE.getWriteExcelFile();

		/**
		 * The meta object literal for the '<em><b>Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WRITE_EXCEL_FILE__TABLES = eINSTANCE.getWriteExcelFile_Tables();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_EXCEL_FILE__URI = eINSTANCE.getWriteExcelFile_Uri();

		/**
		 * The meta object literal for the '<em><b>Append</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_EXCEL_FILE__APPEND = eINSTANCE.getWriteExcelFile_Append();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.ReadExcelFileImpl <em>Read Excel File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.ReadExcelFileImpl
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getReadExcelFile()
		 * @generated
		 */
		EClass READ_EXCEL_FILE = eINSTANCE.getReadExcelFile();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READ_EXCEL_FILE__URI = eINSTANCE.getReadExcelFile_Uri();

		/**
		 * The meta object literal for the '<em><b>Sheets</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READ_EXCEL_FILE__SHEETS = eINSTANCE.getReadExcelFile_Sheets();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelCellsImpl <em>Get Excel Cells</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelCellsImpl
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getGetExcelCells()
		 * @generated
		 */
		EClass GET_EXCEL_CELLS = eINSTANCE.getGetExcelCells();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_EXCEL_CELLS__TABLE = eINSTANCE.getGetExcelCells_Table();

		/**
		 * The meta object literal for the '<em><b>Cells</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET_EXCEL_CELLS__CELLS = eINSTANCE.getGetExcelCells_Cells();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelRangeImpl <em>Get Excel Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.GetExcelRangeImpl
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getGetExcelRange()
		 * @generated
		 */
		EClass GET_EXCEL_RANGE = eINSTANCE.getGetExcelRange();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_EXCEL_RANGE__TABLE = eINSTANCE.getGetExcelRange_Table();

		/**
		 * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET_EXCEL_RANGE__RANGE = eINSTANCE.getGetExcelRange_Range();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.SetExcelCellsImpl <em>Set Excel Cells</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.SetExcelCellsImpl
		 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.impl.CommandsPackageImpl#getSetExcelCells()
		 * @generated
		 */
		EClass SET_EXCEL_CELLS = eINSTANCE.getSetExcelCells();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SET_EXCEL_CELLS__TABLE = eINSTANCE.getSetExcelCells_Table();

		/**
		 * The meta object literal for the '<em><b>Cells</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SET_EXCEL_CELLS__CELLS = eINSTANCE.getSetExcelCells_Cells();

	}

} //CommandsPackage
