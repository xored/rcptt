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
package org.eclipse.rcptt.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.launching.ITestEngineListener;

public class TestEngineListenerManager {
	private static TestEngineListenerManager instance;

	public static TestEngineListenerManager getInstance() {
		if (instance == null) {
			instance = new TestEngineListenerManager();
		}
		return instance;
	}

	private final static String TESTENGINE_EXTPT = "org.eclipse.rcptt.launching.testEngineListener";
	private final static String TESTENGINE_CLASS_ATTR = "class";

	private final List<ITestEngineListener> listeners;

	public TestEngineListenerManager() {
		final List<ITestEngineListener> list = new ArrayList<ITestEngineListener>();
		final IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(TESTENGINE_EXTPT);
		for (final IConfigurationElement element : elements) {
			try {
				final ITestEngineListener listener = (ITestEngineListener) element
						.createExecutableExtension(TESTENGINE_CLASS_ATTR);
				list.add(listener);
			} catch (final CoreException e) {
				e.printStackTrace(); // TODO (test-rail-support) replace with Plugin.log(e);
			}
		}
		this.listeners = list;
    }

	public void fireExecutionCompleted(List<EclScenarioExecutable> scenarios) {
		for (ITestEngineListener listener : this.listeners) {
			listener.executionCompleted(scenarios);
		}
	}
}
