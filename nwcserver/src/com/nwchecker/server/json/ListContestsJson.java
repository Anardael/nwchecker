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
    private List<String> users;
    private Date starts;
    private Contest.Status status;

    private ListContestsJson() {
    }

    public static ListContestsJson createListContestsJson(Contest contest) {
        ListContestsJson json = new ListContestsJson();
        json.id = contest.getId();
        json.title = contest.getTitle();
        json.users = new LinkedList<>();
        for (User user : contest.getUsers()) {
            json.users.add(user.getDisplayName());
        }
        json.starts = contest.getStarts();
        json.status = contest.getStatus();
        return json;
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

    public Contest.Status getStatus() {
        return status;
    }

    public void setStatus(Contest.Status status) {
        this.status = status;
    }
}
