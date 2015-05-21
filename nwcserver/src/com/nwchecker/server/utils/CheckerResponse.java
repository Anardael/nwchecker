package com.nwchecker.server.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CheckerResponse implements Serializable {
	private static final long serialVersionUID = -6224973570501211472L;
	private HashMap<String, Object> response;

	public HashMap<String, Object> getResponse() {
		return response;
	}

	public void setResponse(HashMap<String, Object> response) {
		this.response = response;
	}
}
