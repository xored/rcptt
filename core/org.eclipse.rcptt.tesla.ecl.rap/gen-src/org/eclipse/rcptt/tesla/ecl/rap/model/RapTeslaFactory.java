/**
 */
package org.eclipse.rcptt.tesla.ecl.rap.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.ecl.rap.model.RapTeslaPackage
 * @generated
 */
public interface RapTeslaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RapTeslaFactory eINSTANCE = org.eclipse.rcptt.tesla.ecl.rap.model.impl.RapTeslaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Run Js</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Js</em>'.
	 * @generated
	 */
	RunJs createRunJs();

	/**
	 * Returns a new object of class '<em>Exec Without Js</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exec Without Js</em>'.
	 * @generated
	 */
	ExecWithoutJs createExecWithoutJs();

	/**
	 * Returns a new object of class '<em>Set Download Result File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Download Result File</em>'.
	 * @generated
	 */
	SetDownloadResultFile createSetDownloadResultFile();

	/**
	 * Returns a new object of class '<em>Verify Download File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Verify Download File</em>'.
	 * @generated
	 */
	VerifyDownloadFile createVerifyDownloadFile();

	/**
	 * Returns a new object of class '<em>Mark Download Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mark Download Handler</em>'.
	 * @generated
	 */
	MarkDownloadHandler createMarkDownloadHandler();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RapTeslaPackage getRapTeslaPackage();

} //RapTeslaFactory
