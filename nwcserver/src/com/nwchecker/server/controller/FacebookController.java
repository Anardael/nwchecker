package com.nwchecker.server.controller;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.json.FacebookUserJson;
import com.nwchecker.server.service.FacebookService;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("FacebookController")
public class FacebookController {

    @Autowired
    FacebookService facebookService;

    @RequestMapping(value = "/checkFacebookUser", method = RequestMethod.GET)
    public @ResponseBody boolean getFacebookJson(@RequestParam("nickname") String nickname,
                                                     @RequestParam("email") String email){
        return facebookService.checkFacebookJsonByEmailAndNickname(email, nickname);
    }
}
