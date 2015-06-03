package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.json.JsonViews;

/**
 * <h1>Task Pass Entity</h1> Entity that represents and encapsulate one User's
 * Task submit in some Contest.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-11
 */
@Entity
@Table(name = "TaskPass")
public class TaskPass {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne()
	@JoinColumn(name = "contestPass")
	private ContestPass contestPass;

	@ManyToOne()
	@JoinColumn(name = "task_id")
	private Task task;

	@Column(name = "passed")
	@JsonProperty("passed")
	@JsonView(JsonViews.TaskPassView.class)
	private boolean passed;

	@Column(name = "executionTime")
	@JsonProperty("executionTime")
	@JsonView(JsonViews.TaskPassView.class)
	private int executionTime;

	@Column(name = "memoryUsed")
	@JsonProperty("memoryUsed")
	@JsonView(JsonViews.TaskPassView.class)
	private int memoryUsed;

	@Lob
	@Column(name = "file")
	private byte[] file;

	@Column(name = "compilerMessage")
	private String compilerMessage;

	@Column(name = "passedMinute")
	private int passedMinute;

	@ManyToOne()
	@JoinColumn(name = "userid")
	@JsonView(JsonViews.TaskPassView.class)	
	private User user;
	
	@ManyToOne()
	@JoinColumn(name ="compiler")
	@JsonView(JsonViews.TaskPassView.class)
	private Compiler compiler;
	
//	@JsonIgnore
//	@Column(name = "successfulResult")
//	private String result;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContestPass getContestPass() {
		return contestPass;
	}

	public void setContestPass(ContestPass contestPass) {
		this.contestPass = contestPass;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getCompilerMessage() {
		return compilerMessage;
	}

	public void setCompilerMessage(String compilerMessage) {
		this.compilerMessage = compilerMessage;
	}

	public int getPassedMinute() {
		return passedMinute;
	}

	public void setPassedMinute(int passedMinute) {
		this.passedMinute = passedMinute;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof TaskPass && ((Contest) obj)
				.getId() == this.id);
	}

	public Compiler getCompiler() {
		return compiler;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}	
	
}
