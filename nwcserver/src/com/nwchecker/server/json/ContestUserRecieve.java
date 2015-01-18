/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.json;

import java.util.List;

/**
 *
 * @author Роман
 */
public class ContestUserRecieve {

    private int contestId;
    private List<Integer> userIds;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUsersId(List<Integer> usersId) {
        this.userIds = usersId;
    }

}
