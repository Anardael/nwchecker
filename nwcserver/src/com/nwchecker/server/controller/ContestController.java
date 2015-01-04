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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Роман
 */
@Controller
public class ContestController{

    @Autowired
    private ContestService contestService;

    @RequestMapping("/getContests")
    public String getContests(Model model) {
        System.out.println("asd");
        List<Contest> contests = contestService.getContests();
        model.addAttribute("contests", contests);
        return "contest";
    }

    @RequestMapping("/contestCreating")
    public String contextCreation(Contest contest) {
        return "contestCreate";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid Contest contest, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println(e.toString());
            }
            return "contestCreate";
        }
        return "redirect:/results";
    }

}
