package com.nwchecker.server.service;

import com.nwchecker.server.dao.TypeContestDAO;
import com.nwchecker.server.model.TypeContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TypeContestService")
public class TypeContestServiceImpl implements  TypeContestService {

    @Autowired
    private TypeContestDAO typeContestDAO;

    @Override
    public List<TypeContest> getAllTypeContest() {
        return typeContestDAO.getAllTypes();
    }
}
