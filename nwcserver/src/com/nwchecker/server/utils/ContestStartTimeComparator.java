package com.nwchecker.server.utils;

import com.nwchecker.server.model.Contest;

import java.util.Comparator;

/**
 * Created by Станіслав on 04.03.2015.
 */
public class ContestStartTimeComparator implements Comparator<Contest> {

    @Override
    public int compare(Contest contest1, Contest contest2) {
        return (int) (contest1.getStarts().getTime() - contest2.getStarts().getTime());
    }
}
