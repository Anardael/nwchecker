package com.nwchecker.server.service;

import java.util.Map;


public interface TaskPassService {
	public Map<String, Object> getPagedTaskPassesForContest(int id, int pageSize, int pageNumber);
}
