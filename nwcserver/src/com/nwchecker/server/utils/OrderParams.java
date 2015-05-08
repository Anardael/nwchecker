package com.nwchecker.server.utils;

public class OrderParams {
	private String username;
	private String compiler;
	private String execTime;
	private String memoryUsed;
	private String passed;
	private int pageSize;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCompiler() {
		return compiler;
	}
	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}
	public String getExecTime() {
		return execTime;
	}
	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	public String getMemoryUsed() {
		return memoryUsed;
	}
	public void setMemoryUsed(String memoryUsed) {
		this.memoryUsed = memoryUsed;
	}
	public String getPassed() {
		return passed;
	}
	public void setPassed(String passed) {
		this.passed = passed;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}