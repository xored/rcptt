/**
 */
package org.eclipse.rcptt.tesla.core.protocol;

import org.eclipse.rcptt.tesla.core.protocol.raw.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UI Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#isHasChildren <em>Has Children</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#getGenerationKind <em>Generation Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIElement()
 * @model
 * @generated
 */
public interface UIElement extends Element {
	/**
	 * Returns the value of the '<em><b>Has Children</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Children</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Children</em>' attribute.
	 * @see #setHasChildren(boolean)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIElement_HasChildren()
	 * @model
	 * @generated
	 */
	boolean isHasChildren();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#isHasChildren <em>Has Children</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Children</em>' attribute.
	 * @see #isHasChildren()
	 * @generated
	 */
	void setHasChildren(boolean value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Generation Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generation Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generation Kind</em>' attribute.
	 * @see #setGenerationKind(String)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getUIElement_GenerationKind()
	 * @model
	 * @generated
	 */
	String getGenerationKind();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.UIElement#getGenerationKind <em>Generation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generation Kind</em>' attribute.
	 * @see #getGenerationKind()
	 * @generated
	 */
	void setGenerationKind(String value);

} // UIElement
