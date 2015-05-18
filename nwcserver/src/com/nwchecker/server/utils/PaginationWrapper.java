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

	/**
	 * Get number of pages that fit the results that fit all the records from
	 * database.
	 * 
	 * @param recordCount
	 * @param pageSize
	 * @return number of pages
	 */
	public static Long getPageCount(long recordCount, int pageSize) {
		Long pageCount;
		if (recordCount % pageSize == 0) {
			pageCount = recordCount / pageSize;
		} else
			pageCount = recordCount / pageSize + 1;
		return pageCount;
	}

	/**
	 * Get index of the first element that will be displayed on the page
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static int getFirstIndexOnPage(int pageNumber, int pageSize) {
		return (pageNumber - 1) * pageSize;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
}
