package org.eclipse.rcptt.internal.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.internal.launching.Q7LaunchingPlugin;
import org.eclipse.rcptt.ui.editors.ITestCasePropertySuggestionProvider;

public class PropertySuggestionManager {
	private static PropertySuggestionManager instance;

	public static PropertySuggestionManager getInstance() {
		if (instance == null) {
			instance = new PropertySuggestionManager();
		}
		return instance;
	}

	private final static String PROPSUGGESTION_EXTPT = "org.eclipse.rcptt.ui.propertySuggestionProviders";
	private final static String PROPSUGGESTION_TESTCASE_ATTR = "testCaseProperties";

	private List<ITestCasePropertySuggestionProvider> providers;
	private List<String> testCaseProperties;
	private Map<String, List<String>> testCasePropertySuggestions;

	public PropertySuggestionManager() {
		Set<String> testCaseProps = new TreeSet<String>();
		this.providers = new ArrayList<ITestCasePropertySuggestionProvider>();
		final IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(PROPSUGGESTION_EXTPT);
		for (final IConfigurationElement element : elements) {
			try {
				final ITestCasePropertySuggestionProvider provider = (ITestCasePropertySuggestionProvider) element
						.createExecutableExtension(PROPSUGGESTION_TESTCASE_ATTR);
				providers.add(provider);
				// TODO (test-rail-support) add if-null check
				testCaseProps.addAll(provider.getProperties());
			} catch (final CoreException e) {
				Q7LaunchingPlugin.log(e);
			}
		}
		this.testCaseProperties = new ArrayList<String>(testCaseProps);
		this.testCasePropertySuggestions = new HashMap<String, List<String>>();
	}

	public List<String> getTestCaseProperties() {
		return testCaseProperties;
	}

	public List<String> getTestCasePropertySuggestions(String name) {
		if (name == null || name.equals("")) {
			return Collections.emptyList();
		}
		if (testCasePropertySuggestions.containsKey(name)) {
			return testCasePropertySuggestions.get(name);
		}
		for (ITestCasePropertySuggestionProvider provider : providers) {
			List<String> values = provider.getPropertyValues(name);
			if (values != null) {
				testCasePropertySuggestions.put(name, values);
				return values;
			}
		}
		return Collections.emptyList();
	}
}
