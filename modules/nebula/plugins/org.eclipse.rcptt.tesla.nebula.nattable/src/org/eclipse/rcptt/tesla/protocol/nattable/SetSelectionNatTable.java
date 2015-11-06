/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;

import org.eclipse.rcptt.tesla.core.protocol.ElementCommand;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Selection Nat Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getRow <em>Row</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getColumn <em>Column</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getSetSelectionNatTable()
 * @model
 * @generated
 */
public interface SetSelectionNatTable extends ElementCommand {
	/**
	 * Returns the value of the '<em><b>Row</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row</em>' attribute.
	 * @see #setRow(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getSetSelectionNatTable_Row()
	 * @model default="0"
	 * @generated
	 */
	int getRow();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getRow <em>Row</em>}' attribute.
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
	 * If the meaning of the '<em>Column</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' attribute.
	 * @see #setColumn(int)
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#getSetSelectionNatTable_Column()
	 * @model default="0"
	 * @generated
	 */
	int getColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.protocol.nattable.SetSelectionNatTable#getColumn <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' attribute.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(int value);

} // SetSelectionNatTable
