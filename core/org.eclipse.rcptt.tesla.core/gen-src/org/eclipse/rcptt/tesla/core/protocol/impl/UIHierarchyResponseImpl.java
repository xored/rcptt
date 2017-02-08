/**
 */
package org.eclipse.rcptt.tesla.core.protocol.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;
import org.eclipse.rcptt.tesla.core.protocol.UIElement;
import org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse;

import org.eclipse.rcptt.tesla.core.protocol.raw.impl.ResponseImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UI Hierarchy Response</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIHierarchyResponseImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIHierarchyResponseImpl#getUiElement <em>Ui Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UIHierarchyResponseImpl extends ResponseImpl implements UIHierarchyResponse {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UIElement> children;

	/**
	 * The cached value of the '{@link #getUiElement() <em>Ui Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiElement()
	 * @generated
	 * @ordered
	 */
	protected UIElement uiElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIHierarchyResponseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProtocolPackage.Literals.UI_HIERARCHY_RESPONSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UIElement> getChildren() {
		if (children == null) {
			children = new EObjectResolvingEList<UIElement>(UIElement.class, this, ProtocolPackage.UI_HIERARCHY_RESPONSE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIElement getUiElement() {
		if (uiElement != null && uiElement.eIsProxy()) {
			InternalEObject oldUiElement = (InternalEObject)uiElement;
			uiElement = (UIElement)eResolveProxy(oldUiElement);
			if (uiElement != oldUiElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT, oldUiElement, uiElement));
			}
		}
		return uiElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIElement basicGetUiElement() {
		return uiElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUiElement(UIElement newUiElement) {
		UIElement oldUiElement = uiElement;
		uiElement = newUiElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT, oldUiElement, uiElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__CHILDREN:
				return getChildren();
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT:
				if (resolve) return getUiElement();
				return basicGetUiElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UIElement>)newValue);
				return;
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT:
				setUiElement((UIElement)newValue);
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
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__CHILDREN:
				getChildren().clear();
				return;
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT:
				setUiElement((UIElement)null);
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
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__CHILDREN:
				return children != null && !children.isEmpty();
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENT:
				return uiElement != null;
		}
		return super.eIsSet(featureID);
	}

} //UIHierarchyResponseImpl
