package com.nwchecker.server.controller;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("FacebookController")
public class FacebookController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getFacebookUser", method = RequestMethod.GET)
    public @ResponseBody boolean getFacebookUserJson(@RequestParam("nickname") String nickname,
                                                 @RequestParam("email") String email){
        if(userService.hasEmail(email)){
            return true;
        }
        return userService.hasEmail(email);
    }
}
