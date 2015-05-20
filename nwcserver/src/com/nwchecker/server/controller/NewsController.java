package com.nwchecker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getLastNews(Model model) {

		Contest contest = newsService.getNearestContest();
		model.addAttribute("contest", contest);

		List<ContestPassJson> jsonData = newsService.getLastArchivedContest();
		model.addAttribute("result", jsonData);

		String titleLastContest = newsService.getNameLastContest();
		model.addAttribute("title", titleLastContest);

		return "nwcserver.static.news";
	}

}