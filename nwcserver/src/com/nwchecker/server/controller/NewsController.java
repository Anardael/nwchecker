package com.nwchecker.server.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.NewsService;
import com.nwchecker.server.utils.ContestStartTimeComparator;

@Controller
public class NewsController {

    @Autowired
    private ContestService contestService;
    
    @Autowired 
    private NewsService newsService;
    
    @Autowired
    private ContestPassService contestPassService;
//        
    @RequestMapping("/news")
    public String getNextContest(Model model){
  /*  	 List<Contest> avalaibleContests = contestService.getContestByStatus(Status.PREPARING);    	 
    	 Collections.sort(avalaibleContests , new ContestStartTimeComparator() );   
    	 Contest first = avalaibleContests.get(0);
    	 model.addAttribute("contest",first);
    	 
    	 
   */		
    	Contest contest =newsService.getNextContest();
    	model.addAttribute("contest",contest);
    	
    	 List<Contest> archivedContests = contestService.getContestByStatus(Contest.Status.ARCHIVE);
         Collections.sort(archivedContests, new ContestStartTimeComparator());
         Contest resultLastContest = archivedContests.get(archivedContests.size()-1);
    			 
    	 List<ContestPass> contestPasses = contestPassService.getContestPasses(resultLastContest.getId());
    	 Collections.sort(contestPasses);
    	


    	
//         List<ContestPassJson> jsonData = newsService.getResultLastContest();
//       
//         System.out.println("TEST   \\TEST   \\TEST   \\TEST   \\TEST   \\TEST   \\" + jsonData);
    	 model.addAttribute("result",contestPasses);
    	
      	     	 
            return "news/news";
    }
    

}