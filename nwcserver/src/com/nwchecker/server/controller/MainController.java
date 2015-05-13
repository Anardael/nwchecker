package com.nwchecker.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>Main Controller</h1>
 * This spring controller contains mapped methods,
 * that grants access to base pages.
 * <p>
 *
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * This mapped method used to return Home page.
     * <p>
     *
     * @param session Current user HttpSession object
     * @param model Spring Framework model for this page
     * @return Home page
     * @throws IllegalArgumentException If method took arguments of incorrect type
     */
    @RequestMapping("/index.do")
    public String main(HttpSession session, Model model) throws IllegalArgumentException {
    	model.addAttribute("pageName", "home");
        return "nwcserver.static.index";
    }

    /**
     * This mapped method used to return In Development page.
     * <p>
     *
     * @param session Current user HttpSession object
     * @param model Spring Framework model for this page
     * @return In Development page
     * @throws IllegalArgumentException If method took arguments of incorrect type
     */
    @RequestMapping({"/inDevelopment", "/vestibulum", "/phasellus", "/news", "/rating"})
    public String willBeCreated(HttpSession session, Model model) throws IllegalArgumentException {
    	model.addAttribute("pageName", "inDevelopment");
        return "nwcserver.inDevelopment";
    }

    /**
     * This mapped method used to return current server time.
     * <p>
     *
     * @return Current server time
     * @throws IllegalArgumentException If method took arguments of incorrect type
     */
    @RequestMapping(value = "/getServerTime", method = RequestMethod.GET)
    @ResponseBody
    public String getServerTime() throws IllegalArgumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * This mapped method used to return Access Denied page.
     * <p>
     *
     * @return Access Denied page
     * @throws IllegalArgumentException If method took arguments of incorrect type
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Model model) throws IllegalArgumentException {
    	model.addAttribute("pageName", "accessDenied403");
        return "nwcserver.403";
    }

    /**
     * This mapped method used to return Page Not Found page.
     * <p>
     *
     * @return Page Not Found page
     * @throws IllegalArgumentException If method took arguments of incorrect type
     */
    @RequestMapping(value = "/pageNotFound", method = RequestMethod.GET)
    public String pageNotFound(Model model) throws IllegalArgumentException {
    	model.addAttribute("pageName", "pageNotFound");
        return "nwcserver.404";
    }
}
