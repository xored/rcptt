/**
 */
package org.eclipse.rcptt.tesla.core.protocol.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rcptt.tesla.core.protocol.CheckRapDownloadResult;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Check Rap Download Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.CheckRapDownloadResultImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.CheckRapDownloadResultImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.impl.CheckRapDownloadResultImpl#getBase64Content <em>Base64 Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckRapDownloadResultImpl extends EObjectImpl implements CheckRapDownloadResult {
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
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected String file = FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBase64Content() <em>Base64 Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase64Content()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE64_CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase64Content() <em>Base64 Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase64Content()
	 * @generated
	 * @ordered
	 */
	protected String base64Content = BASE64_CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckRapDownloadResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProtocolPackage.Literals.CHECK_RAP_DOWNLOAD_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(String newFile) {
		String oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBase64Content() {
		return base64Content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase64Content(String newBase64Content) {
		String oldBase64Content = base64Content;
		base64Content = newBase64Content;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__BASE64_CONTENT, oldBase64Content, base64Content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__ID:
				return getId();
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__FILE:
				return getFile();
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__BASE64_CONTENT:
				return getBase64Content();
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
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__ID:
				setId((Integer)newValue);
				return;
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__FILE:
				setFile((String)newValue);
				return;
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__BASE64_CONTENT:
				setBase64Content((String)newValue);
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
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__ID:
				setId(ID_EDEFAULT);
				return;
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__FILE:
				setFile(FILE_EDEFAULT);
				return;
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__BASE64_CONTENT:
				setBase64Content(BASE64_CONTENT_EDEFAULT);
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
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__ID:
				return id != ID_EDEFAULT;
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__FILE:
				return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
			case ProtocolPackage.CHECK_RAP_DOWNLOAD_RESULT__BASE64_CONTENT:
				return BASE64_CONTENT_EDEFAULT == null ? base64Content != null : !BASE64_CONTENT_EDEFAULT.equals(base64Content);
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
		result.append(", file: ");
		result.append(file);
		result.append(", base64Content: ");
		result.append(base64Content);
		result.append(')');
		return result.toString();
	}

} //CheckRapDownloadResultImpl
