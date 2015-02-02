package test;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserDAO userDAO;
    private UserServiceImpl userServiceImpl = new UserServiceImpl();
    private List<User> userList = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAO.class);
        userServiceImpl.setUserDAO(userDAO);
        User userAdmin = new User();
        User userTeacher = new User();
        User userUser = new User();
        userAdmin.setUserId(0);
        userAdmin.setUsername("Admin");
        userAdmin.setDisplayName("Admin_DisplayName");
        userAdmin.setEmail("admin@mail.com");
        userAdmin.setPassword("Admin1234");
        userAdmin.setConfirmPassword("Admin1234");
        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(new Role(userAdmin, "ROLE_ADMIN"));
        userAdmin.setRoles(roleAdmin);

        userTeacher.setUserId(1);
        userTeacher.setUsername("Teacher");
        userTeacher.setDisplayName("Teacher_DisplayName");
        userTeacher.setEmail("teacher@mail.com");
        userTeacher.setPassword("Teacher1234");
        userTeacher.setConfirmPassword("Teacher1234");
        Set<Role> roleTeacher = new HashSet<>();
        roleTeacher.add(new Role(userTeacher, "ROLE_TEACHER"));
        roleTeacher.add(new Role(userTeacher, "ROLE_USER"));
        userTeacher.setRoles(roleTeacher);

        userUser.setUserId(2);
        userUser.setUsername("User");
        userUser.setDisplayName("User_DisplayName");
        userUser.setEmail("user@mail.com");
        userUser.setPassword("User1234");
        userUser.setConfirmPassword("User1234");
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(new Role(userUser, "ROLE_USER"));
        userUser.setRoles(roleUser);
        Set<UserRequest> userRequests = new HashSet<>();
        userRequests.add(new UserRequest(userUser, "WANT_ROLE_TEACHER"));
        userUser.setRequests(userRequests);

        userList.add(userAdmin);
        userList.add(userTeacher);
        userList.add(userUser);

    }

    @Test
    public void testAddUser() throws Exception {
        userServiceImpl.addUser(userList.get(0));
        verify((userDAO), times(1)).addUser(userList.get(0));
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userDAO.getUserByUsername("Admin")).thenReturn(userList.get(0));
        User loadedUser = userServiceImpl.getUserByUsername("Admin");
        assertEquals(loadedUser.getDisplayName(), "Admin_DisplayName");
        loadedUser.setDisplayName("Admin_DisplayName_Updated");
        userServiceImpl.updateUser(loadedUser);
        User updatedUser = userServiceImpl.getUserByUsername("Admin");
        assertEquals(updatedUser.getDisplayName(), "Admin_DisplayName_Updated");
    }

    @Test
    public void testDeleteUserByName() throws Exception {
        when(userDAO.getUserByUsername("User")).thenReturn(userList.get(2));
        userServiceImpl.deleteUserByName("User");
        verify((userDAO), times(1)).getUserByUsername("User");
        verify((userDAO), times(1)).deleteUser(userList.get(2));
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testDeleteUserRoles() throws Exception {
        userServiceImpl.deleteUserRoles(userList.get(0));
        verify((userDAO), times(1)).getUserRoles(userList.get(0));
        verify((userDAO), times(1)).deleteRoles(anyList());
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testDeleteRequest() throws Exception {
        UserRequest userRequest = userList.get(2).getRequests().iterator().next();
        userServiceImpl.deleteRequest(userList.get(2), userRequest);
        verify((userDAO), times(1)).deleteRequest(userList.get(2), userRequest);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userDAO.getUserById(0)).thenReturn(userList.get(0));
        when(userDAO.getUserById(1)).thenReturn(userList.get(1));
        when(userDAO.getUserById(2)).thenReturn(userList.get(2));
        assertEquals(userServiceImpl.getUserById(0).getUsername(), "Admin");
        assertEquals(userServiceImpl.getUserById(1).getUsername(), "Teacher");
        assertEquals(userServiceImpl.getUserById(2).getUsername(), "User");
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        when(userDAO.getUserByUsername("Admin")).thenReturn(userList.get(0));
        when(userDAO.getUserByUsername("Teacher")).thenReturn(userList.get(1));
        when(userDAO.getUserByUsername("User")).thenReturn(userList.get(2));
        assertEquals(userServiceImpl.getUserByUsername("Admin").getUsername(), "Admin");
        assertEquals(userServiceImpl.getUserByUsername("Teacher").getUsername(), "Teacher");
        assertEquals(userServiceImpl.getUserByUsername("User").getUsername(), "User");
    }

    @Test
    public void testGetUsers() throws Exception {
        when(userDAO.getUsers()).thenReturn(userList);
        assertEquals(userServiceImpl.getUsers().size(), 3);
    }

    @Test
    public void testGetUsersByRole() throws Exception {
        LinkedList<User> users = (LinkedList<User>) userServiceImpl.getUsersByRole("ROLE_USER");
        verify((userDAO), times(1)).getUsersByRole("ROLE_USER");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testGetUsersWithRequests() throws Exception {
        LinkedList<User> users = (LinkedList<User>) userServiceImpl.getUsersWithRequests();
        verify((userDAO), times(1)).getUsersWithRequests();
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testHasUsername() throws Exception {
        when(userDAO.hasUsername("Admin")).thenReturn(true);
        when(userDAO.hasUsername("Other")).thenReturn(false);
        assertEquals(userServiceImpl.hasUsername("Admin"), true);
        assertEquals(userServiceImpl.hasUsername("Other"), false);
    }

    @Test
    public void testHasEmail() throws Exception {
        when(userDAO.hasEmail("admin@mail.com")).thenReturn(true);
        assertEquals(userServiceImpl.hasEmail("admin@mail.com"), true);
    }
}