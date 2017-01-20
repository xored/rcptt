package org.eclipse.rcptt.ui.editors;

import java.util.List;

public interface ITestCasePropertySuggestionProvider {

	public List<String> getProperties();

	public List<String> getPropertyValues(String name);

}
