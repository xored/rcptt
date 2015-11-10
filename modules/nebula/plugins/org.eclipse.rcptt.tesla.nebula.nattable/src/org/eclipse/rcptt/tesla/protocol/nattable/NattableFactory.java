/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage
 * @generated
 */
public interface NattableFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NattableFactory eINSTANCE = org.eclipse.rcptt.tesla.protocol.nattable.impl.NattableFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Nat Table Mouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nat Table Mouse Event</em>'.
	 * @generated
	 */
	NatTableMouseEvent createNatTableMouseEvent();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	NattablePackage getNattablePackage();

} //NattableFactory