package com.nwchecker.server.json;

import com.nwchecker.server.model.ContestPass;

/**
 * <h1>Contest Pass Json</h1>
 * JSON object that contains valuable data of some ContestPass object.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-01
 */
public class ContestPassJson {

    private int rank;
    private String displayName;
    private String tasksPassedCount;
    private int timePenalty;

    private ContestPassJson() {
    }

    public static ContestPassJson createContestPassJson(ContestPass contestPass) {
        ContestPassJson contestPassJson = new ContestPassJson();
        contestPassJson.rank = contestPass.getRank();
        contestPassJson.displayName = contestPass.getUser().getDisplayName();
        contestPassJson.tasksPassedCount = contestPass.getPassedCount()
                                         + "/" + contestPass.getContest().getTasks().size();
        contestPassJson.timePenalty = contestPass.getTimePenalty();
        return contestPassJson;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTasksPassedCount() {
        return tasksPassedCount;
    }

    public void setTasksPassedCount(String tasksPassedCount) {
        this.tasksPassedCount = tasksPassedCount;
    }

    public int getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(int timePenalty) {
        this.timePenalty = timePenalty;
    }
}