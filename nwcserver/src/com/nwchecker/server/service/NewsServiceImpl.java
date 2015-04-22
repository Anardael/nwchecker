package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private ContestService contestService;
	
	@Autowired
	private ContestPassService contestPassService;
	 
	@Override
	public Contest getNextContest() {
		List<Contest> avalaibleContests = contestService.getContestByStatus(Status.PREPARING);    	 
   	 Collections.sort(avalaibleContests , new ContestStartTimeComparator() );   
   	 Contest first = avalaibleContests.get(0);
		return first;
	}

	@Override
	public List<ContestPassJson> getResultLastContest() {
		
   	 List<Contest> archivedContests = contestService.getContestByStatus(Contest.Status.ARCHIVE);
     Collections.sort(archivedContests, new ContestStartTimeComparator());
     Contest resultLastContest = archivedContests.get(archivedContests.size()-1);
			 
	 List<ContestPass> contestPasses = contestPassService.getContestPasses(resultLastContest.getId());
	 Collections.sort(contestPasses);
	 
	 List<ContestPassJson> jsonData = new ArrayList<>();
	 
     for (ContestPass contestPass : contestPasses) {
         jsonData.add(ContestPassJson.createContestPassJson(contestPass));
     }
     return jsonData;
	}
}
