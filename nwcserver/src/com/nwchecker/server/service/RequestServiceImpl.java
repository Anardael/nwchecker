package com.nwchecker.server.service;

import com.nwchecker.server.dao.RequestDAO;
import com.nwchecker.server.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ReaktorDTR on 26.01.2015.
 */
@Service("RequestService")
@Transactional
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDAO requestDAO;

    @Override
    public void addRequest(Request request) {
        requestDAO.addRequest(request);
    }

    @Override
    public void deleteRequest(Request request) {
        requestDAO.deleteRequest(request);
    }

    @Override
    public boolean hasObjectRequestWithType(int objectId, String typeRequest) {
        if (requestDAO.getListRequestsWithTypeByObjectId(objectId,typeRequest).size()!=0) {
            return true;
        } else {
            return false;
        }
    }
}
