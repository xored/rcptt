/**
 */
package org.eclipse.rcptt.core.ecl.core.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Assertion AUT Controls Hierarchy State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getAssertionAUTControlsHierarchyState()
 * @model
 * @generated
 */
public enum AssertionAUTControlsHierarchyState implements Enumerator {
	/**
	 * The '<em><b>Get Root</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_ROOT_VALUE
	 * @generated
	 * @ordered
	 */
	GET_ROOT(0, "GetRoot", "GetRoot"),

	/**
	 * The '<em><b>Get Node</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_NODE_VALUE
	 * @generated
	 * @ordered
	 */
	GET_NODE(0, "GetNode", "GetNode"),

	/**
	 * The '<em><b>Clear</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLEAR_VALUE
	 * @generated
	 * @ordered
	 */
	CLEAR(0, "Clear", "Clear");

	/**
	 * The '<em><b>Get Root</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Get Root</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET_ROOT
	 * @model name="GetRoot"
	 * @generated
	 * @ordered
	 */
	public static final int GET_ROOT_VALUE = 0;

	/**
	 * The '<em><b>Get Node</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Get Node</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET_NODE
	 * @model name="GetNode"
	 * @generated
	 * @ordered
	 */
	public static final int GET_NODE_VALUE = 0;

	/**
	 * The '<em><b>Clear</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Clear</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLEAR
	 * @model name="Clear"
	 * @generated
	 * @ordered
	 */
	public static final int CLEAR_VALUE = 0;

	/**
	 * An array of all the '<em><b>Assertion AUT Controls Hierarchy State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AssertionAUTControlsHierarchyState[] VALUES_ARRAY =
		new AssertionAUTControlsHierarchyState[] {
			GET_ROOT,
			GET_NODE,
			CLEAR,
		};

	/**
	 * A public read-only list of all the '<em><b>Assertion AUT Controls Hierarchy State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AssertionAUTControlsHierarchyState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Assertion AUT Controls Hierarchy State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssertionAUTControlsHierarchyState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssertionAUTControlsHierarchyState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assertion AUT Controls Hierarchy State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssertionAUTControlsHierarchyState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssertionAUTControlsHierarchyState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assertion AUT Controls Hierarchy State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssertionAUTControlsHierarchyState get(int value) {
		switch (value) {
			case GET_ROOT_VALUE: return GET_ROOT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AssertionAUTControlsHierarchyState(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //AssertionAUTControlsHierarchyState
