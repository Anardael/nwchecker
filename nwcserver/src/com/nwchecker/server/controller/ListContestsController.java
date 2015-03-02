package com.nwchecker.server.controller;

import com.nwchecker.server.json.ListContestsJson;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
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
    public String getContestStatus(@RequestParam("contestId") int contestId, Principal principal) {
        LOG.info("\"" + principal.getName() + "\" tries to receive contest status.");
        String status = contestService.getContestByID(contestId).getStatus().toString();
        LOG.info("\"" + principal.getName() + "\" received contest status.");
        return status;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/setContestStatus.do", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse setContestStatus(@RequestParam("contestId") int contestId, @RequestParam("contestStatus") String contestStatus,
                                        Principal principal) {
        ValidationResponse result = new ValidationResponse();
        Contest contest = contestService.getContestByID(contestId);
        switch (contestStatus) {
            case "GOING": {
                contest.setStatus(Contest.Status.GOING);
                break;
            }
            case "PREPARING": {
                contest.setStatus(Contest.Status.PREPARING);
                break;
            }
            case "RELEASE": {
                contest.setStatus(Contest.Status.RELEASE);
                break;
            }
            case "ARCHIVE": {
                contest.setStatus(Contest.Status.ARCHIVE);
                break;
            }
        }
        contestService.mergeContest(contest);
        //return SUCCESS status:
        result.setStatus("SUCCESS");
        return result;
    }
}
