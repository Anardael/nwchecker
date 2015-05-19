package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
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
import java.util.List;


@Controller("RatingController")
public class RatingController {

    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestPassService contestPassService;
    @Autowired
    private ScoreCalculationService scoreCalculationService;

    /**
     * This mapped method used to return page when user
     * can view completed contests.
     * <p/>
     *
     * @param model Spring Framework model for this page
     * @return <b>rating.jsp</b> Returns page with completed contests list
     */
    @Link(label="Rating", family="contestRating", parent = "")
    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    public String getRating(Model model) {
        List<Contest> ratingContests = contestService.getContestForRating();
        Collections.sort(ratingContests, new ContestStartTimeComparator());
        Collections.reverse(ratingContests);

        model.addAttribute("ratingContests", ratingContests);

        return "nwcserver.contests.rating";
    }

    /**
     * This mapped method used to show page that contains
     * contest results of contest participants.
     * <p/>
     *
     * @param model Spring Framework model for this page
     * @param id    ID of contest
     * @return <b>contestResults.jsp</b> Returns page with contest statistic
     */
    @Link(label="Results", family="contestRating", parent = "Rating")
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String getResults(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("contestId", id);
        Contest contest = contestService.getContestByID(id);
        if (contest.getTypeContest() != null && contest.getTypeContest().isDynamic()) {
            scoreCalculationService.calculateScore(id);
            model.addAttribute("currentContestFirstTaskId", contest.getTasks().get(0).getId());
        } else  {
            model.addAttribute("currentContestFirstTaskId", null);
        }
        model.addAttribute("contestTitle", contest.getTitle());
        SimpleDateFormat formatStart = new SimpleDateFormat();
        model.addAttribute("contestStart", formatStart.format(contest.getStarts()));
        Calendar contestDuration = Calendar.getInstance();
        contestDuration.setTime(contest.getDuration());
        model.addAttribute("contestDurationHours", contestDuration.get(Calendar.HOUR));
        model.addAttribute("contestDurationMinutes", contestDuration.get(Calendar.MINUTE));
        return "nwcserver.contests.results";
    }

    /**
     * This mapped method used to return results of contest
     * participants in JSON format.
     * <p/>
     *
     * @param id ID of contest
     * @return <b>JSON</b> Returns <b>results of contest participants</b>
     */
    @RequestMapping(value = "/resultsList", method = RequestMethod.GET)
    public @ResponseBody List<ContestPassJson> getResultsList(@RequestParam(value = "id") int id) {
        List<ContestPass> contestPasses = contestPassService.getContestPasses(id);
        Collections.sort(contestPasses);
        List<ContestPassJson> jsonData = new ArrayList<>();
        for (ContestPass contestPass : contestPasses) {
            jsonData.add(ContestPassJson.createContestPassJson(contestPass));
        }
        return jsonData;
    }
}
