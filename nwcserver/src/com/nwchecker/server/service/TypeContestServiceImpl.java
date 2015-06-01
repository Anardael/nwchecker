package com.nwchecker.server.service;

import com.nwchecker.server.dao.TypeContestDAO;
import com.nwchecker.server.model.TypeContest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TypeContestService")
public class TypeContestServiceImpl implements  TypeContestService {
    private static final Logger LOG = Logger.getLogger(TypeContestServiceImpl.class);

    @Autowired
    private TypeContestDAO typeContestDAO;

    @Override
    public List<TypeContest> getAllTypeContest() {
        LOG.debug("Start method getAllTypeContest...");
        return typeContestDAO.getAllTypes();
    }
}
