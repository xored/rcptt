/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
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
import org.eclipse.rcptt.core.scenario.impl.ContextImpl;

import org.eclipse.rcptt.workspace.WSRoot;
import org.eclipse.rcptt.workspace.WorkspaceContext;
import org.eclipse.rcptt.workspace.WorkspacePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.rcptt.core.scenario.Attachment;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.ScenarioPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getAttachments <em>Attachments</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#isClearWorkspace <em>Clear Workspace</em>}</li>
 *   <li>{@link org.eclipse.rcptt.workspace.impl.WorkspaceContextImpl#getIgnoredByClearPattern <em>Ignored By Clear Pattern</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceContextImpl extends WorkspaceDataImpl implements WorkspaceContext {
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
	 * The default value of the '{@link #isClearWorkspace() <em>Clear Workspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClearWorkspace()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLEAR_WORKSPACE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isClearWorkspace() <em>Clear Workspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClearWorkspace()
	 * @generated
	 * @ordered
	 */
	protected boolean clearWorkspace = CLEAR_WORKSPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIgnoredByClearPattern() <em>Ignored By Clear Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredByClearPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String IGNORED_BY_CLEAR_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIgnoredByClearPattern() <em>Ignored By Clear Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredByClearPattern()
	 * @generated
	 * @ordered
	 */
	protected String ignoredByClearPattern = IGNORED_BY_CLEAR_PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkspacePackage.Literals.WORKSPACE_CONTEXT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__VERSION, oldVersion, version));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION, oldDescription, description));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__TAGS, oldTags, tags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attachment> getAttachments() {
		if (attachments == null) {
			attachments = new EObjectContainmentEList<Attachment>(Attachment.class, this, WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS);
		}
		return attachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClearWorkspace() {
		return clearWorkspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClearWorkspace(boolean newClearWorkspace) {
		boolean oldClearWorkspace = clearWorkspace;
		clearWorkspace = newClearWorkspace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__CLEAR_WORKSPACE, oldClearWorkspace, clearWorkspace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIgnoredByClearPattern() {
		return ignoredByClearPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoredByClearPattern(String newIgnoredByClearPattern) {
		String oldIgnoredByClearPattern = ignoredByClearPattern;
		ignoredByClearPattern = newIgnoredByClearPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_CONTEXT__IGNORED_BY_CLEAR_PATTERN, oldIgnoredByClearPattern, ignoredByClearPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS:
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
			case WorkspacePackage.WORKSPACE_CONTEXT__NAME:
				return getName();
			case WorkspacePackage.WORKSPACE_CONTEXT__VERSION:
				return getVersion();
			case WorkspacePackage.WORKSPACE_CONTEXT__ID:
				return getId();
			case WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION:
				return getDescription();
			case WorkspacePackage.WORKSPACE_CONTEXT__TAGS:
				return getTags();
			case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS:
				return getAttachments();
			case WorkspacePackage.WORKSPACE_CONTEXT__CLEAR_WORKSPACE:
				return isClearWorkspace();
			case WorkspacePackage.WORKSPACE_CONTEXT__IGNORED_BY_CLEAR_PATTERN:
				return getIgnoredByClearPattern();
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
			case WorkspacePackage.WORKSPACE_CONTEXT__NAME:
				setName((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__VERSION:
				setVersion((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__ID:
				setId((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__TAGS:
				setTags((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS:
				getAttachments().clear();
				getAttachments().addAll((Collection<? extends Attachment>)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__CLEAR_WORKSPACE:
				setClearWorkspace((Boolean)newValue);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__IGNORED_BY_CLEAR_PATTERN:
				setIgnoredByClearPattern((String)newValue);
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
			case WorkspacePackage.WORKSPACE_CONTEXT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__ID:
				setId(ID_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__TAGS:
				setTags(TAGS_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS:
				getAttachments().clear();
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__CLEAR_WORKSPACE:
				setClearWorkspace(CLEAR_WORKSPACE_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_CONTEXT__IGNORED_BY_CLEAR_PATTERN:
				setIgnoredByClearPattern(IGNORED_BY_CLEAR_PATTERN_EDEFAULT);
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
			case WorkspacePackage.WORKSPACE_CONTEXT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WorkspacePackage.WORKSPACE_CONTEXT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case WorkspacePackage.WORKSPACE_CONTEXT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WorkspacePackage.WORKSPACE_CONTEXT__TAGS:
				return TAGS_EDEFAULT == null ? tags != null : !TAGS_EDEFAULT.equals(tags);
			case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS:
				return attachments != null && !attachments.isEmpty();
			case WorkspacePackage.WORKSPACE_CONTEXT__CLEAR_WORKSPACE:
				return clearWorkspace != CLEAR_WORKSPACE_EDEFAULT;
			case WorkspacePackage.WORKSPACE_CONTEXT__IGNORED_BY_CLEAR_PATTERN:
				return IGNORED_BY_CLEAR_PATTERN_EDEFAULT == null ? ignoredByClearPattern != null : !IGNORED_BY_CLEAR_PATTERN_EDEFAULT.equals(ignoredByClearPattern);
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
				case WorkspacePackage.WORKSPACE_CONTEXT__NAME: return ScenarioPackage.NAMED_ELEMENT__NAME;
				case WorkspacePackage.WORKSPACE_CONTEXT__VERSION: return ScenarioPackage.NAMED_ELEMENT__VERSION;
				case WorkspacePackage.WORKSPACE_CONTEXT__ID: return ScenarioPackage.NAMED_ELEMENT__ID;
				case WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION: return ScenarioPackage.NAMED_ELEMENT__DESCRIPTION;
				case WorkspacePackage.WORKSPACE_CONTEXT__TAGS: return ScenarioPackage.NAMED_ELEMENT__TAGS;
				case WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS: return ScenarioPackage.NAMED_ELEMENT__ATTACHMENTS;
				default: return -1;
			}
		}
		if (baseClass == Context.class) {
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
				case ScenarioPackage.NAMED_ELEMENT__NAME: return WorkspacePackage.WORKSPACE_CONTEXT__NAME;
				case ScenarioPackage.NAMED_ELEMENT__VERSION: return WorkspacePackage.WORKSPACE_CONTEXT__VERSION;
				case ScenarioPackage.NAMED_ELEMENT__ID: return WorkspacePackage.WORKSPACE_CONTEXT__ID;
				case ScenarioPackage.NAMED_ELEMENT__DESCRIPTION: return WorkspacePackage.WORKSPACE_CONTEXT__DESCRIPTION;
				case ScenarioPackage.NAMED_ELEMENT__TAGS: return WorkspacePackage.WORKSPACE_CONTEXT__TAGS;
				case ScenarioPackage.NAMED_ELEMENT__ATTACHMENTS: return WorkspacePackage.WORKSPACE_CONTEXT__ATTACHMENTS;
				default: return -1;
			}
		}
		if (baseClass == Context.class) {
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
		result.append(", clearWorkspace: ");
		result.append(clearWorkspace);
		result.append(", ignoredByClearPattern: ");
		result.append(ignoredByClearPattern);
		result.append(')');
		return result.toString();
	}

} //WorkspaceContextImpl
