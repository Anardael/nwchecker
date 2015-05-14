package com.nwchecker.server.controller;

import com.nwchecker.server.service.RuleService;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.wrapper.RuleWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller("RuleController")
public class RuleController {
    private static final Logger LOG = Logger.getLogger(RuleController.class);

    @Autowired
    private RuleService ruleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rules")
    public String showRules(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userData", userService.getUserByUsername(username));
            LOG.info("\"" + principal.getName() + "\" initialized rules page.");
        } else {
            model.addAttribute("userData", null);
        }

        RuleWrapper ruleWrapper = new RuleWrapper(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);

        return "rule/rules";
    }

    @RequestMapping(value = "/editRules", method = {RequestMethod.GET, RequestMethod.POST})
    public String editRules(Model model, @ModelAttribute("ruleWrapper") RuleWrapper ruleWrapper, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userData", userService.getUserByUsername(username));
            LOG.info("\"" + principal.getName() + "\" initialized editRules page.");
        } else {
            model.addAttribute("userData", null);
        }

        ruleService.updateRules(ruleWrapper.getRuleList());

        ruleWrapper = new RuleWrapper(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);

        return "rule/rules";
    }
}
