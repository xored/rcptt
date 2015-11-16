/**
 */
package org.eclipse.rcptt.tesla.nattable.ecl.nattable.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.rcptt.tesla.core.ui.UiPackage;
import org.eclipse.rcptt.tesla.ecl.model.TeslaPackage;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetNatTable;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.GetRowHeader;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage;
import org.eclipse.rcptt.tesla.nattable.ecl.nattable.NebulaNatTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NattablePackageImpl extends EPackageImpl implements NattablePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nebulaNatTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getNatTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getRowHeaderEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.rcptt.tesla.nattable.ecl.nattable.NattablePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NattablePackageImpl() {
		super(eNS_URI, NattableFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link NattablePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NattablePackage init() {
		if (isInited) return (NattablePackage)EPackage.Registry.INSTANCE.getEPackage(NattablePackage.eNS_URI);

		// Obtain or create and register package
		NattablePackageImpl theNattablePackage = (NattablePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NattablePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NattablePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TeslaPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theNattablePackage.createPackageContents();

		// Initialize created meta-data
		theNattablePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNattablePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NattablePackage.eNS_URI, theNattablePackage);
		return theNattablePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNebulaNatTable() {
		return nebulaNatTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGetNatTable() {
		return getNatTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGetRowHeader() {
		return getRowHeaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGetRowHeader_Text() {
		return (EAttribute)getRowHeaderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NattableFactory getNattableFactory() {
		return (NattableFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		nebulaNatTableEClass = createEClass(NEBULA_NAT_TABLE);

		getNatTableEClass = createEClass(GET_NAT_TABLE);

		getRowHeaderEClass = createEClass(GET_ROW_HEADER);
		createEAttribute(getRowHeaderEClass, GET_ROW_HEADER__TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UiPackage theUiPackage = (UiPackage)EPackage.Registry.INSTANCE.getEPackage(UiPackage.eNS_URI);
		TeslaPackage theTeslaPackage = (TeslaPackage)EPackage.Registry.INSTANCE.getEPackage(TeslaPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		nebulaNatTableEClass.getESuperTypes().add(theUiPackage.getControl());
		getNatTableEClass.getESuperTypes().add(theTeslaPackage.getSelector());
		getRowHeaderEClass.getESuperTypes().add(theTeslaPackage.getSelector());

		// Initialize classes and features; add operations and parameters
		initEClass(nebulaNatTableEClass, NebulaNatTable.class, "NebulaNatTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(getNatTableEClass, GetNatTable.class, "GetNatTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(getRowHeaderEClass, GetRowHeader.class, "GetRowHeader", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getGetRowHeader_Text(), ecorePackage.getEString(), "text", null, 0, 1, GetRowHeader.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //NattablePackageImpl
