/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.launching;

import org.eclipse.rcptt.internal.launching.ExecutionSession;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

public interface ITestEngineListener {

	public void sessionStarted(ExecutionSession session);

	public void sessionCompleted(ExecutionSession session);

	public void executionStarted(EclScenarioExecutable scenario);

	public void executionCompleted(EclScenarioExecutable scenario, Report report);

}
