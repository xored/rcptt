package org.eclipse.rcptt.internal.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.internal.launching.Q7LaunchingPlugin;
import org.eclipse.rcptt.ui.controls.SuggestionItem;
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
	private List<SuggestionItem> testCaseProperties;
	private Map<String, List<SuggestionItem>> testCasePropertySuggestions;

	public PropertySuggestionManager() {
		// Set<SuggestionItem> testCaseProps = new TreeSet<SuggestionItem>();
		List<SuggestionItem> testCaseProps = new ArrayList<SuggestionItem>();
		// TODO (test-rail-support) add destinct and sorting
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
		this.testCaseProperties = new ArrayList<SuggestionItem>(testCaseProps);
		this.testCasePropertySuggestions = new HashMap<String, List<SuggestionItem>>();
	}

	public List<SuggestionItem> getTestCaseProperties() {
		return testCaseProperties;
	}

	public List<SuggestionItem> getTestCasePropertySuggestions(String name) {
		if (name == null || name.equals("")) {
			return Collections.emptyList();
		}
		/*if (testCasePropertySuggestions.containsKey(name)) {
			return testCasePropertySuggestions.get(name);
		}*/
		for (ITestCasePropertySuggestionProvider provider : providers) {
			List<SuggestionItem> values = provider.getPropertyValues(name);
			if (values != null) {
				testCasePropertySuggestions.put(name, values);
				return values;
			}
		}
		return Collections.emptyList();
	}
}
