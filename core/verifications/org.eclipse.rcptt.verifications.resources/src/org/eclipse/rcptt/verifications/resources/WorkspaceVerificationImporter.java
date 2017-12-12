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
package org.eclipse.rcptt.verifications.resources;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.eclipse.rcptt.core.Q7Features;
import org.eclipse.rcptt.resources.WSUtils;
import org.eclipse.rcptt.resources.ImportUtils.Importer;
import org.eclipse.rcptt.workspace.WSFile;
import org.eclipse.rcptt.workspace.WorkspaceVerification;

public class WorkspaceVerificationImporter implements Importer {

	private WorkspaceVerification verifiation;

	public WorkspaceVerificationImporter(WorkspaceVerification verifiation) {
		this.verifiation = verifiation;
	}

	@Override
	public InputStream getContents(Object element) {
		try {
			WSFile file = (WSFile) element;
			if (file.getContent() != null) {
				if (Q7Features.getInstance().isTrue(
						Q7Features.Q7_CONTEXTS_RESOURCES_ZIPPED_TRANSFER)) {
					ZipInputStream zin = new ZipInputStream(
							new ByteArrayInputStream(file.getContent()));
					zin.getNextEntry();
					return zin;
				}
				return new ByteArrayInputStream(file.getContent());
			}
			return WSUtils.getFileStream(verifiation.getLocation(), file, null);

		} catch (Exception e) {
			return null;
		}
	}

}
