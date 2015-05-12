package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.TaskPass;

public class TaskPassJson {
	@JsonProperty("TaskPassId")
	private int taskPassId;
	@JsonProperty("Username")
	private String username;

	@JsonProperty("Compiler")
	private String compiler;

	@JsonProperty("ExecutionTime")
	private int executionTime;

	@JsonProperty("MemoryUsed")
	private int memoryUsed;

	@JsonProperty("NumberOfAttemps")
	private Long numberOfAttempts;

	@JsonProperty("Passed")
	private String passed;

	public TaskPassJson() {
	}

	public static TaskPassJson createTaskPassJson(TaskPass taskPass,
			Long attempts) {
		TaskPassJson tpj = new TaskPassJson();
		tpj.setTaskPassId(taskPass.getId());

		tpj.setUsername(taskPass.getUser().getDisplayName());
		tpj.setCompiler(taskPass.getCompiler().getName());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		tpj.setMemoryUsed(taskPass.getMemoryUsed());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		tpj.setNumberOfAttempts(attempts);
		if (taskPass.isPassed()){
			tpj.setPassed("Passed");
		}
		else tpj.setPassed("Failed");
		return tpj;
	}

	public int getTaskPassId() {
		return taskPassId;
	}

	public void setTaskPassId(int taskPassId) {
		this.taskPassId = taskPassId;
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

	public Long getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(Long numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
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
