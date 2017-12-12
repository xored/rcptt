/*******************************************************************************
 * Copyright (c) 2009, 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.verifications.resources.impl;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.core.VerificationProcessor;
import org.eclipse.rcptt.core.scenario.Verification;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.resources.WSUtils;
import org.eclipse.rcptt.resources.impl.WSRunnables;
import org.eclipse.rcptt.verifications.resources.WorkspaceVerificationMaker;
import org.eclipse.rcptt.verifications.resources.internal.impl.Activator;
import org.eclipse.rcptt.workspace.WSFile;
import org.eclipse.rcptt.workspace.WSFolder;
import org.eclipse.rcptt.workspace.WSProject;
import org.eclipse.rcptt.workspace.WSRoot;
import org.eclipse.rcptt.workspace.WorkspaceFactory;
import org.eclipse.rcptt.workspace.WorkspaceVerification;

@SuppressWarnings("deprecation")
public class WorkspaceVerificationProcessor extends VerificationProcessor {

	@Override
	public Verification create(EObject param, IProcess process) throws CoreException {
		final WorkspaceVerification verification = WorkspaceFactory.eINSTANCE
				.createWorkspaceVerification();
		final WSRoot root = WorkspaceFactory.eINSTANCE.createWSRoot();
		verification.setContent(root);
		final WorkspaceVerificationMaker maker = new WorkspaceVerificationMaker();
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(final IProgressMonitor monitor)
					throws CoreException {
				final IProject[] projects = ResourcesPlugin.getWorkspace()
						.getRoot().getProjects();
				for (final IProject iProject : projects) {
					if (iProject.exists() && iProject.isOpen()) {
						// Do a project refresh, before import
						iProject.refreshLocal(IResource.DEPTH_INFINITE,
								new SubProgressMonitor(monitor, 1));

						final WSProject wsProject = WSUtils.getProject(root,
								iProject.getName(), true);
						root.getProjects().add(wsProject);
						doCreate(wsProject, iProject, maker);
					}
				}
			}
		}, new NullProgressMonitor());
		return verification;
	}

	private void doCreate(final WSFolder folder, final IContainer iContainer,
			WorkspaceVerificationMaker maker) throws CoreException {
		for (final IResource iResource : iContainer.members()) {
			final String name = iResource.getName();
			if (iResource instanceof IFolder) {
				final WSFolder child = WSUtils.getFolder(folder, name, true);
				doCreate(child, (IFolder) iResource, maker);
			} else if (iResource instanceof IFile) {
				final IFile iFile = (IFile) iResource;

				if (!iFile.isLocal(IResource.DEPTH_ZERO)) {
					Activator.logWarn("Cannot retrieve contents of a file %s (%s). File skipped.", name,
							iFile.getLocation());
					continue;
				}

				final WSFile child = WSUtils.getFile(folder, name, true);
				maker.makeExecutableVerification(child, iFile);
			}
		}
	}

	@Override
	public void finish(Verification verification, Object data, IProcess process) throws CoreException {
		final WorkspaceVerification wc = (WorkspaceVerification) verification;
		final IWorkspace ws = ResourcesPlugin.getWorkspace();

		ws.run(WSRunnables.refreshWorkspace, null, IWorkspace.AVOID_UPDATE, null);

		ws.run(new IWorkspaceRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException {
				new WorkspaceVerificationChecker(wc).verifyWorkspace();
			}
		}, null, IWorkspace.AVOID_UPDATE, null);
	}

}
