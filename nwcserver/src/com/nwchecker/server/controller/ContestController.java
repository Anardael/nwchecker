/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.validators.ContestValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Роман
 */
@Controller
public class ContestController {

    @Autowired
    private ContestService contestService;

    @RequestMapping("/getContests")
    public String getContests(Model model) {
        //get list of all contests and forwards it to view:
        List<Contest> contests = contestService.getContests();
        model.addAttribute("contests", contests);
        return "contest";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.GET)
    public String initAddContest(Model model) {
        //prepare contest for view:
        Contest contest = new Contest();
        contest.setId(-1);
        model.addAttribute("contestAddForm", contest);
        return "contestCreate";
    }

    @RequestMapping(value = "/editContest", method = RequestMethod.GET, params = "id")
    public String initEditContest(Model model, @RequestParam("id") int id) {
        //get Contest by id:
        Contest editContest = contestService.getContestByID(id);
        model.addAttribute("contestAddForm", editContest);
        return "contestCreate";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public String addContest(Model model, @ModelAttribute("contestAddForm") Contest contestAddForm,
            BindingResult bindingResult) {
        //manual validate:
        new ContestValidator().validate(contestAddForm, bindingResult);
        //if there are errors in field input:
        if (bindingResult.hasErrors()) {
            return "contestCreate";
        }
        System.out.println(contestAddForm.getId());
        //if contest id==-1 - its new
        if (contestAddForm.getId() == -1) {
            contestService.addContest(contestAddForm);
            model.addAttribute("result", new String("Task have been successfully added"));
        } else {
            //if id!=0- contest already exist in DB
            contestService.updateContest(contestAddForm);
            model.addAttribute("result", new String("Task have been successfully edited"));
        }

        return "result";
    }

}
