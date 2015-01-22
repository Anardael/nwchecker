package com.nwchecker.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.addRoleUser();
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUserByName(String username) {
        User user = userDAO.getUserByUsername(username);
        userDAO.deleteUser(user);
    }
    
    @Override
	public void deleteUserRoles(User user) {
		List<Role> roles = userDAO.getUserRoles(user);
		userDAO.deleteRoles(roles);
	}
    
    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
    
    @Override
    public List<User> getUsersByRole(String role) {
        return userDAO.getUsersByRole(role);
    }

    @Override
    public boolean hasUsername(String username) {
        return userDAO.hasUsername(username);
    }

    @Override
    public boolean hasEmail(String email) {
        return userDAO.hasEmail(email);
    }
}
