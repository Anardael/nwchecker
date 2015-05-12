package com.nwchecker.server.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskPassTableResponseList {
	private String Result;
	private List<TaskPassJson> Records;
	private long TotalRecordCount;
	private String Message;
	

	public TaskPassTableResponseList() {
	}

	public TaskPassTableResponseList(String result, List<TaskPassJson> records,
			long recordCount) {
		this.Result = result;
		this.Records = records;
		this.TotalRecordCount = recordCount;
	}

	public TaskPassTableResponseList(String result, String message) {
		this.Result = result;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		this.Result = result;
	}

	public List<TaskPassJson> getRecords() {
		return Records;
	}

	public void setRecords(List<TaskPassJson> records) {
		this.Records = records;
	}

	public long getTotalRecordCount() {
		return TotalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.TotalRecordCount = totalRecordCount;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}

}
