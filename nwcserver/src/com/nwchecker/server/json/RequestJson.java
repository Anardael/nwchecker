package com.nwchecker.server.json;

import com.nwchecker.server.model.UserRequest;

/**
 * <h1>Request Json</h1>
 * JSON object that contains valuable data of some Request object.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-07
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
