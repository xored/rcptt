/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;

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
			case NattablePackage.NAT_TABLE_CELL_MOUSE_EVENT: return createNatTableCellMouseEvent();
			case NattablePackage.NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT: return createNatTableColumnHeaderMouseEvent();
			case NattablePackage.NAT_TABLE_ROW_HEADER_MOUSE_EVENT: return createNatTableRowHeaderMouseEvent();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT_KIND:
				return createNatTableMouseEventKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT_KIND:
				return convertNatTableMouseEventKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NatTableCellMouseEvent createNatTableCellMouseEvent() {
		NatTableCellMouseEventImpl natTableCellMouseEvent = new NatTableCellMouseEventImpl();
		return natTableCellMouseEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NatTableColumnHeaderMouseEvent createNatTableColumnHeaderMouseEvent() {
		NatTableColumnHeaderMouseEventImpl natTableColumnHeaderMouseEvent = new NatTableColumnHeaderMouseEventImpl();
		return natTableColumnHeaderMouseEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NatTableRowHeaderMouseEvent createNatTableRowHeaderMouseEvent() {
		NatTableRowHeaderMouseEventImpl natTableRowHeaderMouseEvent = new NatTableRowHeaderMouseEventImpl();
		return natTableRowHeaderMouseEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NatTableMouseEventKind createNatTableMouseEventKindFromString(EDataType eDataType, String initialValue) {
		NatTableMouseEventKind result = NatTableMouseEventKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNatTableMouseEventKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
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
