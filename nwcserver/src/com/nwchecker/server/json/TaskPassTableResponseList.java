package com.nwchecker.server.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskPassTableResponseList {
	@JsonProperty("Result")
	private String result;
	@JsonProperty("Records")
	private List<TaskPassJson> records;
	@JsonProperty("TotalRecordCount")
	private long totalRecordCount;
	@JsonProperty("Message")
	private String message;
	

	public TaskPassTableResponseList() {
	}

	public TaskPassTableResponseList(String result, List<TaskPassJson> records,
			long recordCount) {
		this.result = result;
		this.records = records;
		this.totalRecordCount = recordCount;
	}

	public TaskPassTableResponseList(String result, String message) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<TaskPassJson> getRecords() {
		return records;
	}

	public void setRecords(List<TaskPassJson> records) {
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
