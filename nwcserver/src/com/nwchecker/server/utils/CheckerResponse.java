package com.nwchecker.server.utils;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class CheckerResponse implements Serializable {
	private static final long serialVersionUID = -6224973570501211472L;
	private LinkedHashMap<String, Object> response;

	public LinkedHashMap<String, Object> getResponse() {
		return response;
	}

	public void setResponse(LinkedHashMap<String, Object> response) {
		this.response = response;
	}
}
