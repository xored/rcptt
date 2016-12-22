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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.rcptt.internal.launching.ecl.EclScenarioExecutable;
import org.eclipse.rcptt.launching.IQ7Launch;
import org.eclipse.rcptt.launching.ITestEngine;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

public class TestEngineManager {
	private static TestEngineManager instance;

	public static TestEngineManager getInstance() {
		if (instance == null) {
			instance = new TestEngineManager();
		}
		return instance;
	}

	private final static String TESTENGINE_EXTPT = "org.eclipse.rcptt.launching.testEngine";
	private final static String TESTENGINE_ID_ATTR = "id";
	private final static String TESTENGINE_NAME_ATTR = "name";
	private final static String TESTENGINE_CLASS_ATTR = "class";

	public class TestEngineExtension {
		private String id;
		private String name;
		private ITestEngine engine;

		public TestEngineExtension(String id, String name, ITestEngine engine) {
			this.id = id;
			this.name = name;
			this.engine = engine;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public ITestEngine getEngine() {
			return engine;
		}
	}

	private final List<TestEngineExtension> extensions;
	private Map<String, String> engineStatuses;
	private List<ITestEngine> enabledEngines;

	public TestEngineManager() {
		final List<TestEngineExtension> extensions = new ArrayList<TestEngineExtension>();
		final IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(TESTENGINE_EXTPT);
		for (final IConfigurationElement element : elements) {
			final String id = element.getAttribute(TESTENGINE_ID_ATTR);
			final String name = element.getAttribute(TESTENGINE_NAME_ATTR);
			try {
				final ITestEngine engine = (ITestEngine) element
						.createExecutableExtension(TESTENGINE_CLASS_ATTR);
				extensions.add(new TestEngineExtension(id, name, engine));
			} catch (final CoreException e) {
				Q7LaunchingPlugin.log(MessageFormat.format("Failed to add {0} engine", name), e);
			}
		}
		this.extensions = extensions;
    }

	public List<TestEngineExtension> getExtensions() {
		return extensions;
	}

	public void fireSessionStarted(ExecutionSession session) {
		setUpTestEngineManager(session);
		for (ITestEngine engine : this.enabledEngines) {
			engine.sessionStarted(session);
		}
	}

	public void fireSessionCompleted(ExecutionSession session) {
		for (ITestEngine engine : this.enabledEngines) {
			engine.sessionCompleted(session);
		}
	}

	public void fireExecutionStarted(EclScenarioExecutable scenario) {
		for (ITestEngine engine : this.enabledEngines) {
			engine.executionStarted(scenario);
		}
	}

	public void fireExecutionCompleted(EclScenarioExecutable scenario, Report report) {
		for (ITestEngine engine : this.enabledEngines) {
			engine.executionCompleted(scenario, report);
		}
	}

	private void setUpTestEngineManager(ExecutionSession session) {
		try {
			ILaunchConfiguration configuration = ((Launch) session.getLaunch()).getLaunchConfiguration();
			this.engineStatuses = configuration.getAttribute(IQ7Launch.ATTR_TEST_ENGINES,
					Collections.emptyMap());
		} catch (CoreException e) {
			Q7LaunchingPlugin.log("Failed to request enabled Test Engines", e);
			this.engineStatuses = Collections.emptyMap();
		}
		this.enabledEngines = getEnabledEnginesForSession(session);
	}

	private List<ITestEngine> getEnabledEnginesForSession(ExecutionSession session) {
		List<ITestEngine> engines = extensions.stream()
				.filter(extension -> testEngineIsEnabled(extension))
				.map(extension -> extension.getEngine())
				.collect(Collectors.toList());
		return engines;
	}

	private boolean testEngineIsEnabled(TestEngineExtension extension) {
		String id = extension.getId();
		String enabled = engineStatuses.get(id);
		return enabled != null && enabled.equals("true");
	}
}
