/**
 */
package org.eclipse.rcptt.core.ecl.core.model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy;
import org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState;
import org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage;

import org.eclipse.rcptt.ecl.core.impl.CommandImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assertion AUT Controls Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AssertionAUTControlsHierarchyImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AssertionAUTControlsHierarchyImpl#getState <em>State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssertionAUTControlsHierarchyImpl extends CommandImpl implements AssertionAUTControlsHierarchy {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final AssertionAUTControlsHierarchyState STATE_EDEFAULT = AssertionAUTControlsHierarchyState.GET_ROOT;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected AssertionAUTControlsHierarchyState state = STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssertionAUTControlsHierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Q7CorePackage.Literals.ASSERTION_AUT_CONTROLS_HIERARCHY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertionAUTControlsHierarchyState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(AssertionAUTControlsHierarchyState newState) {
		AssertionAUTControlsHierarchyState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__ID:
				return getId();
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__STATE:
				return getState();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__ID:
				setId((String)newValue);
				return;
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__STATE:
				setState((AssertionAUTControlsHierarchyState)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__ID:
				setId(ID_EDEFAULT);
				return;
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__STATE:
				setState(STATE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Q7CorePackage.ASSERTION_AUT_CONTROLS_HIERARCHY__STATE:
				return state != STATE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", state: ");
		result.append(state);
		result.append(')');
		return result.toString();
	}

} //AssertionAUTControlsHierarchyImpl
