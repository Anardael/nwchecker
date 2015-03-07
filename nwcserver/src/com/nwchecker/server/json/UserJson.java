package com.nwchecker.server.json;

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
    private String name;
    private String department;
    private boolean chose;

    private UserJson() {
    }

    public static UserJson createUserJson() {
        return new UserJson();
    }

    public static UserJson createUserJson(int id, String name, String department, boolean chose) {
        UserJson json = new UserJson();
        json.id = String.valueOf(id);
        json.name = name;
        json.department = department;
        json.chose = chose;
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

    public boolean isChose() {
        return chose;
    }

    public void setChose(boolean chose) {
        this.chose = chose;
    }

}
