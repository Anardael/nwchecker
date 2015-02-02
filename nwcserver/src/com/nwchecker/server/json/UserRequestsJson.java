package com.nwchecker.server.json;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ReaktorDTR on 31.01.2015.
 */
public class UserRequestsJson {
    boolean checked;
    private int userId;
    private String username;
    private String displayName;
    private Set<Role> roles = new HashSet<>();
    private String email;
    private String department;
    private String info;
    private Set<UserRequest> requests = new HashSet<>();

    public UserRequestsJson() {
    }

    public UserRequestsJson(User user) {
        this.checked = false;
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        for (Role role : user.getRoles()) {
            Role roleJson = new Role();
            roleJson.setRoleId(role.getRoleId());
            roleJson.setRole(role.getRole());
            this.roles.add(roleJson);
        }
        this.email = user.getEmail();
        this.department = user.getDepartment();
        this.info = user.getInfo();
        for (UserRequest userRequest : user.getRequests()) {
            UserRequest userRequestJson = new UserRequest();
            userRequestJson.setRequestId(userRequest.getRequestId());
            userRequestJson.setRequest(userRequest.getRequest());
            this.requests.add(userRequestJson);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<UserRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<UserRequest> requests) {
        this.requests = requests;
    }
}
