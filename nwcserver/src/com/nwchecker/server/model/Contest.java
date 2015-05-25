package com.nwchecker.server.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.json.ContestView;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h1>Contest Entity</h1> Entity that represents some Contest in DB.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@Entity
@Table(name = "contest")
public class Contest {

	public static enum Status {
		ARCHIVE, PREPARING, RELEASE, GOING;

		@Override
		public String toString() {
			switch (this) {
			case ARCHIVE:
				return "ARCHIVE";
			case PREPARING:
				return "PREPARING";
			case RELEASE:
				return "RELEASE";
			case GOING:
				return "GOING";
			default:
				throw new IllegalArgumentException();
			}
		}

		public static Status stringToStatus(String status) {
			if (Status.ARCHIVE.toString().equals(status)){
				return Status.ARCHIVE;
			}
			if (Status.PREPARING.toString().equals(status)) {
				return Status.PREPARING;
			}
			if (Status.RELEASE.toString().equals(status)) {
				return Status.RELEASE;
			}
			if (Status.GOING.toString().equals(status)) {
				return Status.GOING;
			}
			return null;
		}
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	@JsonView(ContestView.ContestList.class)
	private int id;

	@Column(name = "title")
	@Pattern(regexp = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ,.'()-]{0,}")
	@NotEmpty
	@Size(max = 100)
	@JsonProperty("title")
	@JsonView(ContestView.ContestList.class)
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	@NotEmpty
	private String description;

	@Column(name = "starts")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonProperty("starts")
	@JsonView(ContestView.ContestList.class)
	private Date starts;

	@Column(name = "duration")
	@DateTimeFormat(pattern = "HH:mm")
	private Date duration;

	@OneToMany(mappedBy = "contest", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Task> tasks;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "contest_users", joinColumns = { @JoinColumn(name = "contest_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@JsonView(ContestView.ContestList.class)
	private List<User> users;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	@JsonProperty("status")
	@JsonView(ContestView.ContestList.class)
	private Status status;

	@Column(name = "hidden")
	private boolean hidden;

	@ManyToOne(fetch = FetchType.LAZY)
	private TypeContest typeContest;

	public Contest() {
		this.title = "";
		this.description = "";
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public TypeContest getTypeContest() {
		return typeContest;
	}

	public void setTypeContest(TypeContest typeContest) {
		this.typeContest = typeContest;
	}

	public void setTypeId(int typeId) {
		this.typeContest = new TypeContest();
		typeContest.setId(typeId);
	}

	@Override
	public boolean equals(Object c) {
		return (c != null && c instanceof Contest && ((Contest) c).getId() == this.id);
	}

}
