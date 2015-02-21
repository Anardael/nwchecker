package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.service.ContestEditWatcherService;
import com.nwchecker.server.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.security.Principal;

/**
 * Created by Роман on 21.02.2015.
 */
@Controller("Contes0EditWatchertController")
public class ContestEditWatcherController {
    @Autowired
    private ContestEditWatcherService contestEditWatcherService;

    @Autowired
    private ContestService contestService;

    private static final long CONTEST_EDIT_TIME_OUT_POLLING = 10000;

    private static final long REQUEST_CONTEST_STILL_EDIT_TIME_OUT = 1500;

    //method for indicate if Contest is currently editing by someone:
    @RequestMapping("/checkContestEdit")
    @ResponseBody
    public DeferredResult<String> checkIfContestEditing(Principal principal, @RequestParam(value = "id") int id) {
        final DeferredResult<String> deferredResult = new DeferredResult<>(REQUEST_CONTEST_STILL_EDIT_TIME_OUT, "OK");
        //check if user has access:
        if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), id)) {
            throw new ContestAccessDenied(principal.getName() + " tried to edit Contest. Access denied.");
        }
        String result = contestEditWatcherService.isEditing(id);
        //if nobody currently edit contest, or current user is editing contest:
        if ((result == null) || (result != null && result == principal.getName())) {
            deferredResult.setResult("OK");
            return deferredResult;
        } else {
            //if somebody other is editing contest:
            //send him timeOut and check if he will reconnect:
            contestEditWatcherService.setDeferredResult(id, "timeOut");
            //put assycnhronous response to request map:
            contestEditWatcherService.addRequestStillContestEditing(id, deferredResult);
            return deferredResult;
        }
    }

    //method for set Contest editing by user:
    @RequestMapping("/editingContest")
    @ResponseBody
    public DeferredResult<String> editingContest(Principal principal, @RequestParam(value = "id") final int id) {
        //asynchronous response object:
        final DeferredResult<String> deferredResult = new DeferredResult<>(CONTEST_EDIT_TIME_OUT_POLLING, "timeOut");
        //check if user has access:
        if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), id)) {
            throw new ContestAccessDenied(principal.getName() + " tried to edit Contest. Access denied.");
        }
        //check if contest is currently editing:
        String username = contestEditWatcherService.isEditing(id);
        if (username != null) {
            deferredResult.setResult(username);
            return deferredResult;
        }

        //of time out- delete "currentlyEditing" from contestService map:
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                contestEditWatcherService.removeContestEditingUser(id);
            }
        });
        //add "currentlyEditing" to contestService map:
        contestEditWatcherService.addContestEditingUser(id, principal.getName(), deferredResult);
        return deferredResult;
    }
}
