package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Role Entity</h1>
 * Entity that represents Role of some User in DB.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	@JsonIgnore
	private int roleId;
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	@JsonProperty("role")
	@Column(name = "role")
	private String role;
	
	public Role() {
	}
	
	public Role(User user, String role) {
		this.user = user;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int id) {
		this.roleId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Role) {
			Role role = (Role) obj;
			return ((this.roleId == role.roleId) 
					&& (this.user.equals(role.user))
					&& (this.role.equals(role.role)));
		}
		return false;
	}
}
