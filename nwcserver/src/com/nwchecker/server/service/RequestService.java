package com.nwchecker.server.service;

import com.nwchecker.server.model.Request;

import java.util.List;

/**
 * Created by ReaktorDTR on 26.01.2015.
 */
public interface RequestService {

    public void addRequest(Request request);

    public void deleteRequest(Request request);

    public boolean hasObjectRequestWithType(int objectId, String typeRequest);

}
