/**
 */
package org.eclipse.rcptt.tesla.core.protocol.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

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
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.UIHierarchyResponseImpl#getUiElements <em>Ui Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UIHierarchyResponseImpl extends ResponseImpl implements UIHierarchyResponse {
	/**
	 * The cached value of the '{@link #getUiElements() <em>Ui Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiElements()
	 * @generated
	 * @ordered
	 */
	protected EList<UIElement> uiElements;

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
	public EList<UIElement> getUiElements() {
		if (uiElements == null) {
			uiElements = new EObjectResolvingEList<UIElement>(UIElement.class, this, ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENTS);
		}
		return uiElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENTS:
				return getUiElements();
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
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENTS:
				getUiElements().clear();
				getUiElements().addAll((Collection<? extends UIElement>)newValue);
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
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENTS:
				getUiElements().clear();
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
			case ProtocolPackage.UI_HIERARCHY_RESPONSE__UI_ELEMENTS:
				return uiElements != null && !uiElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UIHierarchyResponseImpl
