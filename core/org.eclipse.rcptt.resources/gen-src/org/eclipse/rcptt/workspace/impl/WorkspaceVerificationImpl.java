/*******************************************************************************
 * Copyright (c) 2009, 2017 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.workspace.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.rcptt.core.scenario.Attachment;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.ScenarioPackage;
import org.eclipse.rcptt.core.scenario.Verification;

import org.eclipse.rcptt.workspace.WorkspacePackage;
import org.eclipse.rcptt.workspace.WorkspaceVerification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Verification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getAttachments <em>Attachments</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#isAllowUncapturedFiles <em>Allow Uncaptured Files</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceVerificationImpl#getIgnoredLines <em>Ignored Lines</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceVerificationImpl extends WorkspaceDataImpl implements WorkspaceVerification {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "2.0";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTags() <em>Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGS_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected String tags = TAGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttachments() <em>Attachments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachments()
	 * @generated
	 * @ordered
	 */
	protected EList<Attachment> attachments;

	/**
	 * The default value of the '{@link #isAllowUncapturedFiles() <em>Allow Uncaptured Files</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowUncapturedFiles()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_UNCAPTURED_FILES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAllowUncapturedFiles() <em>Allow Uncaptured Files</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowUncapturedFiles()
	 * @generated
	 * @ordered
	 */
	protected boolean allowUncapturedFiles = ALLOW_UNCAPTURED_FILES_EDEFAULT;

	/**
	 * The default value of the '{@link #getIgnoredLines() <em>Ignored Lines</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredLines()
	 * @generated
	 * @ordered
	 */
	protected static final String IGNORED_LINES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIgnoredLines() <em>Ignored Lines</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredLines()
	 * @generated
	 * @ordered
	 */
	protected String ignoredLines = IGNORED_LINES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceVerificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkspacePackage.Literals.WORKSPACE_VERIFICATION;
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTags(String newTags) {
		String oldTags = tags;
		tags = newTags;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__TAGS, oldTags, tags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attachment> getAttachments() {
		if (attachments == null) {
			attachments = new EObjectContainmentEList<Attachment>(Attachment.class, this, WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS);
		}
		return attachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllowUncapturedFiles() {
		return allowUncapturedFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllowUncapturedFiles(boolean newAllowUncapturedFiles) {
		boolean oldAllowUncapturedFiles = allowUncapturedFiles;
		allowUncapturedFiles = newAllowUncapturedFiles;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__ALLOW_UNCAPTURED_FILES, oldAllowUncapturedFiles, allowUncapturedFiles));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIgnoredLines() {
		return ignoredLines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoredLines(String newIgnoredLines) {
		String oldIgnoredLines = ignoredLines;
		ignoredLines = newIgnoredLines;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_VERIFICATION__IGNORED_LINES, oldIgnoredLines, ignoredLines));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS:
				return ((InternalEList<?>)getAttachments()).basicRemove(otherEnd, msgs);
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
			case WorkspacePackage.WORKSPACE_VERIFICATION__NAME:
				return getName();
			case WorkspacePackage.WORKSPACE_VERIFICATION__VERSION:
				return getVersion();
			case WorkspacePackage.WORKSPACE_VERIFICATION__ID:
				return getId();
			case WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION:
				return getDescription();
			case WorkspacePackage.WORKSPACE_VERIFICATION__TAGS:
				return getTags();
			case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS:
				return getAttachments();
			case WorkspacePackage.WORKSPACE_VERIFICATION__ALLOW_UNCAPTURED_FILES:
				return isAllowUncapturedFiles();
			case WorkspacePackage.WORKSPACE_VERIFICATION__IGNORED_LINES:
				return getIgnoredLines();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WorkspacePackage.WORKSPACE_VERIFICATION__NAME:
				setName((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__VERSION:
				setVersion((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ID:
				setId((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__TAGS:
				setTags((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS:
				getAttachments().clear();
				getAttachments().addAll((Collection<? extends Attachment>)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ALLOW_UNCAPTURED_FILES:
				setAllowUncapturedFiles((Boolean)newValue);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__IGNORED_LINES:
				setIgnoredLines((String)newValue);
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
			case WorkspacePackage.WORKSPACE_VERIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ID:
				setId(ID_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__TAGS:
				setTags(TAGS_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS:
				getAttachments().clear();
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__ALLOW_UNCAPTURED_FILES:
				setAllowUncapturedFiles(ALLOW_UNCAPTURED_FILES_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_VERIFICATION__IGNORED_LINES:
				setIgnoredLines(IGNORED_LINES_EDEFAULT);
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
			case WorkspacePackage.WORKSPACE_VERIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WorkspacePackage.WORKSPACE_VERIFICATION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case WorkspacePackage.WORKSPACE_VERIFICATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WorkspacePackage.WORKSPACE_VERIFICATION__TAGS:
				return TAGS_EDEFAULT == null ? tags != null : !TAGS_EDEFAULT.equals(tags);
			case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS:
				return attachments != null && !attachments.isEmpty();
			case WorkspacePackage.WORKSPACE_VERIFICATION__ALLOW_UNCAPTURED_FILES:
				return allowUncapturedFiles != ALLOW_UNCAPTURED_FILES_EDEFAULT;
			case WorkspacePackage.WORKSPACE_VERIFICATION__IGNORED_LINES:
				return IGNORED_LINES_EDEFAULT == null ? ignoredLines != null : !IGNORED_LINES_EDEFAULT.equals(ignoredLines);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case WorkspacePackage.WORKSPACE_VERIFICATION__NAME: return ScenarioPackage.NAMED_ELEMENT__NAME;
				case WorkspacePackage.WORKSPACE_VERIFICATION__VERSION: return ScenarioPackage.NAMED_ELEMENT__VERSION;
				case WorkspacePackage.WORKSPACE_VERIFICATION__ID: return ScenarioPackage.NAMED_ELEMENT__ID;
				case WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION: return ScenarioPackage.NAMED_ELEMENT__DESCRIPTION;
				case WorkspacePackage.WORKSPACE_VERIFICATION__TAGS: return ScenarioPackage.NAMED_ELEMENT__TAGS;
				case WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS: return ScenarioPackage.NAMED_ELEMENT__ATTACHMENTS;
				default: return -1;
			}
		}
		if (baseClass == Verification.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ScenarioPackage.NAMED_ELEMENT__NAME: return WorkspacePackage.WORKSPACE_VERIFICATION__NAME;
				case ScenarioPackage.NAMED_ELEMENT__VERSION: return WorkspacePackage.WORKSPACE_VERIFICATION__VERSION;
				case ScenarioPackage.NAMED_ELEMENT__ID: return WorkspacePackage.WORKSPACE_VERIFICATION__ID;
				case ScenarioPackage.NAMED_ELEMENT__DESCRIPTION: return WorkspacePackage.WORKSPACE_VERIFICATION__DESCRIPTION;
				case ScenarioPackage.NAMED_ELEMENT__TAGS: return WorkspacePackage.WORKSPACE_VERIFICATION__TAGS;
				case ScenarioPackage.NAMED_ELEMENT__ATTACHMENTS: return WorkspacePackage.WORKSPACE_VERIFICATION__ATTACHMENTS;
				default: return -1;
			}
		}
		if (baseClass == Verification.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(", id: ");
		result.append(id);
		result.append(", description: ");
		result.append(description);
		result.append(", tags: ");
		result.append(tags);
		result.append(", allowUncapturedFiles: ");
		result.append(allowUncapturedFiles);
		result.append(", ignoredLines: ");
		result.append(ignoredLines);
		result.append(')');
		return result.toString();
	}

} //WorkspaceVerificationImpl
