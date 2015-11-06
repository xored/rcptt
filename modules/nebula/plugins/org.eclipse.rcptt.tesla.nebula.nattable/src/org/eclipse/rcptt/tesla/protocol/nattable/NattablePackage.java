/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;

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
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory
 * @model kind="package"
 * @generated
 */
public interface NattablePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "nattable";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/rcptt/tesla/nebula/nattable";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.rcptt.tesla.nebula.nattable";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NattablePackage eINSTANCE = org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.SetSelectionNatTableImpl <em>Set Selection Nat Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.SetSelectionNatTableImpl
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getSetSelectionNatTable()
	 * @generated
	 */
	int SET_SELECTION_NAT_TABLE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_SELECTION_NAT_TABLE__ID = ProtocolPackage.ELEMENT_COMMAND__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_SELECTION_NAT_TABLE__ELEMENT = ProtocolPackage.ELEMENT_COMMAND__ELEMENT;

	/**
	 * The feature id for the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_SELECTION_NAT_TABLE__ROW = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_SELECTION_NAT_TABLE__COLUMN = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Set Selection Nat Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_SELECTION_NAT_TABLE_FEATURE_COUNT = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable <em>Set Selection Nat Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Selection Nat Table</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable
	 * @generated
	 */
	EClass getSetSelectionNatTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getRow()
	 * @see #getSetSelectionNatTable()
	 * @generated
	 */
	EAttribute getSetSelectionNatTable_Row();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getColumn()
	 * @see #getSetSelectionNatTable()
	 * @generated
	 */
	EAttribute getSetSelectionNatTable_Column();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NattableFactory getNattableFactory();

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
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.SetSelectionNatTableImpl <em>Set Selection Nat Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.SetSelectionNatTableImpl
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getSetSelectionNatTable()
		 * @generated
		 */
		EClass SET_SELECTION_NAT_TABLE = eINSTANCE.getSetSelectionNatTable();

		/**
		 * The meta object literal for the '<em><b>Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SET_SELECTION_NAT_TABLE__ROW = eINSTANCE.getSetSelectionNatTable_Row();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SET_SELECTION_NAT_TABLE__COLUMN = eINSTANCE.getSetSelectionNatTable_Column();

	}

} //NattablePackage
