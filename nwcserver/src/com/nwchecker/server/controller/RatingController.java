package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.RatingService;
import com.nwchecker.server.service.ScoreCalculationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Controller("RatingController")
public class RatingController {

	@Autowired
	private ContestService contestService;
	@Autowired
	private ContestPassService contestPassService;
	@Autowired
	private ScoreCalculationService scoreCalculationService;
	@Autowired
	private RatingService ratingService;

	/**
	 * This mapped method used to return page when user can view completed
	 * contests.
	 * <p/>
	 *
	 * // * @param model Spring Framework model for this page
	 * 
	 * @return <b>CintestRating.jsp</b> Returns page with completed contests
	 *         list
	 */
	@Link(label = "rating.caption", family = "contestRating", parent = "")
	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public String getRating(Model model) {
		model.addAttribute("pageName", "rating");
		return "nwcserver.rating.calculated";
	}

	@RequestMapping(value = "/ratingContest", method = RequestMethod.GET)
	public @ResponseBody List<Contest> getContestForRating() {
		return contestService.getContestForRating();
	}

	/**
	 * This mapped method used to show page that contains contest results of
	 * contest participants.
	 * <p/>
	 *
	 * @param model
	 *            Spring Framework model for this page
	 * @param contestId
	 *            ID of contest
	 * @return <b>contestResults.jsp</b> Returns page with contest statistic
	 */
	@Link(label = "results.caption", family = "contestRating", parent = "rating.caption")
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String getResults(Model model,
			@RequestParam(value = "id") int contestId) {
		Contest contest = contestService.getContestByID(contestId);
		model.addAttribute("contestId", contestId);
		model.addAttribute("dynamic",contest.getTypeContest().isDynamic());
		model.addAttribute("contestTitle", contest.getTitle());
		SimpleDateFormat formatStart = new SimpleDateFormat();
		model.addAttribute("contestStart",
				formatStart.format(contest.getStarts()));
		Calendar contestDuration = Calendar.getInstance();
		contestDuration.setTime(contest.getDuration());
		model.addAttribute("contestDurationHours",
				contestDuration.get(Calendar.HOUR));
		model.addAttribute("contestDurationMinutes",
				contestDuration.get(Calendar.MINUTE));

		return "nwcserver.rating.show";
	}

	/**
	 * This mapped method used to return results of contest participants in JSON
	 * format.
	 * <p/>
	 *
	 * @param contestId
	 *            ID of contest
	 * @return <b>JSON</b> Returns <b>results of contest participants</b>
	 */
	@RequestMapping(value = "/resultsList", method = RequestMethod.GET)
	public @ResponseBody List<ContestPassJson> getResultsList(
			@RequestParam(value = "id") int contestId) {
		List<ContestPass> contestPasses = ratingService
				.getJsonListForContestPassByContestId(contestId);
		List<ContestPassJson> contestPassJson = new LinkedList<ContestPassJson>();
		for (ContestPass contestPass : contestPasses) {
			contestPassJson.add(ContestPassJson
					.createContestPassJson(contestPass));
		}
		return contestPassJson;
	}
}
