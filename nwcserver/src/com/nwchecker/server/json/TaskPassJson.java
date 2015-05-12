package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.TaskPass;

public class TaskPassJson {
	private int taskPassId;
	private String username;
	private String compiler;
	private int executionTime;
	private int memoryUsed;
	private Long numberOfAttempts;
	private boolean passed;

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
		tpj.setPassed(taskPass.isPassed());
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

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
}
