package com.nwchecker.server.utils.messages;

import java.io.Serializable;

public class CheckerResponse implements Serializable {
	private static final long serialVersionUID = -6224973570501211472L;
	private String response;
	private Float score;
	private String log;
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public Float getScore() {
        return score;
    }
    public void setScore(Float score) {
        this.score = score;
    }
    public String getLog() {
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }


}
