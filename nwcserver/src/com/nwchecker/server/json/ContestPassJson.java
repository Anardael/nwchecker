package com.nwchecker.server.json;

import com.nwchecker.server.model.ContestPass;

/**
 * Created by Станіслав on 01.03.2015.
 */
public class ContestPassJson {

    private String username;
    private String displayName;
    private int tasksCount;
    private int passedCount;
    private int timePenalty;

    private ContestPassJson() {
    }

    public static ContestPassJson createContestPassJson(ContestPass contestPass) {
        ContestPassJson contestPassJson = new ContestPassJson();
        contestPassJson.username = contestPass.getUser().getUsername();
        contestPassJson.displayName = contestPass.getUser().getDisplayName();
        contestPassJson.tasksCount = contestPass.getContest().getTasks().size();
        contestPassJson.passedCount = contestPass.getPassedCount();
        contestPassJson.timePenalty = contestPass.getTimePenalty();
        return contestPassJson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(int tasksCount) {
        this.tasksCount = tasksCount;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(int timePenalty) {
        this.timePenalty = timePenalty;
    }
}
