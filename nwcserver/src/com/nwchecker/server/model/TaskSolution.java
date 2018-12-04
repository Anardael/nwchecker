package com.nwchecker.server.model;

import javax.persistence.*;

@Entity
@Table(name = "TaskSolution")
public class TaskSolution {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "checked")
    private boolean checked;

    @ManyToOne()
    @JoinColumn(name = "contest")
    private Contest contest;

    @ManyToOne()
    @JoinColumn(name ="compiler")
    private Compiler compiler;

    @ManyToOne()
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "task")
    private Task task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Compiler getCompiler() {
        return compiler;
    }

    public void setCompiler(Compiler compiler) {
        this.compiler = compiler;
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

    @Override
    public boolean equals(Object c) {
        return (c != null && c instanceof TaskSolution && ((TaskSolution) c).getId() == this.id);
    }
}
