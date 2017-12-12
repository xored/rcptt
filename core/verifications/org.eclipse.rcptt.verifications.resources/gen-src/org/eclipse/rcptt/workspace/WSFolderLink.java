/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.workspace;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>WS Folder Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.rcptt.workspace.WSFolderLink#getFolders <em>Folders</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.WSFolderLink#getFiles <em>Files</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWSFolderLink()
 * @model
 * @generated
 */
public interface WSFolderLink extends WSLink {

	/**
	 * Returns the value of the '<em><b>Folders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.workspace.WSFolderLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folders</em>' containment reference list.
	 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWSFolderLink_Folders()
	 * @model containment="true"
	 * @generated
	 */
	EList<WSFolderLink> getFolders();

	/**
	 * Returns the value of the '<em><b>Files</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.workspace.WSFileLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Files</em>' containment reference list.
	 * @see org.eclipse.rcptt.workspace.WorkspacePackage#getWSFolderLink_Files()
	 * @model containment="true"
	 * @generated
	 */
	EList<WSFileLink> getFiles();
} // WSFolderLink
