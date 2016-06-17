/**
 */
package org.eclipse.rcptt.tesla.core.protocol;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mark Rap Download Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.MarkRapDownloadHandler#getHandler <em>Handler</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getMarkRapDownloadHandler()
 * @model
 * @generated
 */
public interface MarkRapDownloadHandler extends Command {
	/**
	 * Returns the value of the '<em><b>Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler</em>' attribute.
	 * @see #setHandler(String)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getMarkRapDownloadHandler_Handler()
	 * @model
	 * @generated
	 */
	String getHandler();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.MarkRapDownloadHandler#getHandler <em>Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler</em>' attribute.
	 * @see #getHandler()
	 * @generated
	 */
	void setHandler(String value);

} // MarkRapDownloadHandler
