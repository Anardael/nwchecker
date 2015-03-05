package com.nwchecker.server.json;

/**
 * Created by ReaktorDTR on 04.03.2015.
 */
public class StatusContestJson {
    private String statusContest;
    private boolean isContestHidden;

    public String getStatusContest() {
        return statusContest;
    }

    public void setStatusContest(String statusContest) {
        this.statusContest = statusContest;
    }

    public boolean isContestHidden() {
        return isContestHidden;
    }

    public void setContestHidden(boolean isContestHidden) {
        this.isContestHidden = isContestHidden;
    }
}
