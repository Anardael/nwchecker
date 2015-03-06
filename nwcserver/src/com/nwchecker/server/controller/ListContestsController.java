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
 * Created by ReaktorDTR on 06.02.2015.
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/listContests", method = RequestMethod.GET)
    public String userRequests(Principal principal) {
        LOG.info("\"" + principal.getName() + "\" entered to administrator page \"List of contests\".");
        return "adminOptions/listContests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getListOfContests", method = RequestMethod.GET)
    @ResponseBody
    public List<ListContestsJson> getListOfContests(Principal principal) {
        LOG.info("\"" + principal.getName() + "\" tries to receive list of contests.");
        List<Contest> contests = contestService.getContests();
        LinkedList<ListContestsJson> listContestsJson = new LinkedList<>();
        for (Contest contest : contests) {
            ListContestsJson lc = new ListContestsJson(contest);
            listContestsJson.add(lc);
        }
        LOG.info("\"" + principal.getName() + "\" received list of contests.");
        return listContestsJson;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getContestStatus", method = RequestMethod.GET)
    @ResponseBody
    public StatusContestJson getContestStatus(@RequestParam("contestId") int contestId, Principal principal) {
        StatusContestJson status = new StatusContestJson();
        LOG.info("\"" + principal.getName() + "\" tries to receive contest status.");
        status.setStatusContest(contestService.getContestByID(contestId).getStatus().toString());
        status.setContestHidden(contestService.getContestByID(contestId).isHidden());
        LOG.info("\"" + principal.getName() + "\" received contest status.");
        return status;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/setContestStatus.do", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse setContestStatus(@RequestParam("contestId") int contestId, @RequestParam("contestStatus") Contest.Status contestStatus,
                                        @RequestParam("contestHidden") boolean contestHidden, Principal principal) {
        ValidationResponse result = new ValidationResponse();
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
        //return SUCCESS status:
        result.setStatus("SUCCESS");
        return result;
    }
}

