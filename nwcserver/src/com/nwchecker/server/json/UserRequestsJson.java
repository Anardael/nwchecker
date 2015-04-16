package com.nwchecker.server.json;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>User Requests Json</h1>
 * JSON object that contains valuable data of some UserRequest object.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 * @since 2015-01-31
 */
public class UserRequestsJson {
    boolean checked;
    private int userId;
    private String username;
    private String displayName;
    private List<RoleJson> roles;
    private String email;
    private String department;
    private String info;
    private String phone;
    private List<RequestJson> requests;

    private UserRequestsJson() {
    }

    public static UserRequestsJson createUserRequestsJson(User user) {
        UserRequestsJson json = new UserRequestsJson();
        json.checked = false;
        json.userId = user.getUserId();
        json.username = user.getUsername();
        json.displayName = user.getDisplayName();
        json.roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            json.roles.add(RoleJson.createRoleJson(role));
        }
        json.email = user.getEmail();
        json.department = user.getDepartment();
        json.info = user.getInfo();
        json.phone = user.getPhone();
        json.requests = new ArrayList<>();
        for (UserRequest userRequest : user.getRequests()) {
            json.requests.add(RequestJson.createUserRequestsJson(userRequest));
        }
        return json;
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

    public List<RoleJson> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleJson> roles) {
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

    public List<RequestJson> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestJson> requests) {
        this.requests = requests;
    }
    
    public String getPhone() {
		return phone;
	}
    
    public void setPhone(String phone) {
		this.phone = phone;
	}
}
