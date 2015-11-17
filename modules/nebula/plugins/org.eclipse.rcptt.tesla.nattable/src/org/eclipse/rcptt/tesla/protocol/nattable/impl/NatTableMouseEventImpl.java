/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nat Table Mouse Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#getButton <em>Button</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#getStateMask <em>State Mask</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#isColumnHeader <em>Column Header</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl#isRowHeader <em>Row Header</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NatTableMouseEventImpl extends MinimalEObjectImpl.Container implements NatTableMouseEvent {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected Element element;

	/**
	 * The default value of the '{@link #getButton() <em>Button</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getButton()
	 * @generated
	 * @ordered
	 */
	protected static final int BUTTON_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getButton() <em>Button</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getButton()
	 * @generated
	 * @ordered
	 */
	protected int button = BUTTON_EDEFAULT;

	/**
	 * The default value of the '{@link #getStateMask() <em>State Mask</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateMask()
	 * @generated
	 * @ordered
	 */
	protected static final int STATE_MASK_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStateMask() <em>State Mask</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateMask()
	 * @generated
	 * @ordered
	 */
	protected int stateMask = STATE_MASK_EDEFAULT;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final NatTableMouseEventKind KIND_EDEFAULT = NatTableMouseEventKind.DOWN;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected NatTableMouseEventKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #isColumnHeader() <em>Column Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColumnHeader()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLUMN_HEADER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isColumnHeader() <em>Column Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColumnHeader()
	 * @generated
	 * @ordered
	 */
	protected boolean columnHeader = COLUMN_HEADER_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowHeader() <em>Row Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowHeader()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_HEADER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowHeader() <em>Row Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowHeader()
	 * @generated
	 * @ordered
	 */
	protected boolean rowHeader = ROW_HEADER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NatTableMouseEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NattablePackage.Literals.NAT_TABLE_MOUSE_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElement(Element newElement, NotificationChain msgs) {
		Element oldElement = element;
		element = newElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT, oldElement, newElement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElement(Element newElement) {
		if (newElement != element) {
			NotificationChain msgs = null;
			if (element != null)
				msgs = ((InternalEObject)element).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT, null, msgs);
			if (newElement != null)
				msgs = ((InternalEObject)newElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT, null, msgs);
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT, newElement, newElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getButton() {
		return button;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setButton(int newButton) {
		int oldButton = button;
		button = newButton;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__BUTTON, oldButton, button));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getStateMask() {
		return stateMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStateMask(int newStateMask) {
		int oldStateMask = stateMask;
		stateMask = newStateMask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__STATE_MASK, oldStateMask, stateMask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NatTableMouseEventKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(NatTableMouseEventKind newKind) {
		NatTableMouseEventKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isColumnHeader() {
		return columnHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColumnHeader(boolean newColumnHeader) {
		boolean oldColumnHeader = columnHeader;
		columnHeader = newColumnHeader;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER, oldColumnHeader, columnHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRowHeader() {
		return rowHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRowHeader(boolean newRowHeader) {
		boolean oldRowHeader = rowHeader;
		rowHeader = newRowHeader;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NattablePackage.NAT_TABLE_MOUSE_EVENT__ROW_HEADER, oldRowHeader, rowHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT:
				return basicSetElement(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ID:
				return getId();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT:
				return getElement();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__BUTTON:
				return getButton();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__STATE_MASK:
				return getStateMask();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__KIND:
				return getKind();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER:
				return isColumnHeader();
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ROW_HEADER:
				return isRowHeader();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ID:
				setId((Integer)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT:
				setElement((Element)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__BUTTON:
				setButton((Integer)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__STATE_MASK:
				setStateMask((Integer)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__KIND:
				setKind((NatTableMouseEventKind)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER:
				setColumnHeader((Boolean)newValue);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ROW_HEADER:
				setRowHeader((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ID:
				setId(ID_EDEFAULT);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT:
				setElement((Element)null);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__BUTTON:
				setButton(BUTTON_EDEFAULT);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__STATE_MASK:
				setStateMask(STATE_MASK_EDEFAULT);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER:
				setColumnHeader(COLUMN_HEADER_EDEFAULT);
				return;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ROW_HEADER:
				setRowHeader(ROW_HEADER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ID:
				return id != ID_EDEFAULT;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ELEMENT:
				return element != null;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__BUTTON:
				return button != BUTTON_EDEFAULT;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__STATE_MASK:
				return stateMask != STATE_MASK_EDEFAULT;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__KIND:
				return kind != KIND_EDEFAULT;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER:
				return columnHeader != COLUMN_HEADER_EDEFAULT;
			case NattablePackage.NAT_TABLE_MOUSE_EVENT__ROW_HEADER:
				return rowHeader != ROW_HEADER_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", button: ");
		result.append(button);
		result.append(", stateMask: ");
		result.append(stateMask);
		result.append(", kind: ");
		result.append(kind);
		result.append(", columnHeader: ");
		result.append(columnHeader);
		result.append(", rowHeader: ");
		result.append(rowHeader);
		result.append(')');
		return result.toString();
	}

} //NatTableMouseEventImpl
