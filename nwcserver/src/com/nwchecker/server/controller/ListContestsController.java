package com.nwchecker.server.controller;

import com.nwchecker.server.json.ListContestsJson;
import com.nwchecker.server.json.StatusContestJson;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.ScheduleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>List Contests Controller</h1>
 * This spring controller contains mapped methods, that
 * allows administrator to view list of contests and
 * edit contests parameters.
 * <p>
 * <b>Note:</b>Only ADMIN allows to use this methods.
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 * @since 2015-02-06
 */
@Controller
@SessionAttributes("user")
public class ListContestsController {
    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

    @Autowired
    private ContestService contestService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * This mapped method used to return page where admin can
     * view all contests.
     * <p>
     * <b>Note:</b>Only ADMIN has rights to use this method.
     *
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return <b>listContests.jsp</b> Returns page when admin can watch contests
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/listContests", method = RequestMethod.GET)
    public String userRequests(Principal principal, Model model) {
        LOG.info("\"" + principal.getName() + "\" entered to administrator page \"List of contests\"."); 
        model.addAttribute("pageName", "listContests");
        return "nwcserver.adminOptions.listContests";
    }

    /**
     * This mapped method used to list of contests in JSON.
     * <p>
     * <b>Note:</b>Only ADMIN has rights to use this method.
     *
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return Returns <b>List of Contests</b> in JSON format
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getListOfContests", method = RequestMethod.GET)
    @ResponseBody
    public List<ListContestsJson> getListOfContests(Principal principal) {
        LOG.info("\"" + principal.getName() + "\" tries to receive list of contests.");
        List<Contest> contests = contestService.getContests();
        LinkedList<ListContestsJson> listContestsJson = new LinkedList<>();
        for (Contest contest : contests) {
            listContestsJson.add(ListContestsJson.createListContestsJson(contest));
        }
        LOG.info("\"" + principal.getName() + "\" received list of contests.");
        return listContestsJson;
    }

    /**
     * This mapped method used to return contest status.
     * <p>
     * <b>Note:</b>Only ADMIN has rights to use this method.
     *
     * @param contestId ID of contest
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return Contest status
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getContestStatus", method = RequestMethod.GET)
    @ResponseBody
    public StatusContestJson getContestStatus(@RequestParam("contestId") int contestId, Principal principal) {
        LOG.info("\"" + principal.getName() + "\" tries to receive contest status.");
        String contestStatus = contestService.getContestByID(contestId).getStatus().toString();
        boolean isContestHidden = contestService.getContestByID(contestId).isHidden();
        LOG.info("\"" + principal.getName() + "\" received contest status.");
        return StatusContestJson.createStatusContestJson(contestStatus, isContestHidden);
    }

    /**
     * This mapped method used to change selected contest status.
     * <p>
     * <b>Note:</b>Only ADMIN has rights to use this method.
     *
     * @param contestId ID of contest
     * @param contestStatus New contest status
     * @param contestHidden <b>true</b> if contest need to be hidden
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return Returns "SUCCESS" status.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/setContestStatus", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse setContestStatus(@RequestParam("contestId") int contestId, @RequestParam("contestStatus") Contest.Status contestStatus,
                                        @RequestParam("contestHidden") boolean contestHidden, Principal principal) {
        Contest contest = contestService.getContestByID(contestId);
        boolean isContestStatusChanged = false;
        boolean isContestHiddenChanged = false;
        if (!contest.getStatus().toString().equals(contestStatus) && contest.getStatus() != Contest.Status.ARCHIVE) {
            switch (contestStatus) {
                case GOING: {
                    contest.setStarts(new Date());
                    contest.setStatus(Contest.Status.GOING);
                    isContestStatusChanged = true;
                    break;
                }
                case PREPARING: {
                    contest.setStatus(Contest.Status.PREPARING);
                    isContestStatusChanged = true;
                    break;
                }
                case RELEASE: {
                    contest.setStatus(Contest.Status.RELEASE);
                    isContestStatusChanged = true;
                    break;
                }
                case ARCHIVE: {
                    contest.setStatus(Contest.Status.ARCHIVE);
                    isContestStatusChanged = true;
                    break;
                }
            }
        }
        if (contest.isHidden() != contestHidden) {
            contest.setHidden(contestHidden);
            isContestHiddenChanged = true;
        }

        if (isContestStatusChanged) {
            contestService.mergeContest(contest);
            scheduleService.refresh();
        } else if (isContestHiddenChanged) {
            contestService.mergeContest(contest);
        }
        //return SUCCESS status (in JSON):
        return ValidationResponse.createValidationResponse("SUCCESS");
    }
}

