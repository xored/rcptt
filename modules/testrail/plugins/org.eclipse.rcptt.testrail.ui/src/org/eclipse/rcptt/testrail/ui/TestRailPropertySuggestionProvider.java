package org.eclipse.rcptt.testrail.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.rcptt.testrail.TestRailService;
import org.eclipse.rcptt.testrail.domain.TestRailTestCase;
import org.eclipse.rcptt.ui.controls.SuggestionItem;
import org.eclipse.rcptt.ui.editors.ITestCasePropertySuggestionProvider;

public class TestRailPropertySuggestionProvider implements ITestCasePropertySuggestionProvider {

	@Override
	public List<SuggestionItem> getProperties() {
		List<SuggestionItem> props = new ArrayList<SuggestionItem>();
		props.add(new SuggestionItem(TestRailService.TESTRAIL_ID_PARAM));
		return props;
	}

	@Override
	public List<SuggestionItem> getPropertyValues(String name) {
		switch (name) {
		case TestRailService.TESTRAIL_ID_PARAM:
			return getTestCaseIdSuggestions();
		}
		return Collections.emptyList();
	}

	private List<SuggestionItem> getTestCaseIdSuggestions() {
		TestRailService service = new TestRailService();
		List<TestRailTestCase> testCases = service.getTestCases(true);
		if (testCases == null) {
			return Collections.emptyList();
		}
		List<SuggestionItem> suggestions = testCases.stream()
				.map(testCase -> getTestCaseIdSuggestion(testCase))
				.collect(Collectors.toList());
		return suggestions;
	}

	private SuggestionItem getTestCaseIdSuggestion(TestRailTestCase testCase) {
		String value = TestRailService.TESTRAIL_TESTCASEID_PREFIX + testCase.getId();
		String description = testCase.getDescription();
		return new SuggestionItem(value, description);
	}

}
