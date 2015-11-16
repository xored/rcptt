/**
 */
package org.eclipse.rcptt.tesla.nattable.ecl.nattable;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage
 * @generated
 */
public interface NattableFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NattableFactory eINSTANCE = org.eclipse.rcptt.tesla.nattable.ecl.nattable.impl.NattableFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Nebula Nat Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nebula Nat Table</em>'.
	 * @generated
	 */
	NebulaNatTable createNebulaNatTable();

	/**
	 * Returns a new object of class '<em>Get Nat Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Nat Table</em>'.
	 * @generated
	 */
	GetNatTable createGetNatTable();

	/**
	 * Returns a new object of class '<em>Get Row Header</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Row Header</em>'.
	 * @generated
	 */
	GetRowHeader createGetRowHeader();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	NattablePackage getNattablePackage();

} //NattableFactory
