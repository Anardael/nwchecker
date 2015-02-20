package com.nwchecker.server.json;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ReaktorDTR on 06.02.2015.
 */
public class ListContestsJson {
    private int id;
    private String title;
    private List<String> users = new LinkedList<>();
    private Date starts;

    public ListContestsJson() {
    }

    public ListContestsJson(Contest contest) {
        this.id = contest.getId();
        this.title = contest.getTitle();
        for (User user : contest.getUsers()) {
            if (user.hasRole("ROLE_TEACHER")) {
                this.users.add(user.getDisplayName());
            }
        }
        this.starts = contest.getStarts();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Date getStarts() {
        return starts;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }
}
