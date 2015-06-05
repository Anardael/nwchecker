package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;

import com.nwchecker.server.utils.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Contest> getUnhiddenContests() {
        return contestDAO.getUnhiddenContests();
    }

    @Override
    public List<Contest> getContestsListByHiddenStatusUsername(String stringHidden, String stringStatus, String username) {
        int userId = userDAO.getUserByUsername(username).getUserId();
        boolean isBooleanHidden = Support.isBoolean(stringHidden);
        boolean isStatus = Support.isStatus(stringStatus);

        if (!isBooleanHidden && !isStatus){     // hidden: ALL, status: ALL
            return contestDAO.getContestsByUserId(userId);
        }

        if (isBooleanHidden && !isStatus){      // hidden: notALL, status: ALL
            boolean hidden = Boolean.parseBoolean(stringHidden);
            if (hidden){
                return contestDAO.getHiddenContestsByUserId(userId);
            } else {
                return contestDAO.getUnhiddenContests();
            }
        }

        Contest.Status status = Contest.Status.valueOf(stringStatus);

        if (!isBooleanHidden && isStatus) {     // hidden: ALL, status: notALL
            return makeSelectionByStatus(contestDAO.getContestsByUserId(userId), status);
        }

        if (isBooleanHidden && isStatus) {      // hidden: notALL, status: notALL
            boolean hidden = Boolean.parseBoolean(stringHidden);
            if (hidden){
                return makeSelectionByStatus(contestDAO.getHiddenContestsByUserId(userId), status);
            } else {
                return makeSelectionByStatus(contestDAO.getUnhiddenContests(), status);
            }
        }

        return null;
    }

    @Override
    public List<Contest> getUnhiddenContestsListByStatus(String stringStatus) {
        // if status notALL
        if (Support.isStatus(stringStatus)){
            return contestDAO.getUnhiddenContestsByStatus(Contest.Status.valueOf(stringStatus));
        } else {
            return contestDAO.getUnhiddenContests();
        }
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
	public Contest getNearestContest() {
		return contestDAO.getNearestContest();
	}

	@Override
	public Contest getLastArchivedContest() {
	    return contestDAO.getLastArchivedContest();
	}

    private static List<Contest> makeSelectionByStatus(List<Contest> contests, Contest.Status status){
        Iterator<Contest> iterator = contests.iterator();

        while(iterator.hasNext()){
            if(iterator.next().getStatus() != status){
                iterator.remove();
            }
        }

        return contests;
    }
}
