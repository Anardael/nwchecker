package com.nwchecker.server.utils.messages;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckerMessage implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5776109336339786305L;

	private ArrayList<byte[]> inputData;
	private ArrayList<byte[]> outputData;
	private ArrayList<Integer> time;
	private ArrayList<Integer> score;
	private ArrayList<Integer> memory;
	private byte[] userSolution;
	private String extension;
	private String inputFileName;
	private String outputFileName;
	private String checkingScript;

	public ArrayList<byte[]> getInputData() {
		return inputData;
	}

	public void setInputData(ArrayList<byte[]> inputData) {
		this.inputData = inputData;
	}

	public ArrayList<byte[]> getOutputData() {
		return outputData;
	}

	public void setOutputData(ArrayList<byte[]> outputData) {
		this.outputData = outputData;
	}

	public byte[] getUserSolution() {
		return userSolution;
	}

	public void setUserSolution(byte[] userSolution) {
		this.userSolution = userSolution;
	}

    public ArrayList<Integer> getTime() {
        return time;
    }

    public void setTime(ArrayList<Integer> time) {
        this.time = time;
    }

    public ArrayList<Integer> getScore() {
        return score;
    }

    public void setScore(ArrayList<Integer> score) {
        this.score = score;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getCheckingScript() {
        return checkingScript;
    }

    public void setCheckingScript(String checkingScript) {
        this.checkingScript = checkingScript;
    }

    public ArrayList<Integer> getMemory() {
        return memory;
    }

    public void setMemory(ArrayList<Integer> memory) {
        this.memory = memory;
    }
}
