/**
 */
package org.eclipse.rcptt.workspace;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.workspace.WorkspaceData#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.WorkspaceData#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWorkspaceData()
 * @model
 * @generated
 */
public interface WorkspaceData extends EObject {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' containment reference.
	 * @see #setContent(WSRoot)
	 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWorkspaceData_Content()
	 * @model containment="true"
	 * @generated
	 */
	WSRoot getContent();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.workspace.WorkspaceData#getContent <em>Content</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' containment reference.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(WSRoot value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWorkspaceData_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.workspace.WorkspaceData#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // WorkspaceData
