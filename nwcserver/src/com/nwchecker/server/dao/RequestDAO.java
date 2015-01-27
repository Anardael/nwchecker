package com.nwchecker.server.dao;

import com.nwchecker.server.model.Request;

import java.util.List;

/**
 * Created by ReaktorDTR on 26.01.2015.
 */
public interface RequestDAO {

    public void addRequest(Request request);

    public void updateRequest(Request request);

    public void deleteRequest(Request request);

    public List<Request> getListRequestsWithTypeByObjectId(int objectId, String typeRequest);

}
