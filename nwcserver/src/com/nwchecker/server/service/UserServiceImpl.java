package com.nwchecker.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public boolean hasUsername(String username) {
        return userDAO.hasUsername(username);
    }

    @Override
    public boolean hasEmail(String email) {
        return userDAO.hasEmail(email);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
