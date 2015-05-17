package com.nwchecker.server.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>TaskPass Table Response List</h1> JSON entity made to send data from server to
 * jTable javascript plug-in.
 * 
 * @author Boris Andreev
 * @version 1.0
 */
public class JTableResponseList {
	@JsonProperty("Result")
	private String result;
	@JsonProperty("Records")
	private List<?> records;
	@JsonProperty("TotalRecordCount")
	private long totalRecordCount;
	@JsonProperty("Message")
	private String message;

	public JTableResponseList() {
	}

	public JTableResponseList(String result, List<?> records,
			long recordCount) {
		this.result = result;
		this.records = records;
		this.totalRecordCount = recordCount;
	}

	public JTableResponseList(String result, String message) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
