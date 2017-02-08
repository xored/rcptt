/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.core.ecl.core.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Control Hierarchy State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.core.ecl.core.model.Q7CorePackage#getControlHierarchyState()
 * @model
 * @generated
 */
public enum ControlHierarchyState implements Enumerator {
	/**
	 * The '<em><b>HIGHLIGHT WIDGET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIGHLIGHT_WIDGET_VALUE
	 * @generated
	 * @ordered
	 */
	HIGHLIGHT_WIDGET(0, "HIGHLIGHT_WIDGET", "HIGHLIGHT_WIDGET"),

	/**
	 * The '<em><b>UPDATE ASSERT WINDOW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPDATE_ASSERT_WINDOW_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATE_ASSERT_WINDOW(1, "UPDATE_ASSERT_WINDOW", "UPDATE_ASSERT_WINDOW"),

	/**
	 * The '<em><b>GET ELEMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_ELEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	GET_ELEMENT(2, "GET_ELEMENT", "GET_ELEMENT"),

	/**
	 * The '<em><b>GET PARENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_PARENT_VALUE
	 * @generated
	 * @ordered
	 */
	GET_PARENT(3, "GET_PARENT", "GET_PARENT");

	/**
	 * The '<em><b>HIGHLIGHT WIDGET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HIGHLIGHT WIDGET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HIGHLIGHT_WIDGET
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HIGHLIGHT_WIDGET_VALUE = 0;

	/**
	 * The '<em><b>UPDATE ASSERT WINDOW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPDATE ASSERT WINDOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPDATE_ASSERT_WINDOW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UPDATE_ASSERT_WINDOW_VALUE = 1;

	/**
	 * The '<em><b>GET ELEMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GET ELEMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET_ELEMENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GET_ELEMENT_VALUE = 2;

	/**
	 * The '<em><b>GET PARENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GET PARENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET_PARENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GET_PARENT_VALUE = 3;

	/**
	 * An array of all the '<em><b>Control Hierarchy State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ControlHierarchyState[] VALUES_ARRAY =
		new ControlHierarchyState[] {
			HIGHLIGHT_WIDGET,
			UPDATE_ASSERT_WINDOW,
			GET_ELEMENT,
			GET_PARENT,
		};

	/**
	 * A public read-only list of all the '<em><b>Control Hierarchy State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ControlHierarchyState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Control Hierarchy State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ControlHierarchyState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ControlHierarchyState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Control Hierarchy State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ControlHierarchyState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ControlHierarchyState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Control Hierarchy State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ControlHierarchyState get(int value) {
		switch (value) {
			case HIGHLIGHT_WIDGET_VALUE: return HIGHLIGHT_WIDGET;
			case UPDATE_ASSERT_WINDOW_VALUE: return UPDATE_ASSERT_WINDOW;
			case GET_ELEMENT_VALUE: return GET_ELEMENT;
			case GET_PARENT_VALUE: return GET_PARENT;
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
	private ControlHierarchyState(int value, String name, String literal) {
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
	
} //ControlHierarchyState
