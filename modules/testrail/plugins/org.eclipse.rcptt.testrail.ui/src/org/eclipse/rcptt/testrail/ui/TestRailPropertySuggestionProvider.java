package org.eclipse.rcptt.testrail.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rcptt.testrail.TestRailService;
import org.eclipse.rcptt.ui.editors.ITestCasePropertySuggestionProvider;

public class TestRailPropertySuggestionProvider implements ITestCasePropertySuggestionProvider {

	@Override
	public List<String> getProperties() {
		List<String> props = new ArrayList<String>();
		props.add("testrail-id");
		return props;
	}

	@Override
	public List<String> getPropertyValues(String name) {
		// REMOVE
		if (name.equals("testrail-id")) {
			TestRailService serv = new TestRailService();
			return serv.getTestCaseIdSuggestions();
		}
		return null;
	}

}
