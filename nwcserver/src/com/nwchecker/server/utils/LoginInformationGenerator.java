package com.nwchecker.server.utils;


import java.util.HashMap;
import java.util.Map;

public class LoginInformationGenerator {
    public static Map<String, String> generateLoginData(){
        long rand = Math.round(Math.random()*1000);

        final String generatedUsername = "FacebookUser" + rand;
        final String generatedPassword = "Facebook" + rand;

        return new HashMap<String, String>(){{
            put("username", generatedUsername);
            put("password", generatedPassword);
        }};
    }
}
