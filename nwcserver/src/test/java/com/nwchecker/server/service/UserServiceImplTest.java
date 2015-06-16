package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.assertEquals;

/**
 * <h1>UserServiceImpl Test</h1> Test for UserServiceImpl methods.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/forTests/context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	/**
	 * Test of addUser method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseTearDown(value = { "classpath:/forTests/dataset.xml" }, type = DatabaseOperation.DELETE_ALL)
	public void testAddUser() throws Exception {
		User userNew = new User();
		userNew.setUsername("userNew");
		userNew.setDisplayName("UserNew_DisplayName");
		userNew.setEmail("userNew@mail.com");
		userNew.setPassword("User1234");
		userNew.setConfirmPassword("User1234");

		userService.addUser(userNew);
		assertEquals(userService.getUserByUsername("userNew").getUsername(),
				"userNew");
	}

	/**
	 * Test of updatedUser method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testUpdateUser() throws Exception {
		User loadedUser = userService.getUserByUsername("admin");
		loadedUser.setDisplayName("Admin_DisplayName_Updated");
		userService.updateUser(loadedUser);
		User updatedUser = userService.getUserByUsername("admin");
		assertEquals(updatedUser.getDisplayName(), "Admin_DisplayName_Updated");
	}

	/**
	 * Test of deleteUserByName method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testDeleteUserByName() throws Exception {
		assertEquals(userService.hasUsername("user"), true);
		userService.deleteUserByName("user");
		assertEquals(userService.hasUsername("user"), false);
	}

	/**
	 * Test of deleteUserRole method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testDeleteUserRole() throws Exception {
		User user = userService.getUserByUsername("user");
		assertEquals(user.hasRole("ROLE_USER"), true);
		userService.deleteUserRole(user, "ROLE_USER");
		assertEquals(user.hasRole("ROLE_USER"), false);
	}

	/**
	 * Test of getUsersWithRequests method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUsersWithRequests() throws Exception {
		assertEquals(userService.getUsersWithRequests().size(), 1);
	}

	/**
	 * Test of deleteRequest method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testDeleteRequest() throws Exception {
		UserRequest userRequest = userService.getUserByUsername("user")
				.getRequests().iterator().next();
		assertEquals(userService.getUsersWithRequests().size(), 1);
		userService.deleteRequest(userService.getUserByUsername("user"),
				userRequest);
		assertEquals(userService.getUsersWithRequests().size(), 0);
	}

	/**
	 * Test of getUserById method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUserById() throws Exception {
		assertEquals(userService.getUserById(1).getUsername(), "admin");
		assertEquals(userService.getUserById(2).getUsername(), "teacher");
		assertEquals(userService.getUserById(3).getUsername(), "user");
	}

	/**
	 * Test of detUserByUsername method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUserByUsername() throws Exception {
		assertEquals(userService.getUserByUsername("admin").getUsername(),
				"admin");
		assertEquals(userService.getUserByUsername("teacher").getUsername(),
				"teacher");
		assertEquals(userService.getUserByUsername("user").getUsername(),
				"user");
	}

	/**
	 * Test of getUsers method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUsers() throws Exception {
		assertEquals(userService.getUsers().size(), 4);
	}

	/**
	 * Test of getUsersByRole method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUsersByRole() throws Exception {
		assertEquals(userService.getUsersByRole("ROLE_USER").size(), 2);
	}

	/**
	 * Test of hasUsername method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testHasUsername() throws Exception {
		assertEquals(userService.hasUsername("admin"), true);
		assertEquals(userService.hasUsername("Admin"), false);
	}

	/**
	 * Test of hasEmail method, of class UserServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testHasEmail() throws Exception {
		assertEquals(userService.hasEmail("admin@ua.ua"), true);
		assertEquals(userService.hasEmail("admin@mail.com"), false);
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testPagedUsers() {
		assertEquals(3, userService.getPagedUsers(1, 10, null, null, null)
				.size());
		assertEquals(1, userService.getPagedUsers(0, 0, null, null, "user1")
				.size());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetUsersForPagination() {
		assertEquals(1, userService.getRecordCount("user1").intValue());
		assertEquals(2, userService.getRecordCount("user").intValue());
	}
}