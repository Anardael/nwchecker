package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.TaskPass;

public class TaskPassJson {
	@JsonProperty("Username")
	private String username;

	@JsonProperty("Compiler")
	private String compiler;

	@JsonProperty("ExecutionTime")
	private int executionTime;

	@JsonProperty("MemoryUsed")
	private int memoryUsed;

	@JsonProperty("Passed")
	private String passed;

	public TaskPassJson() {
	}

	public static TaskPassJson createTaskPassJson(TaskPass taskPass) {
		TaskPassJson tpj = new TaskPassJson();
		tpj.setUsername(taskPass.getUser().getDisplayName());
		tpj.setCompiler(taskPass.getCompiler().getName());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		tpj.setMemoryUsed(taskPass.getMemoryUsed());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		if (taskPass.isPassed()){
			tpj.setPassed("Passed");
		}
		else tpj.setPassed("Failed");
		return tpj;
	}

	public String getUserName() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	/**
	 * @return the passed
	 */
	public String getPassed() {
		return passed;
	}

	/**
	 * @param passed the passed to set
	 */
	public void setPassed(String passed) {
		this.passed = passed;
	}

}
