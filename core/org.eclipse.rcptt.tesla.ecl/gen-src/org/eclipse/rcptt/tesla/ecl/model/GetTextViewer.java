/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.ecl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Text Viewer</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.rcptt.tesla.ecl.model.TeslaPackage#getGetTextViewer()
 * @model annotation="http://www.eclipse.org/ecl/docs description='Gets the text viewer. If text viewer doesn\'t exist, then error is returned.' returns='text viewer' recorded='true' example='with [get-section Script | get-text-viewer] {\n        set-caret-pos 1 20\n        type-text MyText\n    }'"
 * @generated
 */
public interface GetTextViewer extends Selector {
} // GetTextViewer
