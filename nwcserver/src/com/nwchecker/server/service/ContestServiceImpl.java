package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.User;
import com.nwchecker.server.utils.ContestStartTimeComparator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private UserService userService;

    @Autowired
    private ContestDAO contestDAO;

    @Override
    public void addContest(Contest c) {
        contestDAO.addContest(c);
    }

    @Override
    public void updateContest(Contest c) {
        contestDAO.updateContest(c);
    }

    @Override
    public void mergeContest(Contest c) {
        contestDAO.mergeContest(c);
    }

    @Override
    public List<Contest> getContests() {
        return contestDAO.getContests();
    }

    @Override
    public Contest getContestByID(int id) {
        return contestDAO.getContestByID(id);
    }

    @Override
    public boolean checkIfUserHaveAccessToContest(String username, int ContestId) {
        User teacher = userService.getUserByUsername(username);
        if ((teacher.getContest() != null) && (teacher.getContest().size() > 0)) {
            for (Contest c : teacher.getContest()) {
                if (c.getId() == ContestId && c.getStatus().equals(Contest.Status.RELEASE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Contest> getContestByStatus(Contest.Status status) {
        return contestDAO.getContestByStatus(status);
    }

    @Override
    public List<Contest> getContestForRating() {
        return contestDAO.getContestsForRating();
    }

    public List<Contest> getPagedContests(int pageSize, int pageNumber) {
        return contestDAO.getPagedContests(pageSize, (pageNumber-1)*pageSize);
    }
    
    public List<Contest> getPagedContests(Contest.Status status, int pageSize, int pageNumber) {
        return contestDAO.getPagedContests(status, pageSize, (pageNumber-1)*pageSize);
    }
    public Long getPageCount(int pageSize){
    	Long count = contestDAO.getEntryCount();
    	if (count%pageSize==0)
    	return count/pageSize;
    	else return count/pageSize+1;
    }
    
    public Long getPageCount(Contest.Status status, int pageSize){
    	Long count = contestDAO.getEntryCount(status);  	
    	if (count%pageSize==0)
    	return count/pageSize;
    	else return count/pageSize+1;
    }


}
