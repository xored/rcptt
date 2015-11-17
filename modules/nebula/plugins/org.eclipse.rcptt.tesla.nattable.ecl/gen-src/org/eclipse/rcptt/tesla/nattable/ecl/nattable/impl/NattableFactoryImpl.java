/**
 */
package org.eclipse.rcptt.tesla.nattable.ecl.nattable.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetNatTable;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NebulaNatTable;

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
			case NattablePackage.NEBULA_NAT_TABLE: return createNebulaNatTable();
			case NattablePackage.GET_NAT_TABLE: return createGetNatTable();
			case NattablePackage.GET_ROW_HEADER: return createGetRowHeader();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NebulaNatTable createNebulaNatTable() {
		NebulaNatTableImpl nebulaNatTable = new NebulaNatTableImpl();
		return nebulaNatTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetNatTable createGetNatTable() {
		GetNatTableImpl getNatTable = new GetNatTableImpl();
		return getNatTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetRowHeader createGetRowHeader() {
		GetRowHeaderImpl getRowHeader = new GetRowHeaderImpl();
		return getRowHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
