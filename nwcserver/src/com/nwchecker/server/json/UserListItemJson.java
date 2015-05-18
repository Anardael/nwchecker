package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>User List Item Json</h1> JSON object that contains valuable data of some
 * User object from users list.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-07
 */
public class UserListItemJson extends Json {
	@JsonProperty("username")
	private String username;
	@JsonProperty("displayName")
	private String displayName;	
	@JsonProperty("roles")
	private List<RoleJson> roles;
	@JsonProperty("email")
	private String email;
	@JsonProperty("department")
	private String department;
	@JsonProperty("info")
	private String info;

	public UserListItemJson() {
	}

	public UserListItemJson(User user) {
		this.username = user.getUsername();
		this.displayName = user.getDisplayName();
		this.roles = JsonUtil.createJsonList(RoleJson.class, new LinkedList<Role>(user.getRoles()));
		this.email = user.getEmail();
		this.department = user.getDepartment();
		this.info = user.getInfo();
	}
	/**
	 * 
	 * @deprecated
	 * use JsonUtil instead
	 */
	@Deprecated
	public static UserListItemJson createUserListItemJson(User user) {
		UserListItemJson json = new UserListItemJson();
		json.username = user.getUsername();
		json.displayName = user.getDisplayName();
		json.roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			json.roles.add(RoleJson.createRoleJson(role));
		}
		json.email = user.getEmail();
		json.department = user.getDepartment();
		json.info = user.getInfo();
		return json;
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
}
