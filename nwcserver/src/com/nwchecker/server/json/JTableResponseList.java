package com.nwchecker.server.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <h1>TaskPass Table Response List</h1> JSON entity made to send data from server to
 * jTable javascript plug-in.
 * 
 * @author Boris Andreev
 * @version 1.0
 */
public class JTableResponseList {
	@JsonView(JsonViews.TaskPassView.class)
	@JsonProperty("rows")
	private List<?> records;
	@JsonView(JsonViews.TaskPassView.class)
	@JsonProperty("total")
	private long totalRecordCount;
	@JsonProperty("Message")
	private String message;

	public JTableResponseList() {
	}

	public JTableResponseList(List<?> records,
			long recordCount) {
		this.records = records;
		this.totalRecordCount = recordCount;
	}

	public JTableResponseList(String result, String message) {
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
