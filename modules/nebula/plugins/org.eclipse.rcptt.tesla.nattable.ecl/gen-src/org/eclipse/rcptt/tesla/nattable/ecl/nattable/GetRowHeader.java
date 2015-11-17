/**
 */
package org.eclipse.rcptt.tesla.nattable.ecl.nattable;

import org.eclipse.rcptt.tesla.ecl.model.Selector;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Row Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage#getGetRowHeader()
 * @model
 * @generated
 */
public interface GetRowHeader extends Selector {

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
	 * @see org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage#getGetRowHeader_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);
} // GetRowHeader
