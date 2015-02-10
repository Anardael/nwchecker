/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.json;

/**
 *
 * @author Роман
 */
public class UserJson {

    private String id;
    private String name;
    private String department;
    private boolean choosed;

    public UserJson(int id, String name, String department, boolean choosed) {
        this.id = String.valueOf(id);
        this.name = name;
        this.department = department;
        this.choosed = choosed;
    }

    public UserJson() {
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

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

}
