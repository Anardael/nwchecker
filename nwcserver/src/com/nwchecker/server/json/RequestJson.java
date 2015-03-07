package com.nwchecker.server.json;

import com.nwchecker.server.model.UserRequest;

/**
 * Created by Станіслав on 07.03.2015.
 */
public class RequestJson {

    private String request;

    private RequestJson() {
    }

    public static RequestJson createUserRequestsJson(UserRequest userRequest) {
        RequestJson json = new RequestJson();
        json.request = userRequest.getRequest();
        return json;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
