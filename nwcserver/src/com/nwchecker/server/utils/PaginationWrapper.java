package com.nwchecker.server.utils;

import java.util.List;

public class PaginationWrapper<E> {
	private List<E> dataList;
	private Long pageCount;
	private Long recordCount;

	public List<E> getDataList() {
		return dataList;
	}

	public void setDataList(List<E> data) {
		this.dataList = data;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	
	public static Long getPageCount(long entryCount, int pageSize){
		Long pageCount;
		if (entryCount%pageSize == 0){
			pageCount = entryCount/pageSize;
		}
		else pageCount = entryCount/pageSize +1;
		return pageCount;
	}
	public static int getFirstResult(int pageNumber, int pageSize){
		return (pageNumber-1)*pageSize;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
}
