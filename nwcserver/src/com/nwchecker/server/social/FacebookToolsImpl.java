package com.nwchecker.server.social;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service("FacebookTools")
public class FacebookToolsImpl implements FacebookTools {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public boolean loginFacebookUserByEmailAndNickname(String email, String nickname) {
        if(userDAO.hasEmail(email)){
            User user = userDAO.getUserByEmail(email);

            addUserToSecurityContext(user.getUsername());
            return false;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            long rand = Math.round(Math.random()*1000);
            user.setUsername("FacebookUser" + rand);
            user.setPassword(encoder.encode("Facebook" + rand));
            user.setEmail(email);
            user.setEnabled(true);
            user.addRole("ROLE_USER");
            userDAO.addUser(user);

            addUserToSecurityContext(user.getUsername());
            sendMessageToEmail(email, "FacebookUser" + rand, "Facebook" + rand);

            return true;
        }


    }

    private void addUserToSecurityContext(String username){
        UserDetails details = userDetailsService.loadUserByUsername(username);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void sendMessageToEmail(String email, String username, String password){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("nwchecker@gmail.com");
            helper.setTo(email);
            helper.setSubject("Login and password for NWCServer");
            helper.setText("Your login information\n" + "Username: " + username + "\nPassword: " + password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
