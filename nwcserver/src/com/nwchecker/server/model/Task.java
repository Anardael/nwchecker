package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Task {

	@Id
	@Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="task_description")
	private String description;
	
	public Task() {
		this.id = 0;
		this.description = "";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
