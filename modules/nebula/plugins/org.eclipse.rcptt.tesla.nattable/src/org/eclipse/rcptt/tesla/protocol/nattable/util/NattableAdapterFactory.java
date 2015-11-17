/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.rcptt.tesla.core.protocol.ElementCommand;

import org.eclipse.rcptt.tesla.core.protocol.raw.Command;

import org.eclipse.rcptt.tesla.protocol.nattable.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage
 * @generated
 */
public class NattableAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NattablePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NattableAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = NattablePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NattableSwitch<Adapter> modelSwitch =
		new NattableSwitch<Adapter>() {
			@Override
			public Adapter caseNatTableMouseEvent(NatTableMouseEvent object) {
				return createNatTableMouseEventAdapter();
			}
			@Override
			public Adapter caseNatTableCellMouseEvent(NatTableCellMouseEvent object) {
				return createNatTableCellMouseEventAdapter();
			}
			@Override
			public Adapter caseNatTableColumnHeaderMouseEvent(NatTableColumnHeaderMouseEvent object) {
				return createNatTableColumnHeaderMouseEventAdapter();
			}
			@Override
			public Adapter caseNatTableRowHeaderMouseEvent(NatTableRowHeaderMouseEvent object) {
				return createNatTableRowHeaderMouseEventAdapter();
			}
			@Override
			public Adapter caseCommand(Command object) {
				return createCommandAdapter();
			}
			@Override
			public Adapter caseElementCommand(ElementCommand object) {
				return createElementCommandAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent <em>Nat Table Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent
	 * @generated
	 */
	public Adapter createNatTableMouseEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent <em>Nat Table Cell Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent
	 * @generated
	 */
	public Adapter createNatTableCellMouseEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent <em>Nat Table Column Header Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent
	 * @generated
	 */
	public Adapter createNatTableColumnHeaderMouseEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent <em>Nat Table Row Header Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent
	 * @generated
	 */
	public Adapter createNatTableRowHeaderMouseEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.core.protocol.raw.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.core.protocol.raw.Command
	 * @generated
	 */
	public Adapter createCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.tesla.core.protocol.ElementCommand <em>Element Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.tesla.core.protocol.ElementCommand
	 * @generated
	 */
	public Adapter createElementCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //NattableAdapterFactory
