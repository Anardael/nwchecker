package com.nwchecker.server.controller;

import com.nwchecker.server.model.Rule;
import com.nwchecker.server.service.RuleService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("RuleController")
public class RuleController{

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/donec")
    public String showRules(Model model){
        String content = "";
        model.addAttribute("updateContent", content);
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        return "rule/rules";
    }

    @RequestMapping("/donec/edit")
    public String editRules(Model model, @ModelAttribute("updateContent") String content){
        System.out.println("RULE WAS EDITED!!!  " + content);
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        return "rule/rules";
    }
}
