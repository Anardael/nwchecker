package com.nwchecker.server.service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.json.FacebookUserJson;
import com.nwchecker.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("FacebookService")
public class FacebookServiceImpl implements FacebookService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public boolean checkFacebookJsonByEmailAndNickname(String email, String nickname) {
        if(userDAO.hasEmail(email)){
            User user = userDAO.getUserByEmail(email);

            addUserToSecurityContext(user.getUsername());
            return false;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            long rand = Math.round(Math.random()*100);
            user.setUsername("FacebookUser" + rand);
            user.setPassword(encoder.encode("Facebook" + rand));
            user.setEmail(email);
            user.setEnabled(true);
            user.addRole("ROLE_USER");
            userDAO.addUser(user);

            addUserToSecurityContext(user.getUsername());
            return true;
        }


    }

    private void addUserToSecurityContext(String username){
        UserDetails details = userDetailsService.loadUserByUsername(username);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
