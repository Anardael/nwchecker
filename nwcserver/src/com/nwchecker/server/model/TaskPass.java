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

    @Column(name = "passed")
    private boolean passed;

    @Column(name = "executionTime")
    private long executionTime;

    @Column(name = "memoryUsed")
    private long memoryUsed;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "compilerMessage")
    private String compilerMessage;

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

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getCompilerMessage() {
        return compilerMessage;
    }

    public void setCompilerMessage(String compilerMessage) {
        this.compilerMessage = compilerMessage;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof TaskPass && ((Contest) obj).getId() == this.id);
    }
}
