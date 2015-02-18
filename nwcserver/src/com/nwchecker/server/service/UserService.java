package com.nwchecker.server.service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUserByName(String username);

    public void deleteUserRole(User user, String role);

    public void deleteRequest(User user, UserRequest userRequest);

    public User getUserById(int id);

    public User getUserByUsername(String username);

    public List<User> getUsers();

    public List<User> getUsersByRole(String role);

    public List<User> getUsersWithRequests();

    public boolean hasUsername(String username);

    public boolean hasEmail(String email);

    public void setUserDAO(UserDAO userDAO);

}
