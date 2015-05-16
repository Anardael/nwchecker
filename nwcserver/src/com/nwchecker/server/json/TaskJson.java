package com.nwchecker.server.json;

import java.util.List;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskTheoryLink;

public class TaskJson extends Json{
	
	    private String title;

	    private int complexity;

	    private int rate;

	    private String description;

	    private String forumLink;

	    private List<TaskTheoryLink> theoryLinks;
	    public TaskJson(Task task){
	    	this.complexity=task.getComplexity();
	    	this.description=task.getDescription();
	    	this.forumLink=task.getForumLink();
	    	this.title=task.getTitle();
	    	this.rate=task.getRate();
	    };
	    public TaskJson(){};
	    public static TaskJson createTaskJson(Task task){
	    	TaskJson tj = new TaskJson();
	    	tj.complexity=task.getComplexity();
	    	tj.description=task.getDescription();
	    	tj.forumLink=task.getForumLink();
	    	tj.title=task.getTitle();
	    	tj.rate=task.getRate();
	    	return tj;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public int getComplexity() {
	        return complexity;
	    }

	    public void setComplexity(int difficulty) {
	        this.complexity = difficulty;
	    }

	    public int getRate() {
	        return rate;
	    }

	    public void setRate(int maxMar) {
	        this.rate = maxMar;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getForumLink() {
	        return forumLink;
	    }

	    public void setForumLink(String forumLink) {
	        this.forumLink = forumLink;
	    }

	    public List<TaskTheoryLink> getTheoryLinks() {
	        return theoryLinks;
	    }

	    public void setTheoryLinks(List<TaskTheoryLink> theoryLinks) {
	        this.theoryLinks = theoryLinks;
	    }
	}