package com.nwchecker.server.model;

import javax.persistence.*;

/**
 * Created by Роман on 11.02.2015.
 */
@Entity
@Table(name = "TaskPass")
public class TaskPass {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "failTimes")
    private int failTimes;

    @Column(name = "passed")
    private boolean passed;

    @Column(name = "timeSpent")
    private long timeSpent;

    @Column(name = "memoryUsed")
    private long memoryUsed;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(int failTimes) {
        this.failTimes = failTimes;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof TaskPass && ((Contest) obj).getId() == this.id);
    }
}
