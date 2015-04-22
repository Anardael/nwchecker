package com.nwchecker.server.controller;

import com.nwchecker.server.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Controller("RuleController")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private CookieLocaleResolver localeResolver;

    @RequestMapping("/donec")
    public String showRules(Model model){
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag("ENG"));
        return "rule/rules";
    }
}
