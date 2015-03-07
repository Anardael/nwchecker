package com.nwchecker.server.json;

import com.nwchecker.server.model.Role;

/**
 * <h1>Role Json</h1>
 * JSON object that contains valuable data of some Role object.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-07
 */
public class RoleJson {

    private String role;

    private RoleJson() {
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
