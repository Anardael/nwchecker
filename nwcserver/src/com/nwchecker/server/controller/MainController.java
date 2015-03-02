package com.nwchecker.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

    @RequestMapping("/index.do")
    public String main(HttpSession session, Model model) throws IllegalArgumentException {
        return "index";
    }

    @RequestMapping({"/inDevelopment", "/donec", "/vestibulum", "/etiam", "/phasellus", "/news", "/rating"})
    public String willBeCreated(HttpSession session, Model model) throws IllegalArgumentException {
        return "access/inDevelopment";
    }

    @RequestMapping(value = "/getServerTime", method = RequestMethod.GET)
    @ResponseBody
    public String getServerTime() throws IllegalArgumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
