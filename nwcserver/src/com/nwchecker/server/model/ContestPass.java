package com.nwchecker.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Роман on 21.02.2015.
 */

@Entity
@Table(name = "ContestPass")
public class ContestPass implements Comparable<ContestPass> {

    public static enum ContestStatus {
        ARCHIVE, GOING
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Contest contest;

    @Enumerated(EnumType.STRING)
    @Column(name = "contestStatus")
    private ContestStatus contestStatus;

    @Column(name = "passing")
    private boolean passing;

    @Column(name = "passed")
    private int passedCount;

    @Column(name = "timePenalty")
    private int timePenalty;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contestPass")
    private List<TaskPass> taskPassList;

    @Column(name = "rank")
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public ContestStatus getContestStatus() {
        return contestStatus;
    }

    public void setContestStatus(ContestStatus contestStatus) {
        this.contestStatus = contestStatus;
    }

    public List<TaskPass> getTaskPassList() {
        return taskPassList;
    }

    public List<TaskPass> getFailedTaskPasses(int taskId) {
        List<TaskPass> result = new LinkedList<>();
        for (TaskPass taskPass : getTaskPassList()) {
            if (taskPass.getTask().getId() == taskId && !taskPass.isPassed()) {
                result.add(taskPass);
            }
        }
        return result;
    }

    public void setTaskPassList(List<TaskPass> taskPassList) {
        this.taskPassList = taskPassList;
    }

    public boolean isPassing() {
        return passing;
    }

    public void setPassing(boolean passing) {
        this.passing = passing;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(int timePenalty) {
        this.timePenalty = timePenalty;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(ContestPass o) {
        if (this.passedCount < o.getPassedCount()) {
            return +1;
        } else if (this.passedCount > o.getPassedCount()) {
            return -1;
        } else {
            //if passed count equals- check penalty time:
            if (this.timePenalty > o.getTimePenalty()) {
                return +1;
            } else if (this.timePenalty < o.getTimePenalty()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
