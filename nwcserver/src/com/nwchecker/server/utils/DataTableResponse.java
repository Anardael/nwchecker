package com.nwchecker.server.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTableResponse {
	@JsonProperty("iTotalRecords")
	private int recordCount;
	@JsonProperty("iTotalDisplayRecords")
	private int filteredRecordCount;
	@JsonProperty("sEcho")
	private String sEcho;
	@JsonProperty("aaData")
	private List<?> dataList;

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getFilteredRecordCount() {
		return filteredRecordCount;
	}

	public void setFilteredRecordCount(int filteredRecordCount) {
		this.filteredRecordCount = filteredRecordCount;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
}
