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
package org.eclipse.rcptt.ecl.data.apache.poi.commands.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.rcptt.ecl.core.CorePackage;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsFactory;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelCells;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.GetExcelRange;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.SetExcelCells;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile;
import org.eclipse.rcptt.ecl.data.objects.ObjectsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommandsPackageImpl extends EPackageImpl implements CommandsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass writeExcelFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readExcelFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getExcelCellsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getExcelRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setExcelCellsEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CommandsPackageImpl() {
		super(eNS_URI, CommandsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CommandsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CommandsPackage init() {
		if (isInited) return (CommandsPackage)EPackage.Registry.INSTANCE.getEPackage(CommandsPackage.eNS_URI);

		// Obtain or create and register package
		CommandsPackageImpl theCommandsPackage = (CommandsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CommandsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CommandsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();
		ObjectsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCommandsPackage.createPackageContents();

		// Initialize created meta-data
		theCommandsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCommandsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CommandsPackage.eNS_URI, theCommandsPackage);
		return theCommandsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWriteExcelFile() {
		return writeExcelFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWriteExcelFile_Tables() {
		return (EReference)writeExcelFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteExcelFile_Uri() {
		return (EAttribute)writeExcelFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteExcelFile_Append() {
		return (EAttribute)writeExcelFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReadExcelFile() {
		return readExcelFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReadExcelFile_Uri() {
		return (EAttribute)readExcelFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReadExcelFile_Sheets() {
		return (EAttribute)readExcelFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGetExcelCells() {
		return getExcelCellsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetExcelCells_Table() {
		return (EReference)getExcelCellsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGetExcelCells_Cells() {
		return (EAttribute)getExcelCellsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGetExcelRange() {
		return getExcelRangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetExcelRange_Table() {
		return (EReference)getExcelRangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGetExcelRange_Range() {
		return (EAttribute)getExcelRangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetExcelCells() {
		return setExcelCellsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSetExcelCells_Table() {
		return (EReference)setExcelCellsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSetExcelCells_Cells() {
		return (EReference)setExcelCellsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommandsFactory getCommandsFactory() {
		return (CommandsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		writeExcelFileEClass = createEClass(WRITE_EXCEL_FILE);
		createEReference(writeExcelFileEClass, WRITE_EXCEL_FILE__TABLES);
		createEAttribute(writeExcelFileEClass, WRITE_EXCEL_FILE__URI);
		createEAttribute(writeExcelFileEClass, WRITE_EXCEL_FILE__APPEND);

		readExcelFileEClass = createEClass(READ_EXCEL_FILE);
		createEAttribute(readExcelFileEClass, READ_EXCEL_FILE__URI);
		createEAttribute(readExcelFileEClass, READ_EXCEL_FILE__SHEETS);

		getExcelCellsEClass = createEClass(GET_EXCEL_CELLS);
		createEReference(getExcelCellsEClass, GET_EXCEL_CELLS__TABLE);
		createEAttribute(getExcelCellsEClass, GET_EXCEL_CELLS__CELLS);

		getExcelRangeEClass = createEClass(GET_EXCEL_RANGE);
		createEReference(getExcelRangeEClass, GET_EXCEL_RANGE__TABLE);
		createEAttribute(getExcelRangeEClass, GET_EXCEL_RANGE__RANGE);

		setExcelCellsEClass = createEClass(SET_EXCEL_CELLS);
		createEReference(setExcelCellsEClass, SET_EXCEL_CELLS__TABLE);
		createEReference(setExcelCellsEClass, SET_EXCEL_CELLS__CELLS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ObjectsPackage theObjectsPackage = (ObjectsPackage)EPackage.Registry.INSTANCE.getEPackage(ObjectsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		writeExcelFileEClass.getESuperTypes().add(theCorePackage.getCommand());
		readExcelFileEClass.getESuperTypes().add(theCorePackage.getCommand());
		getExcelCellsEClass.getESuperTypes().add(theCorePackage.getCommand());
		getExcelRangeEClass.getESuperTypes().add(theCorePackage.getCommand());
		setExcelCellsEClass.getESuperTypes().add(theCorePackage.getCommand());

		// Initialize classes and features; add operations and parameters
		initEClass(writeExcelFileEClass, WriteExcelFile.class, "WriteExcelFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWriteExcelFile_Tables(), theObjectsPackage.getTable(), null, "tables", null, 1, -1, WriteExcelFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWriteExcelFile_Uri(), theEcorePackage.getEString(), "uri", null, 1, 1, WriteExcelFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWriteExcelFile_Append(), theEcorePackage.getEBoolean(), "append", "false", 0, 1, WriteExcelFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(readExcelFileEClass, ReadExcelFile.class, "ReadExcelFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReadExcelFile_Uri(), theEcorePackage.getEString(), "uri", null, 1, 1, ReadExcelFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReadExcelFile_Sheets(), theEcorePackage.getEString(), "sheets", null, 0, -1, ReadExcelFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getExcelCellsEClass, GetExcelCells.class, "GetExcelCells", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGetExcelCells_Table(), theObjectsPackage.getTable(), null, "table", null, 1, 1, GetExcelCells.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGetExcelCells_Cells(), theEcorePackage.getEString(), "cells", null, 1, -1, GetExcelCells.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getExcelRangeEClass, GetExcelRange.class, "GetExcelRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGetExcelRange_Table(), theObjectsPackage.getTable(), null, "table", null, 1, 1, GetExcelRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGetExcelRange_Range(), theEcorePackage.getEString(), "range", null, 1, 1, GetExcelRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setExcelCellsEClass, SetExcelCells.class, "SetExcelCells", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSetExcelCells_Table(), theObjectsPackage.getTable(), null, "table", null, 1, 1, SetExcelCells.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSetExcelCells_Cells(), theCorePackage.getEclMap(), null, "cells", null, 1, 1, SetExcelCells.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/ecl/docs
		createDocsAnnotations();
		// http://www.eclipse.org/ecl/input
		createInputAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ecl/docs</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createDocsAnnotations() {
		String source = "http://www.eclipse.org/ecl/docs";	
		addAnnotation
		  (writeExcelFileEClass, 
		   source, 
		   new String[] {
			 "description", "Writes tables from input pipe to into excel (xls or xlsx) file. Fails if file is not found or format is invalid.",
			 "returns", "The value of <code>tables</code> argument",
			 "example", "global [val supportedDevices [list\n\t[list \"Device Name\" \"Device Info\"]\n\t[list \"Device 1\" \"Info 1\"]\n\t[list \"Device 2\" \"Info 2\"]\n\t[list \"Device 3\" \"Info 3\"]\n]]\n\nglobal [val allDevices [list\n\t[list \"Device Name\" \"Device Info\"]\n\t[list \"Device 1\" \"Info 1\"]\n\t[list \"Device 2\" \"Info 2\"]\n\t[list \"Device 3\" \"Info 3\"]\n\t[list \"Device 4\" \"Info 4\"]\n\t[list \"Device 5\" \"Info 5\"]\n]]\n\n\nproc newTable [val table] [val name] {\n\t$table | list-as-table-data | set-page-name $name\n}\n\n\nemit [newTable $supportedDevices \"Supported devices\"] [newTable $allDevices \"All devices\"]\n\t| write-excel-file \"workspace:/excel/devices.xlsx\""
		   });	
		addAnnotation
		  (getWriteExcelFile_Tables(), 
		   source, 
		   new String[] {
			 "description", "Tables to write"
		   });	
		addAnnotation
		  (getWriteExcelFile_Uri(), 
		   source, 
		   new String[] {
			 "description", "URI to write Excel data to. Currently supported schemes are workspace:/ for files in workspace and file:/ for files on local file system"
		   });	
		addAnnotation
		  (readExcelFileEClass, 
		   source, 
		   new String[] {
			 "description", "Parses given excel file (xls or xlsx) into tables and write them to output pipe. Fails if file is not found or format is invalid.",
			 "returns", "List of <code>Table</code> EMF Objects",
			 "example", "global [val devicesInfo [\n\tread-excel-file \"workspace:/excel/devices.xlsx\" \"Supported devices\" \"All devices\"\n\t\t| to-list\n]]\n\n\nclear-log-view\n\n$devicesInfo | each [val table] {\n\tlog [format \"Page name: %s\" [$table | get pageName]]\n\t$table | list [get rows] | each [val row] {\n\t\t$row | list [get values] | each [val value] {\n\t\t\tlog $value\n\t\t}\n\t}\n}\n\nread-excel-file \"workspace:/excel/devices.xlsx\" \"Additional info\"\n\t| get rows | get values | log"
		   });	
		addAnnotation
		  (getReadExcelFile_Uri(), 
		   source, 
		   new String[] {
			 "description", "URI to read Excel data from. Currently supported schemes are workspace:/ for files in workspace and file:/ for files on local file system"
		   });	
		addAnnotation
		  (getReadExcelFile_Sheets(), 
		   source, 
		   new String[] {
			 "description", "List of Excel sheet names to read data from"
		   });	
		addAnnotation
		  (getExcelCellsEClass, 
		   source, 
		   new String[] {
			 "description", "Gets cell values by excel names and writes them into the output pipe",
			 "returns", "Cell values",
			 "example", "read-excel-file \"workspace:/excel/devices.xlsx\" \"Supported devices\"\n\t| get-excel-cells A1 | eq \"Device Name\" | verify-true"
		   });	
		addAnnotation
		  (getGetExcelCells_Table(), 
		   source, 
		   new String[] {
			 "description", "Table to get cells from"
		   });	
		addAnnotation
		  (getGetExcelCells_Cells(), 
		   source, 
		   new String[] {
			 "description", "Cell names"
		   });	
		addAnnotation
		  (getExcelRangeEClass, 
		   source, 
		   new String[] {
			 "description", "Gets range by excel name and writes it into the output pipe",
			 "returns", "<code>Table</code> EMF Object",
			 "example", "read-excel-file \"workspace:/excel/devices.xlsx\" \"Supported devices\"\n\t| get-excel-range \"A2:B4\" | list [get rows] | each [val row] {\n\t\t$row | list [get values] | each [val value] {\n\t\t\tlog $value\n\t\t}\n}"
		   });	
		addAnnotation
		  (getGetExcelRange_Table(), 
		   source, 
		   new String[] {
			 "description", "Table to get range from"
		   });	
		addAnnotation
		  (getGetExcelRange_Range(), 
		   source, 
		   new String[] {
			 "description", "Range name in the excel format (for example \"A1:B2\")"
		   });	
		addAnnotation
		  (setExcelCellsEClass, 
		   source, 
		   new String[] {
			 "description", "Sets cell values to the table",
			 "returns", "The value of \'table\' argument",
			 "example", "read-excel-file \"workspace:/excel/devices.xlsx\" \"Supported devices\"\n\t| set-excel-cells [map [entry \"A1\" \"New Device Name\"] [entry \"B1\" \"New Device Target\"]]\n\t| write-excel-file \"workspace:/excel/devices.xlsx\" -append"
		   });	
		addAnnotation
		  (getSetExcelCells_Table(), 
		   source, 
		   new String[] {
			 "description", "Table to set cell values to"
		   });	
		addAnnotation
		  (getSetExcelCells_Cells(), 
		   source, 
		   new String[] {
			 "description", "Map where key is cell name and value is cell value to set"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ecl/input</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createInputAnnotations() {
		String source = "http://www.eclipse.org/ecl/input";	
		addAnnotation
		  (getWriteExcelFile_Tables(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getGetExcelCells_Table(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getGetExcelRange_Table(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSetExcelCells_Table(), 
		   source, 
		   new String[] {
		   });
	}

} //CommandsPackageImpl
