package com.nwchecker.server.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.utils.ContestStartTimeComparator;

@Controller
public class NewsController {

    @Autowired
    private ContestService contestService;
        
    @RequestMapping("/news")
    public String getNextContest(Model model){
    	 List<Contest> avalaibleContests = contestService.getContestByStatus(Status.PREPARING);
    	 
    	 Collections.sort(avalaibleContests , new ContestStartTimeComparator() );
    
    	 Contest first = avalaibleContests.get(0);
    	 model.addAttribute("contest",first);
            return "news/news";
    }
    
    @RequestMapping("/news")
    public String getResultLastContest(Model model){
    	List<Contest> avalaibleContests = contestService.getContestByStatus(Status.ARCHIVE);
    	Collections.sort(avalaibleContests , new ContestStartTimeComparator() );
    	
    	Contest last = avalaibleContests.get(avalaibleContests.size()-1);
    	model.addAttribute("contest",last);
           return "news/news";
    }
    

}