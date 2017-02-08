/**
 */
package org.eclipse.rcptt.tesla.core.protocol;

import org.eclipse.emf.common.util.EList;

import org.eclipse.rcptt.tesla.core.protocol.raw.Response;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UI Hierarchy Response</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse#getUiElement <em>Ui Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIHierarchyResponse()
 * @model
 * @generated
 */
public interface UIHierarchyResponse extends Response {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.tesla.core.protocol.UIElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIHierarchyResponse_Children()
	 * @model
	 * @generated
	 */
	EList<UIElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Ui Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ui Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Element</em>' reference.
	 * @see #setUiElement(UIElement)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIHierarchyResponse_UiElement()
	 * @model
	 * @generated
	 */
	UIElement getUiElement();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse#getUiElement <em>Ui Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Element</em>' reference.
	 * @see #getUiElement()
	 * @generated
	 */
	void setUiElement(UIElement value);

} // UIHierarchyResponse
