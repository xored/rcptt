package org.eclipse.rcptt.testrail.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestRailTestCase {
	@Expose(serialize = false)
	private String id;
	@Expose
	@SerializedName("title")
	private String name;
	private String description;

	public TestRailTestCase() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
