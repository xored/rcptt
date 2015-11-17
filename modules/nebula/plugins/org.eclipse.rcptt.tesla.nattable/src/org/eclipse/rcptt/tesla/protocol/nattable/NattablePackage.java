/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory
 * @model kind="package"
 * @generated
 */
public interface NattablePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "nattable";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/rcptt/tesla/nattable";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.rcptt.tesla.nattable";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NattablePackage eINSTANCE = org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl <em>Nat Table Mouse Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableMouseEvent()
	 * @generated
	 */
	int NAT_TABLE_MOUSE_EVENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__ID = ProtocolPackage.ELEMENT_COMMAND__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__ELEMENT = ProtocolPackage.ELEMENT_COMMAND__ELEMENT;

	/**
	 * The feature id for the '<em><b>Button</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__BUTTON = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State Mask</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__STATE_MASK = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__KIND = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Column Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Row Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT__ROW_HEADER = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Nat Table Mouse Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT = ProtocolPackage.ELEMENT_COMMAND_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableCellMouseEventImpl <em>Nat Table Cell Mouse Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableCellMouseEventImpl
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableCellMouseEvent()
	 * @generated
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__ID = NAT_TABLE_MOUSE_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__ELEMENT = NAT_TABLE_MOUSE_EVENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Button</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__BUTTON = NAT_TABLE_MOUSE_EVENT__BUTTON;

	/**
	 * The feature id for the '<em><b>State Mask</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__STATE_MASK = NAT_TABLE_MOUSE_EVENT__STATE_MASK;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__KIND = NAT_TABLE_MOUSE_EVENT__KIND;

	/**
	 * The feature id for the '<em><b>Column Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__COLUMN_HEADER = NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER;

	/**
	 * The feature id for the '<em><b>Row Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__ROW_HEADER = NAT_TABLE_MOUSE_EVENT__ROW_HEADER;

	/**
	 * The feature id for the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__ROW = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT__COLUMN = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Nat Table Cell Mouse Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_CELL_MOUSE_EVENT_FEATURE_COUNT = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableColumnHeaderMouseEventImpl <em>Nat Table Column Header Mouse Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableColumnHeaderMouseEventImpl
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableColumnHeaderMouseEvent()
	 * @generated
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__ID = NAT_TABLE_MOUSE_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__ELEMENT = NAT_TABLE_MOUSE_EVENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Button</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__BUTTON = NAT_TABLE_MOUSE_EVENT__BUTTON;

	/**
	 * The feature id for the '<em><b>State Mask</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__STATE_MASK = NAT_TABLE_MOUSE_EVENT__STATE_MASK;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__KIND = NAT_TABLE_MOUSE_EVENT__KIND;

	/**
	 * The feature id for the '<em><b>Column Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__COLUMN_HEADER = NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER;

	/**
	 * The feature id for the '<em><b>Row Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__ROW_HEADER = NAT_TABLE_MOUSE_EVENT__ROW_HEADER;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__INDEX = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__TEXT = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Nat Table Column Header Mouse Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT_FEATURE_COUNT = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableRowHeaderMouseEventImpl <em>Nat Table Row Header Mouse Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableRowHeaderMouseEventImpl
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableRowHeaderMouseEvent()
	 * @generated
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__ID = NAT_TABLE_MOUSE_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__ELEMENT = NAT_TABLE_MOUSE_EVENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Button</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__BUTTON = NAT_TABLE_MOUSE_EVENT__BUTTON;

	/**
	 * The feature id for the '<em><b>State Mask</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__STATE_MASK = NAT_TABLE_MOUSE_EVENT__STATE_MASK;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__KIND = NAT_TABLE_MOUSE_EVENT__KIND;

	/**
	 * The feature id for the '<em><b>Column Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__COLUMN_HEADER = NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER;

	/**
	 * The feature id for the '<em><b>Row Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__ROW_HEADER = NAT_TABLE_MOUSE_EVENT__ROW_HEADER;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__INDEX = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT__TEXT = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Nat Table Row Header Mouse Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAT_TABLE_ROW_HEADER_MOUSE_EVENT_FEATURE_COUNT = NAT_TABLE_MOUSE_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind <em>Nat Table Mouse Event Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableMouseEventKind()
	 * @generated
	 */
	int NAT_TABLE_MOUSE_EVENT_KIND = 4;


	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent <em>Nat Table Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nat Table Mouse Event</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent
	 * @generated
	 */
	EClass getNatTableMouseEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getButton <em>Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Button</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getButton()
	 * @see #getNatTableMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableMouseEvent_Button();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getStateMask <em>State Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Mask</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getStateMask()
	 * @see #getNatTableMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableMouseEvent_StateMask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#getKind()
	 * @see #getNatTableMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableMouseEvent_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isColumnHeader <em>Column Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Header</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isColumnHeader()
	 * @see #getNatTableMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableMouseEvent_ColumnHeader();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isRowHeader <em>Row Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Header</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent#isRowHeader()
	 * @see #getNatTableMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableMouseEvent_RowHeader();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent <em>Nat Table Cell Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nat Table Cell Mouse Event</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent
	 * @generated
	 */
	EClass getNatTableCellMouseEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getRow()
	 * @see #getNatTableCellMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableCellMouseEvent_Row();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent#getColumn()
	 * @see #getNatTableCellMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableCellMouseEvent_Column();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent <em>Nat Table Column Header Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nat Table Column Header Mouse Event</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent
	 * @generated
	 */
	EClass getNatTableColumnHeaderMouseEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent#getIndex()
	 * @see #getNatTableColumnHeaderMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableColumnHeaderMouseEvent_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableColumnHeaderMouseEvent#getText()
	 * @see #getNatTableColumnHeaderMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableColumnHeaderMouseEvent_Text();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent <em>Nat Table Row Header Mouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nat Table Row Header Mouse Event</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent
	 * @generated
	 */
	EClass getNatTableRowHeaderMouseEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getIndex()
	 * @see #getNatTableRowHeaderMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableRowHeaderMouseEvent_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableRowHeaderMouseEvent#getText()
	 * @see #getNatTableRowHeaderMouseEvent()
	 * @generated
	 */
	EAttribute getNatTableRowHeaderMouseEvent_Text();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind <em>Nat Table Mouse Event Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Nat Table Mouse Event Kind</em>'.
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind
	 * @generated
	 */
	EEnum getNatTableMouseEventKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NattableFactory getNattableFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl <em>Nat Table Mouse Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableMouseEventImpl
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableMouseEvent()
		 * @generated
		 */
		EClass NAT_TABLE_MOUSE_EVENT = eINSTANCE.getNatTableMouseEvent();

		/**
		 * The meta object literal for the '<em><b>Button</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_MOUSE_EVENT__BUTTON = eINSTANCE.getNatTableMouseEvent_Button();

		/**
		 * The meta object literal for the '<em><b>State Mask</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_MOUSE_EVENT__STATE_MASK = eINSTANCE.getNatTableMouseEvent_StateMask();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_MOUSE_EVENT__KIND = eINSTANCE.getNatTableMouseEvent_Kind();

		/**
		 * The meta object literal for the '<em><b>Column Header</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_MOUSE_EVENT__COLUMN_HEADER = eINSTANCE.getNatTableMouseEvent_ColumnHeader();

		/**
		 * The meta object literal for the '<em><b>Row Header</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_MOUSE_EVENT__ROW_HEADER = eINSTANCE.getNatTableMouseEvent_RowHeader();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableCellMouseEventImpl <em>Nat Table Cell Mouse Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableCellMouseEventImpl
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableCellMouseEvent()
		 * @generated
		 */
		EClass NAT_TABLE_CELL_MOUSE_EVENT = eINSTANCE.getNatTableCellMouseEvent();

		/**
		 * The meta object literal for the '<em><b>Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_CELL_MOUSE_EVENT__ROW = eINSTANCE.getNatTableCellMouseEvent_Row();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_CELL_MOUSE_EVENT__COLUMN = eINSTANCE.getNatTableCellMouseEvent_Column();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableColumnHeaderMouseEventImpl <em>Nat Table Column Header Mouse Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableColumnHeaderMouseEventImpl
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableColumnHeaderMouseEvent()
		 * @generated
		 */
		EClass NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT = eINSTANCE.getNatTableColumnHeaderMouseEvent();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__INDEX = eINSTANCE.getNatTableColumnHeaderMouseEvent_Index();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_COLUMN_HEADER_MOUSE_EVENT__TEXT = eINSTANCE.getNatTableColumnHeaderMouseEvent_Text();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableRowHeaderMouseEventImpl <em>Nat Table Row Header Mouse Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NatTableRowHeaderMouseEventImpl
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableRowHeaderMouseEvent()
		 * @generated
		 */
		EClass NAT_TABLE_ROW_HEADER_MOUSE_EVENT = eINSTANCE.getNatTableRowHeaderMouseEvent();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_ROW_HEADER_MOUSE_EVENT__INDEX = eINSTANCE.getNatTableRowHeaderMouseEvent_Index();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAT_TABLE_ROW_HEADER_MOUSE_EVENT__TEXT = eINSTANCE.getNatTableRowHeaderMouseEvent_Text();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind <em>Nat Table Mouse Event Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind
		 * @see org.eclipse.rcptt.tesla.protocol.nattable.impl.NattablePackageImpl#getNatTableMouseEventKind()
		 * @generated
		 */
		EEnum NAT_TABLE_MOUSE_EVENT_KIND = eINSTANCE.getNatTableMouseEventKind();

	}

} //NattablePackage
