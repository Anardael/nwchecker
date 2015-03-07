package com.nwchecker.server.service;

/**
 * <h1>Score Calculation Service</h1>
 * Service that calculate Users score after Contest finished
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-26
 */
public interface ScoreCalculationService {

    /**
     * Calculate Users score.
     * <p>
     *
     * @param ContestId Unique ID of existing Contest
     */
    public void calculateScore(int ContestId);

}
