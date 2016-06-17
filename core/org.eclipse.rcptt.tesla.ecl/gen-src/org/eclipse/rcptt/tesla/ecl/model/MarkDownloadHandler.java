/**
 */
package org.eclipse.rcptt.tesla.ecl.model;

import org.eclipse.rcptt.ecl.core.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mark Download Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.ecl.model.MarkDownloadHandler#getHandlerName <em>Handler Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.ecl.model.TeslaPackage#getMarkDownloadHandler()
 * @model
 * @generated
 */
public interface MarkDownloadHandler extends Command {
	/**
	 * Returns the value of the '<em><b>Handler Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Name</em>' attribute.
	 * @see #setHandlerName(String)
	 * @see org.eclipse.rcptt.tesla.ecl.model.TeslaPackage#getMarkDownloadHandler_HandlerName()
	 * @model required="true"
	 * @generated
	 */
	String getHandlerName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.ecl.model.MarkDownloadHandler#getHandlerName <em>Handler Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Name</em>' attribute.
	 * @see #getHandlerName()
	 * @generated
	 */
	void setHandlerName(String value);

} // MarkDownloadHandler
