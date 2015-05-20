package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nwchecker.server.dao.ContestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.utils.ContestStartTimeComparator;

@Service(value = "NewsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private ContestDAO contestDAO;

	@Autowired
	private ContestService contestService;

	@Autowired
	private ContestPassService contestPassService;

	@Override
	public Contest getNearestContest() {

		Contest contest = contestDAO.getNearestContest();
		return contest;
	}

	@Override
	public List<ContestPassJson> getLastArchivedContest() {

		Contest lastContest = contestDAO.getLastArchivedContest();
		List<ContestPass> contestPasses = contestPassService
				.getContestPasses(lastContest.getId());

		List<ContestPassJson> jsonData = new ArrayList<>();

		for (ContestPass contestPass : contestPasses) {
			jsonData.add(ContestPassJson.createContestPassJson(contestPass));
		}
		return jsonData;
	}

	@Override
	public String getNameLastContest() {
		List<Contest> archivedContests = contestService
				.getContestByStatus(Contest.Status.ARCHIVE);
		Collections.sort(archivedContests, new ContestStartTimeComparator());
		Contest last = archivedContests.get(archivedContests.size() - 1);
		String name = last.getTitle();
		return name;
	}
}
