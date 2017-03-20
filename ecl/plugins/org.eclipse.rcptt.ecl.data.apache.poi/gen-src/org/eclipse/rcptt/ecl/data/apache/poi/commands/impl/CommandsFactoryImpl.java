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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.*;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsFactory;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.CommandsPackage;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.ReadExcelFile;
import org.eclipse.rcptt.ecl.data.apache.poi.commands.WriteExcelFile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommandsFactoryImpl extends EFactoryImpl implements CommandsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CommandsFactory init() {
		try {
			CommandsFactory theCommandsFactory = (CommandsFactory)EPackage.Registry.INSTANCE.getEFactory(CommandsPackage.eNS_URI);
			if (theCommandsFactory != null) {
				return theCommandsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CommandsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommandsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CommandsPackage.WRITE_EXCEL_FILE: return createWriteExcelFile();
			case CommandsPackage.READ_EXCEL_FILE: return createReadExcelFile();
			case CommandsPackage.GET_EXCEL_CELLS: return createGetExcelCells();
			case CommandsPackage.GET_EXCEL_RANGE: return createGetExcelRange();
			case CommandsPackage.SET_EXCEL_CELLS: return createSetExcelCells();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WriteExcelFile createWriteExcelFile() {
		WriteExcelFileImpl writeExcelFile = new WriteExcelFileImpl();
		return writeExcelFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadExcelFile createReadExcelFile() {
		ReadExcelFileImpl readExcelFile = new ReadExcelFileImpl();
		return readExcelFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GetExcelCells createGetExcelCells() {
		GetExcelCellsImpl getExcelCells = new GetExcelCellsImpl();
		return getExcelCells;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GetExcelRange createGetExcelRange() {
		GetExcelRangeImpl getExcelRange = new GetExcelRangeImpl();
		return getExcelRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetExcelCells createSetExcelCells() {
		SetExcelCellsImpl setExcelCells = new SetExcelCellsImpl();
		return setExcelCells;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommandsPackage getCommandsPackage() {
		return (CommandsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CommandsPackage getPackage() {
		return CommandsPackage.eINSTANCE;
	}

} //CommandsFactoryImpl
