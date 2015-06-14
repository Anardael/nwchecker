package com.nwchecker.server.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component("FacebookMailSender")
@Scope("prototype")
public class FacebookMailSender implements Runnable {
    private Thread thread;
    private String email;
    private String username;
    private String password;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void run() {
        sendMessage();
        System.out.println("Finish email-thread with parameters: {" + email + ", " + username + ", " + password + "}");
    }

    public void sendMessageWithParameters(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;

        thread = new Thread(this);
        System.out.println("Create email-thread with parameters: {" + email + ", " + username + ", " + password + "}");
        thread.start();
    }

    synchronized private void sendMessage(){
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
