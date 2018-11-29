package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taskTestResult")
public class TaskTestResult {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Column(name = "executionTime")
	private int executionTime;
	
	@Column(name = "memoryUsed")
	private int memoryUsed;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "compilerMessage")
	private String compilerMessage;

	@ManyToOne
	@JoinColumn(name="taskPassId")
	private TaskPass taskPass;

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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TaskPass getTaskPass() {
		return taskPass;
	}

	public void setTaskPass(TaskPass taskPass) {
		this.taskPass = taskPass;
	}

}
