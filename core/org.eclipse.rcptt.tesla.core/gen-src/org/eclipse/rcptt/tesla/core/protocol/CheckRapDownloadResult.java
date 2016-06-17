/**
 */
package org.eclipse.rcptt.tesla.core.protocol;

import org.eclipse.rcptt.tesla.core.protocol.raw.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Check Rap Download Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.CheckRapDownloadResult#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.CheckRapDownloadResult#getBase64Content <em>Base64 Content</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getCheckRapDownloadResult()
 * @model
 * @generated
 */
public interface CheckRapDownloadResult extends Command {
	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getCheckRapDownloadResult_File()
	 * @model
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.CheckRapDownloadResult#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Base64 Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base64 Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base64 Content</em>' attribute.
	 * @see #setBase64Content(String)
	 * @see org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage#getCheckRapDownloadResult_Base64Content()
	 * @model
	 * @generated
	 */
	String getBase64Content();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.tesla.core.protocol.CheckRapDownloadResult#getBase64Content <em>Base64 Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base64 Content</em>' attribute.
	 * @see #getBase64Content()
	 * @generated
	 */
	void setBase64Content(String value);

} // CheckRapDownloadResult
