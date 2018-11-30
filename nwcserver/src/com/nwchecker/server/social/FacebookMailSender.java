package com.nwchecker.server.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component("FacebookMailSender")
public class FacebookMailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(final String email, final String username, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
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
        }).start();
    }
}
