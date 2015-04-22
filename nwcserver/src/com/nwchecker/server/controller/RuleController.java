package com.nwchecker.server.controller;

import com.nwchecker.server.model.Rule;
import com.nwchecker.server.service.RuleService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        model.addAttribute("ruleList", ruleService.getRulesByLanguageTag(LocaleContextHolder.getLocale().toString()));
        return "rule/rules";
    }
}
