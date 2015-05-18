package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.TaskPass;

/**
 * <h1>TaskPass JSON</h1> JSON entity that is designed to transform a task
 * solution record into data ready for display
 * <p>
 * 
 * @author Boris Andreev
 * @version 1.0
 */
public class TaskPassJson extends Json {
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

	public TaskPassJson(TaskPass taskPass) {
		this.setUsername(taskPass.getUser().getDisplayName());
		this.setCompiler(taskPass.getCompiler().getName());
		this.setExecutionTime(taskPass.getExecutionTime());
		this.setMemoryUsed(taskPass.getMemoryUsed());
		this.setExecutionTime(taskPass.getExecutionTime());
		if (taskPass.isPassed()) {
			this.setPassed("Passed");
		} else
			this.setPassed("Failed");
	}

	/**
	 * 
	 * @deprecated use JsonUtil instead
	 */

	@Deprecated
	public static TaskPassJson createTaskPassJson(TaskPass taskPass) {
		TaskPassJson tpj = new TaskPassJson();
		tpj.setUsername(taskPass.getUser().getDisplayName());
		tpj.setCompiler(taskPass.getCompiler().getName());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		tpj.setMemoryUsed(taskPass.getMemoryUsed());
		tpj.setExecutionTime(taskPass.getExecutionTime());
		if (taskPass.isPassed()) {
			tpj.setPassed("Passed");
		} else
			tpj.setPassed("Failed");
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

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public boolean equals(TaskPassJson other) {
		return (this.username.equals(other.getUserName())
				&& this.compiler.equals(other.getCompiler())
				&& this.getExecutionTime() == other.getExecutionTime()
				&& this.memoryUsed == other.getMemoryUsed() && this.passed
					.equals(other.getPassed()));
	}
}
