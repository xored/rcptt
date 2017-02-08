/**
 */
package org.eclipse.rcptt.tesla.core.protocol.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;

import org.eclipse.rcptt.tesla.core.protocol.raw.impl.ElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UI Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIElementImpl#isHasChildren <em>Has Children</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIElementImpl#getGenerationKind <em>Generation Kind</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UIElementImpl extends ElementImpl implements UIElement {
	/**
	 * The default value of the '{@link #isHasChildren() <em>Has Children</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasChildren()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_CHILDREN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasChildren() <em>Has Children</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasChildren()
	 * @generated
	 * @ordered
	 */
	protected boolean hasChildren = HAS_CHILDREN_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenerationKind() <em>Generation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationKind()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATION_KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGenerationKind() <em>Generation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationKind()
	 * @generated
	 * @ordered
	 */
	protected String generationKind = GENERATION_KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProtocolPackage.Literals.UI_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasChildren() {
		return hasChildren;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasChildren(boolean newHasChildren) {
		boolean oldHasChildren = hasChildren;
		hasChildren = newHasChildren;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.UI_ELEMENT__HAS_CHILDREN, oldHasChildren, hasChildren));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.UI_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGenerationKind() {
		return generationKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerationKind(String newGenerationKind) {
		String oldGenerationKind = generationKind;
		generationKind = newGenerationKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.UI_ELEMENT__GENERATION_KIND, oldGenerationKind, generationKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProtocolPackage.UI_ELEMENT__HAS_CHILDREN:
				return isHasChildren();
			case ProtocolPackage.UI_ELEMENT__NAME:
				return getName();
			case ProtocolPackage.UI_ELEMENT__GENERATION_KIND:
				return getGenerationKind();
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
			case ProtocolPackage.UI_ELEMENT__HAS_CHILDREN:
				setHasChildren((Boolean)newValue);
				return;
			case ProtocolPackage.UI_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ProtocolPackage.UI_ELEMENT__GENERATION_KIND:
				setGenerationKind((String)newValue);
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
			case ProtocolPackage.UI_ELEMENT__HAS_CHILDREN:
				setHasChildren(HAS_CHILDREN_EDEFAULT);
				return;
			case ProtocolPackage.UI_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProtocolPackage.UI_ELEMENT__GENERATION_KIND:
				setGenerationKind(GENERATION_KIND_EDEFAULT);
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
			case ProtocolPackage.UI_ELEMENT__HAS_CHILDREN:
				return hasChildren != HAS_CHILDREN_EDEFAULT;
			case ProtocolPackage.UI_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProtocolPackage.UI_ELEMENT__GENERATION_KIND:
				return GENERATION_KIND_EDEFAULT == null ? generationKind != null : !GENERATION_KIND_EDEFAULT.equals(generationKind);
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
		result.append(" (hasChildren: ");
		result.append(hasChildren);
		result.append(", name: ");
		result.append(name);
		result.append(", generationKind: ");
		result.append(generationKind);
		result.append(')');
		return result.toString();
	}

} //UIElementImpl
