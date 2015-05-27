package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.Role;

/**
 * <h1>Role Json</h1> JSON object that contains valuable data of some Role
 * object.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-07
 */
public class RoleJson extends Json {
	@JsonProperty("Role")
	private String role;

	public RoleJson() {
	}

	public RoleJson(Role role) {
		this.role = role.getRole();
	}

	public static RoleJson createRoleJson(Role role) {
		RoleJson json = new RoleJson();
		json.role = role.getRole();
		return json;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
