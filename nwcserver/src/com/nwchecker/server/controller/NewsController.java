package com.nwchecker.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;

@Controller
public class NewsController {

	@Autowired
	private ContestService contestService;
	
	@Link(label="news.caption", family="News", parent = "")
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getLastNews(Model model) {

		Contest contest = contestService.getNearestContest();
		model.addAttribute("contest", contest);


		Contest lastContest = contestService.getLastArchivedContest();
		model.addAttribute("title", lastContest.getTitle());
		model.addAttribute("dynamic", lastContest.getTypeContest().isDynamic());
		model.addAttribute("contestId", lastContest.getId());
		model.addAttribute("pageName", "news");

		return "nwcserver.static.news";
	}

}
