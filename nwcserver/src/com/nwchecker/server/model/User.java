package com.nwchecker.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "users")
public class User {

    // User id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @Expose
    private int userId;
    // User name
    @Column(name = "username")
    @Expose
    private String username;
    // Password
    @Column(name = "password")
    private String password;
    @Transient
    private String confirmPassword;
    // Display name
    @Column(name = "display_name")
    @Expose
    private String displayName;
    // User email
    @Column(name = "email")
    @Expose
    private String email;
    // Some university user data(probably faculty or group)
    @Column(name = "info")
    @Expose
    private String info;
    // User role (User,Teacher or Admin).
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Expose
    private Set<Role> roles;
    // Department for teacher users.
    @Column(name = "department")
    @Expose
    private String department;
    // Ban Time - if exists 0 time while user will be inactive
    @Column(name = "ban_time")
    private long banTime;
    // Enabled user
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "contest_users",
            joinColumns = {
                @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "contest_id")})
    private List<Contest> contest;

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRequest> requests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<TaskPass> taskPass;

    public User() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
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

    public boolean hasRole(String role) {
    	return (getRoleObject(role) != null);
    }
    
    public Role getRoleObject(String role) {
    	Role[] rolesArray = roles.toArray(new Role[roles.size()]);
    	for (int i = 0; i < rolesArray.length; i++) {
    		if (rolesArray[i].getRole().equals(role)) {
    			return rolesArray[i];
    		}
    	}
    	return null;
    }
    
    public void addRoleAdmin() {
        if (roles == null) {
            roles = new HashSet<Role>();
            roles.add(new Role(this, "ROLE_ADMIN"));
        } else {
            roles.add(new Role(this, "ROLE_ADMIN"));
        }
    }

    public void addRoleUser() {
        if (roles == null) {
            roles = new HashSet<Role>();
            roles.add(new Role(this, "ROLE_USER"));
        } else {
            roles.add(new Role(this, "ROLE_USER"));
        }
    }

    public void addRoleTeacher() {
        if (roles == null) {
            roles = new HashSet<Role>();
            roles.add(new Role(this, "ROLE_TEACHER"));
        } else {
            roles.add(new Role(this, "ROLE_TEACHER"));
        }
    }

    public List<Contest> getContest() {
        return contest;
    }

    public void setContest(List<Contest> contest) {
        this.contest = contest;
    }

    public Set<UserRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<UserRequest> requests) {
        this.requests = requests;
    }

    public List<TaskPass> getTaskPass() {
        return taskPass;
    }

    public void setTaskPass(List<TaskPass> taskPass) {
        this.taskPass = taskPass;
    }

    @Override
    public boolean equals(Object u) {
        return (u != null && u instanceof User && ((User) u).getUserId() == this.userId);
    }

}
