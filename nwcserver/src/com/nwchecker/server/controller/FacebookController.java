package com.nwchecker.server.controller;

import com.nwchecker.server.social.FacebookTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller("FacebookController")
public class FacebookController {

    @Autowired
    FacebookTools facebookTools;

    @RequestMapping(value = "/loginFacebookUser", method = RequestMethod.GET)
    public String loginFacebookUser(@RequestParam("nickname") String nickname,
                                  @RequestParam("email") String email,
                                  Model model,
                                  HttpServletRequest request){
        boolean isFirstLogin = facebookTools.loginFacebookUserByEmailAndNickname(email, nickname);
        if(isFirstLogin){
            model.addAttribute("nickname", nickname);
            model.addAttribute("email", email);
        }
        model.addAttribute("isFirstLogin", isFirstLogin);
        request.getSession().setAttribute("nickname", nickname);

        return "nwcserver.static.index";
    }
}
