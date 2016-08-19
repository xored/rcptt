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
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIHierarchyResponse#getUiElements <em>Ui Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIHierarchyResponse()
 * @model
 * @generated
 */
public interface UIHierarchyResponse extends Response {
	/**
	 * Returns the value of the '<em><b>Ui Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.tesla.core.protocol.UIElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ui Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Elements</em>' reference list.
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIHierarchyResponse_UiElements()
	 * @model
	 * @generated
	 */
	EList<UIElement> getUiElements();

} // UIHierarchyResponse
