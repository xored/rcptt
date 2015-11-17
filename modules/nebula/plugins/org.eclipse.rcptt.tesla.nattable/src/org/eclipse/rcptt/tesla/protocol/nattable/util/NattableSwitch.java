/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.rcptt.tesla.core.protocol.ElementCommand;

import org.eclipse.rcptt.tesla.core.protocol.raw.Command;

import org.eclipse.rcptt.tesla.protocol.nattable.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage
 * @generated
 */
public class NattableSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NattablePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NattableSwitch() {
		if (modelPackage == null) {
			modelPackage = NattablePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT: {
				NatTableMouseEvent natTableMouseEvent = (NatTableMouseEvent)theEObject;
				T result = caseNatTableMouseEvent(natTableMouseEvent);
				if (result == null) result = caseElementCommand(natTableMouseEvent);
				if (result == null) result = caseCommand(natTableMouseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NattablePackage.NAT_TABLE_CELL_MOUSE_EVENT: {
				NatTableCellMouseEvent natTableCellMouseEvent = (NatTableCellMouseEvent)theEObject;
				T result = caseNatTableCellMouseEvent(natTableCellMouseEvent);
				if (result == null) result = caseNatTableMouseEvent(natTableCellMouseEvent);
				if (result == null) result = caseElementCommand(natTableCellMouseEvent);
				if (result == null) result = caseCommand(natTableCellMouseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NattablePackage.NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT: {
				NatTableColumnHeaderMouseEvent natTableColumnHeaderMouseEvent = (NatTableColumnHeaderMouseEvent)theEObject;
				T result = caseNatTableColumnHeaderMouseEvent(natTableColumnHeaderMouseEvent);
				if (result == null) result = caseNatTableMouseEvent(natTableColumnHeaderMouseEvent);
				if (result == null) result = caseElementCommand(natTableColumnHeaderMouseEvent);
				if (result == null) result = caseCommand(natTableColumnHeaderMouseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NattablePackage.NAT_TABLE_ROW_HEADER_MOUSE_EVENT: {
				NatTableRowHeaderMouseEvent natTableRowHeaderMouseEvent = (NatTableRowHeaderMouseEvent)theEObject;
				T result = caseNatTableRowHeaderMouseEvent(natTableRowHeaderMouseEvent);
				if (result == null) result = caseNatTableMouseEvent(natTableRowHeaderMouseEvent);
				if (result == null) result = caseElementCommand(natTableRowHeaderMouseEvent);
				if (result == null) result = caseCommand(natTableRowHeaderMouseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nat Table Mouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nat Table Mouse Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNatTableMouseEvent(NatTableMouseEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nat Table Cell Mouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nat Table Cell Mouse Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNatTableCellMouseEvent(NatTableCellMouseEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nat Table Column Header Mouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nat Table Column Header Mouse Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNatTableColumnHeaderMouseEvent(NatTableColumnHeaderMouseEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nat Table Row Header Mouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nat Table Row Header Mouse Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNatTableRowHeaderMouseEvent(NatTableRowHeaderMouseEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommand(Command object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementCommand(ElementCommand object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //NattableSwitch
