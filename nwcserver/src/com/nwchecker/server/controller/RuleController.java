package com.nwchecker.server.controller;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.Rule;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.RuleService;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.wrapper.RuleWrapper;
import com.nwchecker.server.wrapper.WrapperList;
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

    @RequestMapping(value = "/rules")
    public String showRules(Model model, Principal principal){
        if(principal != null){
            String username = principal.getName();
            model.addAttribute("userData", userService.getUserByUsername(username));
            LOG.info("\"" + principal.getName() + "\" initialized rules page.");
        } else{
            model.addAttribute("userData", null);
        }

        RuleWrapper ruleWrapper = new RuleWrapper(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);

        /*WrapperList<Rule> ruleWrapper = new WrapperList<Rule>(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);*/

        return "rule/rules";
    }

    @RequestMapping(value = "/editRules", method = {RequestMethod.GET, RequestMethod.POST})
    public String editRules(Model model, @ModelAttribute("ruleWrapper") RuleWrapper ruleWrapper, Principal principal){
        if(principal != null){
            String username = principal.getName(); // get logged in username
            model.addAttribute("userData", userService.getUserByUsername(username));
            LOG.info("\"" + principal.getName() + "\" initialized editRules page.");
        } else{
            model.addAttribute("userData", null);
        }

        ruleService.updateRules(ruleWrapper.getRuleList());

        ruleWrapper = new RuleWrapper(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);

        /*ruleService.updateRules(ruleWrapper.getDataList());

        ruleWrapper = new WrapperList<Rule>(ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        model.addAttribute("ruleWrapper", ruleWrapper);*/

        return "rule/rules";
    }
}
