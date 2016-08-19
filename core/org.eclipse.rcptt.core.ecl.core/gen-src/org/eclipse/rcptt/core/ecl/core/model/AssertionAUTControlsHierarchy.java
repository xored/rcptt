/**
 */
package org.eclipse.rcptt.core.ecl.core.model;

import org.eclipse.rcptt.ecl.core.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assertion AUT Controls Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy#getState <em>State</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getAssertionAUTControlsHierarchy()
 * @model
 * @generated
 */
public interface AssertionAUTControlsHierarchy extends Command {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getAssertionAUTControlsHierarchy_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState
	 * @see #setState(AssertionAUTControlsHierarchyState)
	 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getAssertionAUTControlsHierarchy_State()
	 * @model
	 * @generated
	 */
	AssertionAUTControlsHierarchyState getState();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchy#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.core.ecl.core.model.AssertionAUTControlsHierarchyState
	 * @see #getState()
	 * @generated
	 */
	void setState(AssertionAUTControlsHierarchyState value);

} // AssertionAUTControlsHierarchy
