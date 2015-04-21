package com.nwchecker.server.controller;

import com.nwchecker.server.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("RuleController")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/donec")
    public String showRules(Model model){
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag("ENG"));
        return "rule/rule";
    }
}
