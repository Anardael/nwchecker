package com.nwchecker.server.utils;

import com.nwchecker.server.model.Contest;

import java.util.Comparator;

/**
 * <h1>Contest Start Time Comparator</h1>
 * Comparator that used for sorting contests
 * with status "ARCHIVE" in start
 * time order.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-04
 */
public class ContestStartTimeComparator implements Comparator<Contest> {

    @Override
    public int compare(Contest contest1, Contest contest2) {
        return (int) (contest1.getStarts().getTime() - contest2.getStarts().getTime());
    }
}
