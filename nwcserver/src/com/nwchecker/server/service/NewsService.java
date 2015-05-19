package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;

public interface NewsService {

	public Contest getNextContest();

	public List<ContestPassJson> getResultLastContest();

	public String getNameLastContest();

}
