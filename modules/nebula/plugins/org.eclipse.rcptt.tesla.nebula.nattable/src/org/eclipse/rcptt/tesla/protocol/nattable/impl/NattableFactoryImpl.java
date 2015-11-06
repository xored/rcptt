/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.rcptt.tesla.protocol.nattable.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NattableFactoryImpl extends EFactoryImpl implements NattableFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NattableFactory init() {
		try {
			NattableFactory theNattableFactory = (NattableFactory)EPackage.Registry.INSTANCE.getEFactory(NattablePackage.eNS_URI);
			if (theNattableFactory != null) {
				return theNattableFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NattableFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NattableFactoryImpl() {
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
			case NattablePackage.SET_SELECTION_NAT_TABLE: return createSetSelectionNatTable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetSelectionNatTable createSetSelectionNatTable() {
		SetSelectionNatTableImpl setSelectionNatTable = new SetSelectionNatTableImpl();
		return setSelectionNatTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NattablePackage getNattablePackage() {
		return (NattablePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NattablePackage getPackage() {
		return NattablePackage.eINSTANCE;
	}

} //NattableFactoryImpl
