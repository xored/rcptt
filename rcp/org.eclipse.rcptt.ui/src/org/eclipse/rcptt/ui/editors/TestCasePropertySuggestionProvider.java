package org.eclipse.rcptt.ui.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rcptt.ui.controls.SuggestionItem;

public class TestCasePropertySuggestionProvider implements ITestCasePropertySuggestionProvider {

	@Override
	public List<SuggestionItem> getProperties() {
		// TODO (test-rail-support) replace with Messages
		List<SuggestionItem> properties = new ArrayList<SuggestionItem>();
		properties.add(new SuggestionItem("issue-id"));
		properties.add(new SuggestionItem("creator"));
		properties.add(new SuggestionItem("version"));
		return properties;
	}

	@Override
	public List<SuggestionItem> getPropertyValues(String name) {
		return null;
	}

}
