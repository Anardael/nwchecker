package com.nwchecker.server.controller;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.Rule;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.RuleService;
import com.nwchecker.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller("RuleController")
public class RuleController{

    private static final Logger LOG = Logger.getLogger(RuleController.class);

    @Autowired
    private RuleService ruleService;

    @Autowired
    private UserService userService;

    /*@PreAuthorize("isAuthenticated()")*/
    @RequestMapping(value = "/donec", method = {RequestMethod.GET, RequestMethod.POST})
    public String showRules(Model model, Principal principal){
        if(principal != null){
            String username = principal.getName(); // get logged in username
            model.addAttribute("userData", userService.getUserByUsername(username));
            LOG.info("\"" + principal.getName() + "\" initialized rule page.");
        } else{
            System.out.println("NULL USER!");
            model.addAttribute("userData", null);
        }
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));

        model.addAttribute("testRule", new Rule());
        return "rule/rules";
    }

    @RequestMapping("/donec/edit")
    public String editRules(Model model, /*@ModelAttribute("ruleList") List<Rule> rules,*/ @ModelAttribute("testRule") Rule rule){
        System.out.println("RULE WAS EDITED!!!");
        /*for(Rule r : rules){
            System.out.println(r);
        }*/
        System.out.println(rule);
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        return "rule/rules";
    }
}
