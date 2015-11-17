/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nat Table Row Header Mouse Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableRowHeaderMouseEvent()
 * @model
 * @generated
 */
public interface NatTableRowHeaderMouseEvent extends NatTableMouseEvent {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableRowHeaderMouseEvent_Index()
	 * @model default="-1"
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableRowHeaderMouseEvent_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

} // NatTableRowHeaderMouseEvent
