package com.nwchecker.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	// User id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int		userId;
	// User name
	@Column(name = "username")
	private String	username;
	// Password
	@Column(name = "password")
	private String	password;
	@Transient
	private String	confirmPassword;
	// Display name
	@Column(name = "display_name")
	private String	displayName;
	// User email
	@Column(name = "email")
	private String	email;
	// Some university user data(probably faculty or group)
	@Column(name = "info")
	private String	info;
	// User role (User,Teacher or Admin).
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Role> roles = new HashSet<>();
	// Department for teacher users.
	@Column(name = "department")
	private String	department;
	// Ban Time - if exists 0 time while user will be inactive
	@Column(name = "ban_time")
	private long	banTime;
	// Enabled user
	@Column(name = "enabled")
	private boolean	enabled;

	public User() {
	}

	public void setId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getBanTime() {
		return banTime;
	}

	public void setBanTime(long banTime) {
		this.banTime = banTime;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isNew() {
	    return (this.userId == 0);
	}
	
	public void addRoleAdmin() {
		roles.add(new Role(this,"ROLE_ADMIN"));
	}
	
	public void addRoleUser() {
		roles.add(new Role(this,"ROLE_USER"));
	}
	
	public void addRoleTeacher() {
		roles.add(new Role(this,"ROLE_TEACHER"));
	}
	
}
