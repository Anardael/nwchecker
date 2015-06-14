package com.nwchecker.server.json;


import com.nwchecker.server.model.User;

public class FacebookUserJson {
    private String email;
    private String password;
    private boolean firstEntry;

    private FacebookUserJson(){}

    public static FacebookUserJson createFacebookJson(User user, boolean firstEntry){
        FacebookUserJson facebookUserJson = new FacebookUserJson();
        facebookUserJson.setEmail(user.getEmail());
        facebookUserJson.setPassword(user.getPassword());
        facebookUserJson.setFirstEntry(firstEntry);
        return facebookUserJson;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstEntry() {
        return firstEntry;
    }

    public void setFirstEntry(boolean firstEntry) {
        this.firstEntry = firstEntry;
    }
}
