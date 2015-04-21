package com.nwchecker.server.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.utils.ContestStartTimeComparator;

@Controller
public class NewsController {

    @Autowired
    private ContestService contestService;
    
    @Autowired
    private ContestPassService contestPassService;
        
    @RequestMapping("/news")
    public String getNextContest(Model model){
    	 System.out.println("Test last contest ");
    	 List<Contest> avalaibleContests = contestService.getContestByStatus(Status.PREPARING);    	 
    	 Collections.sort(avalaibleContests , new ContestStartTimeComparator() );   
    	 Contest first = avalaibleContests.get(0);
    	 model.addAttribute("contest",first);
    	 
    	 List<Contest> archivedContests = contestService.getContestByStatus(Contest.Status.ARCHIVE);
         Collections.sort(archivedContests, new ContestStartTimeComparator());
         Contest resultLastContest = archivedContests.get(archivedContests.size()-1);
    			 
    	 List<ContestPass> contestPasses = contestPassService.getContestPasses(resultLastContest.getId());
    	 List<ContestPassJson> jsonData = new ArrayList<>();
    	 
         for (ContestPass contestPass : contestPasses) {
             jsonData.add(ContestPassJson.createContestPassJson(contestPass));
         }
      
    	 model.addAttribute("result",jsonData);
    	
      	     	 
            return "news/news";
    }
    

}