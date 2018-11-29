package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;

import com.nwchecker.server.utils.CheckSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ContestDAO contestDAO;

    @Transactional
    @Override
    public void addContest(Contest c) {
        contestDAO.addContest(c);
    }
    @Transactional
    @Override
    public void updateContest(Contest c) {
        contestDAO.updateContest(c);
    }
    @Transactional
    @Override
    public void mergeContest(Contest c) {
        contestDAO.mergeContest(c);
    }
    @Transactional
    @Override
    public List<Contest> getContests() {
        return contestDAO.getContests();
    }

    @Transactional
    @Override
    public Contest getContestByID(int id) {
        return contestDAO.getContestByID(id);
    }

    @Transactional
    @Override
    public boolean checkIfUserHaveAccessToContest(String username, int contestId) {
        User teacher = userService.getUserByUsername(username);
        if ((teacher.getContest() != null) && (teacher.getContest().size() > 0)) {
            for (Contest c : teacher.getContest()) {
                if (c.getId() == contestId && (c.getStatus().equals(Contest.Status.PREPARING)||(c.getStatus().equals(Contest.Status.ARCHIVE)))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    @Override
    public List<Contest> getContestForRating() {
        return contestDAO.getContestsForRating();
    }

    @Transactional
    @Override
    public List<Contest> getContestsListByHiddenStatusUsername(String stringHidden, String stringStatus, String username) {
        int userId = userDAO.getUserByUsername(username).getUserId();
        boolean isBooleanHidden = CheckSortType.stringIsBoolean(stringHidden);
        boolean isStatus = CheckSortType.stringIsStatus(stringStatus);

        if (!isBooleanHidden && !isStatus){     // hidden: ALL, status: ALL
            return contestDAO.getUnhiddenContestsByUserId(userId);
        }

        boolean hidden = Boolean.parseBoolean(stringHidden);

        if (isBooleanHidden && !isStatus){      // hidden: notALL, status: ALL
            if (hidden){
                return contestDAO.getHiddenContestsByUserId(userId);
            } else {
                return contestDAO.getUnhiddenContests();
            }
        }

        Contest.Status status = Contest.Status.valueOf(stringStatus);

        if (!isBooleanHidden && isStatus) {     // hidden: ALL, status: notALL
            return contestDAO.getUnhiddenContestsByUserIdAndStatus(userId, status);
        }

        if (isBooleanHidden && isStatus) {      // hidden: notALL, status: notALL
            if (hidden){
                return contestDAO.getHiddenContestsByUserIdAndStatus(userId, status);
            } else {
                return contestDAO.getUnhiddenContestsByStatus(status);
            }
        }

        return null;
    }

    @Transactional
    @Override
    public List<Contest> getUnhiddenContestsListByStatus(String stringStatus) {
        // if status notALL
        if (CheckSortType.stringIsStatus(stringStatus)){
            return contestDAO.getUnhiddenContestsByStatus(Contest.Status.valueOf(stringStatus));
        } else {
            return contestDAO.getUnhiddenContests();
        }
    }

    @Transactional
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
    @Transactional
	@Override
	public Contest getNearestContest() {
		return contestDAO.getNearestContest();
	}

    @Transactional
	@Override
	public Contest getLastArchivedContest() {
	    return contestDAO.getLastArchivedContest();
	}

}
