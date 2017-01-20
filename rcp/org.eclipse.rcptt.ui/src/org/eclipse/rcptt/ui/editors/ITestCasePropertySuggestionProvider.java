package org.eclipse.rcptt.ui.editors;

import java.util.List;

import org.eclipse.rcptt.ui.controls.SuggestionItem;

public interface ITestCasePropertySuggestionProvider {

	public List<SuggestionItem> getProperties();

	public List<SuggestionItem> getPropertyValues(String name);

}
