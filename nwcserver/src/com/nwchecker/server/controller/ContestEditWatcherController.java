package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.service.ContestEditWatcherService;
import com.nwchecker.server.service.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * <h1>Contest Edit Watcher Controller</h1>
 * This spring controller contains mapped methods, that used
 * to synchronize teachers access and prevent simultaneous editing
 * of the same contest by multiple users.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
 */
@Controller("ContestEditWatcherController")
public class ContestEditWatcherController {

    @Autowired
    ContestEditWatcherService contestEditWatcherService;

    @RequestMapping(value = "/checkContestIsEdited", method = RequestMethod.GET)
    public @ResponseBody boolean checkContestIsEdited(@RequestParam("id") int contestId) {
        return contestEditWatcherService.checkContestIsEditedById(contestId);
    }
}
