/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;
import java.util.List;

/**
 *
 * @author Роман
 */
public interface ContestDAO {

    public void addContest(Contest c);

    public void updateContest(Contest c);

    public List<Contest> getContests();

    public Contest getContestByID(int id);
}
