package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskPassTableResponse {
	@JsonProperty("Result")
	private String result;
	@JsonProperty("Record")
	private TaskPassJson record;
	@JsonProperty("Message")
	private String message;

	public TaskPassTableResponse() {
	}

	public TaskPassTableResponse(String result, TaskPassJson record,
			String message) {
		this.result = result;
		this.record = record;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TaskPassJson getRecord() {
		return record;
	}

	public void setRecord(TaskPassJson record) {
		this.record = record;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
