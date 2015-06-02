package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;

import com.nwchecker.server.utils.ContestStartTimeComparator;
import com.nwchecker.server.utils.PaginationWrapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

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
    public boolean checkIfUserHaveAccessToContest(String username, int contestId) {
        User teacher = userService.getUserByUsername(username);
        if ((teacher.getContest() != null) && (teacher.getContest().size() > 0)) {
            for (Contest c : teacher.getContest()) {
                if (c.getId() == contestId && c.getStatus().equals(Contest.Status.PREPARING)) {
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

    @Override
    public List<Contest> getContestsByPrincipal(Principal principal) {
        if (principal == null || !userDAO.getUserByUsername(principal.getName()).hasRole("ROLE_TEACHER")){
            return contestDAO.getUnhiddenContests();
        } else {
            return contestDAO.getContests();
        }
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

    @Override
    public Long getContestEndTime(Contest contest){
        Calendar endDate = Calendar.getInstance();
        Calendar duration = Calendar.getInstance();
        endDate.setTime(contest.getStarts());
        duration.setTime(contest.getDuration());
        endDate.add(Calendar.HOUR, duration.get(Calendar.HOUR));
        endDate.add(Calendar.MINUTE, duration.get(Calendar.MINUTE));
        endDate.add(Calendar.SECOND, duration.get(Calendar.SECOND));
        long gtmMillis = endDate.getTimeInMillis() - endDate.getTimeZone().getRawOffset();
        return gtmMillis;
    }

    @Override
    public Long getEntryCountForRating(){
        Long count = contestDAO.getEntryCountForRating();
        return count+1;
    }
	@Override
	public Contest getNearestContest() {

		Contest contest = contestDAO.getNearestContest();
		return contest;
	}

	@Override
	public Contest getLastArchivedContest() {

	return contestDAO.getLastArchivedContest();
	}


}
