package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <h1>Task Theory Link Entity</h1>
 * Entity that represents theory link of some Task in DB.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@Entity
@Table(name = "TaskTheoryLink")
public class TaskTheoryLink {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    private Task task;

    @Column(name = "link")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task t) {
        this.task = t;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TaskTheoryLink() {
    }

    public TaskTheoryLink(String link) {
        this.link = link;
    }

}
