/**
 */
package org.eclipse.rcptt.core.ecl.core.model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.rcptt.core.ecl.core.model.AUTControlsHierarchy;
import org.eclipse.rcptt.core.ecl.core.model.AUTControlsHierarchyState;
import org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage;

import org.eclipse.rcptt.ecl.core.impl.CommandImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AUT Controls Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AUTControlsHierarchyImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AUTControlsHierarchyImpl#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AUTControlsHierarchyImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.impl.AUTControlsHierarchyImpl#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AUTControlsHierarchyImpl extends CommandImpl implements AUTControlsHierarchy {
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
	protected static final AUTControlsHierarchyState STATE_EDEFAULT = AUTControlsHierarchyState.HIGHLIGHT_WIDGET;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected AUTControlsHierarchyState state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final String KIND_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected String kind = KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AUTControlsHierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Q7CorePackage.Literals.AUT_CONTROLS_HIERARCHY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.AUT_CONTROLS_HIERARCHY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AUTControlsHierarchyState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(AUTControlsHierarchyState newState) {
		AUTControlsHierarchyState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.AUT_CONTROLS_HIERARCHY__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.AUT_CONTROLS_HIERARCHY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(String newKind) {
		String oldKind = kind;
		kind = newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7CorePackage.AUT_CONTROLS_HIERARCHY__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__ID:
				return getId();
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__STATE:
				return getState();
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__DESCRIPTION:
				return getDescription();
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__KIND:
				return getKind();
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
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__ID:
				setId((String)newValue);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__STATE:
				setState((AUTControlsHierarchyState)newValue);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__KIND:
				setKind((String)newValue);
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
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__ID:
				setId(ID_EDEFAULT);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__STATE:
				setState(STATE_EDEFAULT);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__KIND:
				setKind(KIND_EDEFAULT);
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
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__STATE:
				return state != STATE_EDEFAULT;
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Q7CorePackage.AUT_CONTROLS_HIERARCHY__KIND:
				return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
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
		result.append(", description: ");
		result.append(description);
		result.append(", kind: ");
		result.append(kind);
		result.append(')');
		return result.toString();
	}

} //AUTControlsHierarchyImpl
