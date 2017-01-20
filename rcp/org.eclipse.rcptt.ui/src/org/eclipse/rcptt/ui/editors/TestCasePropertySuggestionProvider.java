package org.eclipse.rcptt.ui.editors;

import java.util.ArrayList;
import java.util.List;

public class TestCasePropertySuggestionProvider implements ITestCasePropertySuggestionProvider {

	@Override
	public List<String> getProperties() {
		// TODO (test-rail-support) replace with Messages
		List<String> properties = new ArrayList<String>();
		properties.add("issue-id");
		properties.add("creator");
		properties.add("version");
		return properties;
	}

	@Override
	public List<String> getPropertyValues(String name) {
		return null;
	}

}
