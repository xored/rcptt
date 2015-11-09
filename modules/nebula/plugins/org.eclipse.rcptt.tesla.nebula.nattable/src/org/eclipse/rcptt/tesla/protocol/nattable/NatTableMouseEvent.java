/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;

import org.eclipse.rcptt.tesla.core.protocol.ElementCommand;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nat Table Mouse Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getRow <em>Row</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getButton <em>Button</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getStateMask <em>State Mask</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent()
 * @model
 * @generated
 */
public interface NatTableMouseEvent extends ElementCommand {
	/**
	 * Returns the value of the '<em><b>Row</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row</em>' attribute.
	 * @see #setRow(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_Row()
	 * @model default="0"
	 * @generated
	 */
	int getRow();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getRow <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row</em>' attribute.
	 * @see #getRow()
	 * @generated
	 */
	void setRow(int value);

	/**
	 * Returns the value of the '<em><b>Column</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' attribute.
	 * @see #setColumn(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_Column()
	 * @model default="0"
	 * @generated
	 */
	int getColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getColumn <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' attribute.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(int value);

	/**
	 * Returns the value of the '<em><b>Button</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Button</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Button</em>' attribute.
	 * @see #setButton(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_Button()
	 * @model
	 * @generated
	 */
	int getButton();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getButton <em>Button</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Button</em>' attribute.
	 * @see #getButton()
	 * @generated
	 */
	void setButton(int value);

	/**
	 * Returns the value of the '<em><b>State Mask</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Mask</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Mask</em>' attribute.
	 * @see #setStateMask(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_StateMask()
	 * @model
	 * @generated
	 */
	int getStateMask();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getStateMask <em>State Mask</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Mask</em>' attribute.
	 * @see #getStateMask()
	 * @generated
	 */
	void setStateMask(int value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"DOWN"</code>.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind
	 * @see #setKind(NatTableMouseEventKind)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_Kind()
	 * @model default="DOWN"
	 * @generated
	 */
	NatTableMouseEventKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(NatTableMouseEventKind value);

} // NatTableMouseEvent
