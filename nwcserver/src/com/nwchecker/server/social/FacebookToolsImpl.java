package com.nwchecker.server.social;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;
import com.nwchecker.server.utils.LoginInformationGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("FacebookTools")
public class FacebookToolsImpl implements FacebookTools {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    FacebookMailSender facebookMailSender;

    @Override
    public boolean loginFacebookUserByEmailAndNickname(String email, String nickname) {
        if(userDAO.hasEmail(email)){
            User user = userDAO.getUserByEmail(email);

            addUserToSecurityContext(user.getUsername());
            return false;
        } else {
            Map<String, String> loginData = LoginInformationGenerator.generateLoginData();
            User user = createUser(email, loginData.get("username"), loginData.get("password"), nickname);
            userDAO.addUser(user);
            addUserToSecurityContext(user.getUsername());
            facebookMailSender.sendMessage(email, loginData.get("username"), loginData.get("password"));

            return true;
        }
    }

    private void addUserToSecurityContext(String username){
        UserDetails details = userDetailsService.loadUserByUsername(username);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private User createUser(String email, String username, String password, String nickname){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setDisplayName(nickname);
        user.setEnabled(true);
        user.addRole("ROLE_USER");

        return user;
    }
}
