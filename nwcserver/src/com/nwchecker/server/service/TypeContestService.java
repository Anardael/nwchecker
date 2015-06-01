package com.nwchecker.server.service;


import com.nwchecker.server.model.TypeContest;

import java.util.List;

/**
 * <h1>TypeContest Service</h1>
 * Service that can get all type from database.
 * <p>
 *
 * @author Vitalik Kulishenko
 * @version 1.0
 */
public interface TypeContestService {
    /**
     * Return all TypeContest from database.
     * <p>
     *
     * @return TypeContest from database
     */
    List<TypeContest> getAllTypeContest();
}
