package com.nwchecker.server.dao;

import com.nwchecker.server.model.ContestPass;

import java.util.List;

/**
 * Created by Роман on 21.02.2015.
 */
public interface ContestPassDAO {

    void saveContestPass(ContestPass contestPass);

    void updateContestPass(ContestPass contestPass);

    List<ContestPass> getContestPasses(int contestId);

}
