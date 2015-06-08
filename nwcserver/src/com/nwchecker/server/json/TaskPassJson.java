package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.TaskTestResult;

public class TaskPassJson {
	@JsonView(JsonViews.TaskPassView.class)
	private String user;
	@JsonView(JsonViews.TaskPassView.class)
	private String compiler;
	@JsonView(JsonViews.TaskPassView.class)
	private boolean passed;
	@JsonView(JsonViews.TaskPassView.class)
	private String passedTests;

	public static TaskPassJson createTaskPassJson(TaskPass taskPass) {
		TaskPassJson taskPassJson = new TaskPassJson();
		taskPassJson.setCompiler(taskPass.getCompiler().getName());
		taskPassJson.setPassed(taskPass.isPassed());
		taskPassJson.setUsername(taskPass.getUser().getDisplayName());
		int passedCount = 0;
		for (TaskTestResult testResult : taskPass.getTestResults()) {
			if (testResult.getRate() > 0) {
				passedCount++;
			}
		}
		taskPassJson.setPassedTests(passedCount + "/"
				+ taskPass.getTask().getInOutData().size());
		return taskPassJson;
	}

	public String getUsername() {
		return user;
	}

	public void setUsername(String username) {
		this.user = username;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getPassedTests() {
		return passedTests;
	}

	public void setPassedTests(String passedTests) {
		this.passedTests = passedTests;
	}
}
