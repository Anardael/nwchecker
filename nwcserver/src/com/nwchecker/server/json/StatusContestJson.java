package com.nwchecker.server.json;

/**
 * <h1>Status Contest Json</h1>
 * JSON object that encapsulates data about Status of some Contest.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 * @since 2015-03-04
 */
public class StatusContestJson {

    private String statusContest;
    private boolean isContestHidden;

    private StatusContestJson() {
    }

    public static StatusContestJson createStatusContestJson(String status, boolean isHidden) {
        StatusContestJson json = new StatusContestJson();
        json.statusContest = status;
        json.isContestHidden = isHidden;
        return json;
    }

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
