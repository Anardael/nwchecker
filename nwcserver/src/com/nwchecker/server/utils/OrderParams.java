package com.nwchecker.server.utils;

public class OrderParams {
	private boolean username = false;
	private String usernameType;
	private boolean compiler;
	private String compilerType;
	private boolean execTime;
	private String execTimeType;
	private boolean memoryUsed;
	private String memoryUsedType;
	private boolean passed;
	private String passedType;
	private int pageSize;

	/**
	 * @return the username
	 */
	public boolean isUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(boolean username) {
		this.username = username;
	}

	/**
	 * @return the usernameType
	 */
	public String getUsernameType() {
		return usernameType;
	}

	/**
	 * @param usernameType
	 *            the usernameType to set
	 */
	public void setUsernameType(String usernameType) {
		this.usernameType = usernameType;
	}

	/**
	 * @return the compiler
	 */
	public boolean isCompiler() {
		return compiler;
	}

	/**
	 * @param compiler
	 *            the compiler to set
	 */
	public void setCompiler(boolean compiler) {
		this.compiler = compiler;
	}

	/**
	 * @return the compilerType
	 */
	public String getCompilerType() {
		return compilerType;
	}

	/**
	 * @param compilerType
	 *            the compilerType to set
	 */
	public void setCompilerType(String compilerType) {
		this.compilerType = compilerType;
	}

	/**
	 * @return the execTime
	 */
	public boolean isExecTime() {
		return execTime;
	}

	/**
	 * @param execTime
	 *            the execTime to set
	 */
	public void setExecTime(boolean execTime) {
		this.execTime = execTime;
	}

	/**
	 * @return the execTimeType
	 */
	public String getExecTimeType() {
		return execTimeType;
	}

	/**
	 * @param execTimeType
	 *            the execTimeType to set
	 */
	public void setExecTimeType(String execTimeType) {
		this.execTimeType = execTimeType;
	}

	/**
	 * @return the memoryUsed
	 */
	public boolean isMemoryUsed() {
		return memoryUsed;
	}

	/**
	 * @param memoryUsed
	 *            the memoryUsed to set
	 */
	public void setMemoryUsed(boolean memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	/**
	 * @return the memoryUsedType
	 */
	public String getMemoryUsedType() {
		return memoryUsedType;
	}

	/**
	 * @param memoryUsedType
	 *            the memoryUsedType to set
	 */
	public void setMemoryUsedType(String memoryUsedType) {
		this.memoryUsedType = memoryUsedType;
	}

	/**
	 * @return the passed
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * @param passed
	 *            the passed to set
	 */
	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	/**
	 * @return the passedType
	 */
	public String getPassedType() {
		return passedType;
	}

	/**
	 * @param passedType
	 *            the passedType to set
	 */
	public void setPassedType(String passedType) {
		this.passedType = passedType;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}