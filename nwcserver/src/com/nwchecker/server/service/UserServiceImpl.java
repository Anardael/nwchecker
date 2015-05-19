package com.nwchecker.server.service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.json.JsonUtil;
import com.nwchecker.server.json.UserListItemJson;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.utils.PaginationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        user.addRole("ROLE_USER");
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
    public void deleteUserRole(User user, String role) {
    	Role roleObject = user.getRoleObject(role);
    	user.getRoles().remove(roleObject);
    	userDAO.deleteRole(roleObject);
	}

    @Override
    public void deleteRequest(User user, UserRequest userRequest) {
        userDAO.deleteRequest(user,userRequest);
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

	@Override
	public List<User> getUsersWithRequests() {
		return userDAO.getUsersWithRequests();
	}

	@Override
	public List<User> getPagedUsers(int startIndex, int pageSize,
			String sorting, String filter) {
		List<User> pagedUsers;
		if (!(sorting == null) && !(sorting.equals("")) && !(filter == null)
				&& !(filter.equals(""))) {
			pagedUsers = userDAO.getPagedUsersSortedAndFiltered(startIndex, pageSize, sorting,
					filter);
		} else {
			if (!(sorting == null) && !(sorting.equals(""))) {
				pagedUsers = userDAO.getPagedUsersSorted(startIndex, pageSize,
						sorting);
			} else {
				if (!(filter == null) && !(filter.equals(""))) {
					pagedUsers = userDAO.getPagedUsersFiltered(startIndex,
							pageSize, filter);
				} else {
					pagedUsers = userDAO.getPagedUsers(startIndex, pageSize);
				}
			}
		}
		return pagedUsers;
	}

	@Override
	public Long getRecordCount(String filter) {
		return userDAO.getRecordCount(filter);
	}

	@Override
	public PaginationWrapper<UserListItemJson> getUsersForPagination(
			int startIndex, int pageSize, String sorting, String filter) {
		PaginationWrapper<UserListItemJson> response = new PaginationWrapper<UserListItemJson>();
		List<User> userList = getPagedUsers(startIndex, pageSize, sorting,
				filter);
		List<UserListItemJson> jsonList = JsonUtil.createJsonList(
				UserListItemJson.class, userList);
		response.setDataList(jsonList);
		response.setRecordCount(getRecordCount(filter));
		return response;
	}
}
