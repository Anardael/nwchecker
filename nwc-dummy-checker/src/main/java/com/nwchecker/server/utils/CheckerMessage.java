package com.nwchecker.server.utils;

import java.io.Serializable;
import java.util.LinkedList;

public class CheckerMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5776109336339786305L;

	private LinkedList<byte[]> inputData;
	private LinkedList<byte[]> outputData;
	private byte[] userSolution;
	private int compilerId;

	public LinkedList<byte[]> getInputData() {
		return inputData;
	}

	public void setInputData(LinkedList<byte[]> inputData) {
		this.inputData = inputData;
	}

	public LinkedList<byte[]> getOutputData() {
		return outputData;
	}

	public void setOutputData(LinkedList<byte[]> outputData) {
		this.outputData = outputData;
	}

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
}
