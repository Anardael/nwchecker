package com.nwchecker.server.controller;

import com.nwchecker.server.json.ListContestsJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ReaktorDTR on 06.02.2015.
 */
@Controller
@SessionAttributes("user")
public class ListContests {
    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

    @Autowired
    private ContestService contestService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/listContests", method = RequestMethod.GET)
    public String userRequests(Principal principal) {
        LOG.info("\"" + principal.getName()
                + "\" entered to administrator page \"List of contests\".");
        return "adminOptions/listContests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getListOfContests", method = RequestMethod.GET)
    @ResponseBody
    public List<ListContestsJson> getListOfContests(Principal principal) {
        LOG.info("\"" + principal.getName()
                + "\" tries to receive list of contests.");
        List<Contest> contests = contestService.getContests();
        LinkedList<ListContestsJson> listContestsJsons = new LinkedList<>();
        for (Contest contest : contests) {
            ListContestsJson lc = new ListContestsJson(contest);
            listContestsJsons.add(lc);
        }
        LOG.info("\"" + principal.getName()
                + "\" received list of contests.");
        return listContestsJsons;
    }

    @RequestMapping(value = "/listTeachersModal", method = RequestMethod.GET)
    public String listTeachersModal(@RequestParam("contestId") String contestId, Model model) {
        model.addAttribute("contestId", contestId);
        return "adminOptions/listTeachersModal";
    }

}
