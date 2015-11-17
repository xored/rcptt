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
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getButton <em>Button</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getStateMask <em>State Mask</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isColumnHeader <em>Column Header</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isRowHeader <em>Row Header</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent()
 * @model abstract="true"
 * @generated
 */
public interface NatTableMouseEvent extends ElementCommand {
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

	/**
	 * Returns the value of the '<em><b>Column Header</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Header</em>' attribute.
	 * @see #setColumnHeader(boolean)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_ColumnHeader()
	 * @model default="false"
	 * @generated
	 */
	boolean isColumnHeader();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isColumnHeader <em>Column Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Header</em>' attribute.
	 * @see #isColumnHeader()
	 * @generated
	 */
	void setColumnHeader(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Header</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Header</em>' attribute.
	 * @see #setRowHeader(boolean)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableMouseEvent_RowHeader()
	 * @model default="false"
	 * @generated
	 */
	boolean isRowHeader();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isRowHeader <em>Row Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Header</em>' attribute.
	 * @see #isRowHeader()
	 * @generated
	 */
	void setRowHeader(boolean value);

} // NatTableMouseEvent
