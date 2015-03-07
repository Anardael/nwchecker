package com.nwchecker.server.json;

import com.nwchecker.server.model.Role;

/**
 * Created by Станіслав on 07.03.2015.
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
