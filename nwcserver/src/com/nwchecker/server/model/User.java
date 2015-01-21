package com.nwchecker.server.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    // User id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
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
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Expose
    private Set<Role> roles = new HashSet<Role>();
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

    public void addRoleAdmin() {
        roles.add(new Role(this, "ROLE_ADMIN"));
    }

    public void addRoleUser() {
        roles.add(new Role(this, "ROLE_USER"));
    }

    public void addRoleTeacher() {
        roles.add(new Role(this, "ROLE_TEACHER"));
    }

    public List<Contest> getContest() {
        return contest;
    }

    public void setContest(List<Contest> contest) {
        this.contest = contest;
    }

    @Override
    public boolean equals(Object u) {
        return (u != null && u instanceof User && ((User) u).getUserId() == this.userId);
    }

}
