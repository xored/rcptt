/**
 */
package org.eclipse.rcptt.core.scenario;

import java.util.Map;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.core.scenario.CapabilityContext#getReferences <em>References</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.core.scenario.ScenarioPackage#getCapabilityContext()
 * @model
 * @generated
 */
public interface CapabilityContext extends Context {

	/**
	 * Returns the value of the '<em><b>References</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' reference.
	 * @see #setReferences(Map.Entry)
	 * @see org.eclipse.rcptt.core.scenario.ScenarioPackage#getCapabilityContext_References()
	 * @model mapType="org.eclipse.rcptt.core.scenario.StringListToStringListMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	Map.Entry<EList<String>, EList<String>> getReferences();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.core.scenario.CapabilityContext#getReferences <em>References</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>References</em>' reference.
	 * @see #getReferences()
	 * @generated
	 */
	void setReferences(Map.Entry<EList<String>, EList<String>> value);
} // CapabilityContext
