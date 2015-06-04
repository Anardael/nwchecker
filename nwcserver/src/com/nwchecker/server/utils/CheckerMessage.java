package com.nwchecker.server.utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.nwchecker.server.model.TaskData;

public class CheckerMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5776109336339786305L;

	private List<TaskData> inOutDataPairs;
	private byte[] userSolution;
	private int compilerId;

	public byte[] getUserSolution() {
		return userSolution;
	}

	public void setUserSolution(byte[] userSolution) {
		this.userSolution = userSolution;
	}

	public int getCompilerId() {
		return compilerId;
	}

	public void setCompilerId(int compilerId) {
		this.compilerId = compilerId;
	}

	public List<TaskData> getInOutDataPairs() {
		return inOutDataPairs;
	}

	public void setInOutDataPairs(List<TaskData> inOutData) {
		this.inOutDataPairs = inOutData;
	}
}
