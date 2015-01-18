package com.nwchecker.server.service;

import com.nwchecker.server.model.User;
import java.util.List;

public interface UserService {

    public void addUser(User user);

    public User getUserById(int id);

    public List<User> getUsers();

    public User getUserByUsername(String username);

    public boolean hasUsername(String username);

    public boolean hasEmail(String email);

}
