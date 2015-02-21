package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Роман on 21.02.2015.
 */

@Entity
@Table(name = "ContestPass")
public class ContestPass {

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contestPass")
    private List<TaskPass> taskPassList;

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

    public void setTaskPassList(List<TaskPass> taskPassList) {
        this.taskPassList = taskPassList;
    }

    public boolean isPassing() {
        return passing;
    }

    public void setPassing(boolean passing) {
        this.passing = passing;
    }
}
