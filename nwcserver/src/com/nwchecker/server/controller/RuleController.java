package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.service.RuleService;
import com.nwchecker.server.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller("RuleController")
public class RuleController {    
    @Autowired
    private RuleService ruleService;

    @Autowired
    private UserService userService;
    @Link(label="rules.caption", family="rules", parent = "")
    @RequestMapping(value = "/rules")
    public String showRules(Model model) {
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("pageName", "rules");

        return "nwcserver.static.rules";
    }

    @RequestMapping(value = "/editRule", method = {RequestMethod.GET, RequestMethod.POST})
    public String editRule(Model model, @RequestParam("content") String ruleContent, @RequestParam("id") int ruleId) {
        ruleService.updateRuleContentById(ruleId, ruleContent);

        return showRules(model);
    }
}
