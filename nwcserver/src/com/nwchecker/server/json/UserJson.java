package com.nwchecker.server.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nwchecker.server.model.User;

/**
 * <h1>User Json</h1>
 * JSON object that contains valuable data of some User object.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public class UserJson {

    private String id;
    @JsonProperty(value="displayName")
    private String name;
    private String department;
    @JsonProperty(value="chose")
    private boolean chosen;

    private UserJson() {
    }

    public static UserJson createUserJson() {
        return new UserJson();
    }

    public static UserJson createUserJson(User user) {
        UserJson json = new UserJson();
        json.id = user.getUserId().toString();
        json.name = user.getDisplayName();
        json.department = user.getDepartment();
        return json;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}


}