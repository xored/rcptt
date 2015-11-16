/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nat Table Cell Mouse Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getRow <em>Row</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getColumn <em>Column</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableCellMouseEvent()
 * @model
 * @generated
 */
public interface NatTableCellMouseEvent extends NatTableMouseEvent {
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
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableCellMouseEvent_Row()
	 * @model default="0"
	 * @generated
	 */
	int getRow();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getRow <em>Row</em>}' attribute.
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
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getNatTableCellMouseEvent_Column()
	 * @model default="0"
	 * @generated
	 */
	int getColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getColumn <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' attribute.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(int value);

} // NatTableCellMouseEvent
