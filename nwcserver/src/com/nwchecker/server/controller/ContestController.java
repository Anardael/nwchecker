/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

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
    public String contextCreation(Model model) {
        //creates new Contest and forwards it to view:
        model.addAttribute("contestAddForm", new Contest());
        return "contestCreate";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid @ModelAttribute("contestAddForm") Contest contestAddForm,
            BindingResult bindingResult) {
        //if there are errors in field input:
        if (bindingResult.hasErrors()) {
            System.out.println("contest title : " + contestAddForm.getTitle());
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println(e.toString());
            }
            return "contestCreate";
        }
        System.out.println(contestAddForm.getDescription());
        return "redirect:/results";
    }

}
