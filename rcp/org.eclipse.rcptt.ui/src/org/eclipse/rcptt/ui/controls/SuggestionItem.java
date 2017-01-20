package org.eclipse.rcptt.ui.controls;

public class SuggestionItem {

	private String name;
	private String description;

	public SuggestionItem() {
	}

	public SuggestionItem(String name) {
		this.name = name;
	}

	public SuggestionItem(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
