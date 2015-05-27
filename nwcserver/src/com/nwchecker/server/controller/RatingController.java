package com.nwchecker.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.json.wrapper.FilteredResultProvider;
import com.nwchecker.server.json.wrapper.MorphedResult;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.RatingService;
import com.nwchecker.server.service.ScoreCalculationService;
import com.nwchecker.server.utils.ContestStartTimeComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
	 * @param model
	 *            Spring Framework model for this page
	 * @return <b>CintestRating.jsp</b> Returns page with completed contests
	 *         list
	 */
	@Link(label = "rating.caption", family = "contestRating", parent = "")
	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public String getRating(Model model) {
		model.addAttribute("ratingContests",
				contestService.getContestForRating());

		return "nwcserver.contests.rating";
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
		model.addAttribute("dynamic",
				ratingService.scoreCalculateIfDynamicContest(contestId));
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

		return "nwcserver.contests.results";
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
	public @ResponseBody String getResultsList(
			@RequestParam(value = "id") int contestId) {
		List<ContestPass> contestPasses = ratingService
				.getJsonListForContestPassByContestId(contestId);
		List<MorphedResult<ContestPass>> morphedContestPass = new LinkedList<MorphedResult<ContestPass>>();
		for (ContestPass contestPass : contestPasses) {
			MorphedResult<ContestPass> morphedPass = new MorphedResult<ContestPass>(
					contestPass);
			morphedPass.addExpansionData("displayName", contestPass.getUser()
					.getDisplayName());
			morphedPass.addExpansionData("tasksPassedCount",
					contestPass.getPassedCount() + "/"
							+ contestPass.getContest().getTasks().size());

			morphedContestPass.add(morphedPass);
		}
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.setFilters(new FilteredResultProvider());
		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String response = null;
		try {
			response = jsonMapper.writeValueAsString(morphedContestPass);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
