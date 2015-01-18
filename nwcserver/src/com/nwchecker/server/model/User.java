package com.nwchecker.server.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    // User id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    // User name
    @Column(name = "username")
    @NotEmpty
    @Size(min = 3)
    private String username;
    // Password
    @Column(name = "password")
    @NotEmpty
    @Size(min = 4)
    private String password;
    // Display name
    @Column(name = "display_name")
    @NotEmpty
    private String displayName;
    // User email
    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;
    // Some university user data(probably faculty or group)
    @Column(name = "info")
    private String info;
    // User access level (User,Teacher or Admin).
    @Column(name = "access_level")
    private String accessLevel;
    // Department for teacher users.
    @Column(name = "department")
    private String department;
    // Ban Time - if exists 0 time while user will be inactive
    @Column(name = "ban_time")
    private long banTime;
    // Enabled user
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(mappedBy = "users")
    private List<Contest> contest;

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
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
        return (this.id == 0);
    }

    public List<Contest> getContest() {
        return contest;
    }

    public void setContest(List<Contest> contest) {
        this.contest = contest;
    }

    @Override
    public boolean equals(Object u) {
        return (u != null && u instanceof User && ((User) u).getId() == this.id);
    }

}
