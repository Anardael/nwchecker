package com.nwchecker.server.utils;

public class CheckerResponse {
	private int success;
	private int executionTime;
	private int memoryUsed;

	/*
	 * private static final long serialVersionUID = -6224973570501211472L;
	 * private LinkedHashMap<String, Object> response;
	 * 
	 * public LinkedHashMap<String, Object> getResponse() { return response; }
	 * 
	 * public void setResponse(LinkedHashMap<String, Object> response) {
	 * this.response = response; }
	 */
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
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
}
