package test.java.com.nwchecker.server.validators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.nwchecker.server.validators.RolesDescriptionValidator;

public class RolesDescriptionValidatorPatternTest {

	private String pattern;
	
	@Before
	public void setUp() {
		pattern = new RolesDescriptionValidator().getRolesDescPattern();
	}
	
	@Test
	public void testDataIncorrect() {
		assertFalse(("Incorrect data").matches(pattern));
	}
	
	@Test
	public void testAdmin() {
		assertTrue(("ROLE_ADMIN").matches(pattern));
	}
	
	@Test
	public void testTeacher() {
		assertTrue(("ROLE_TEACHER").matches(pattern));
	}
	
	@Test
	public void testUser() {
		assertTrue(("ROLE_USER").matches(pattern));
	}
	
	@Test
	public void testAdminTeacher() {
		assertTrue(("ROLE_ADMINROLE_TEACHER").matches(pattern));
	}
	
	@Test
	public void testAdminUser() {
		assertTrue(("ROLE_ADMINROLE_USER").matches(pattern));
	}
	
	@Test
	public void testTeacherUser() {
		assertTrue(("ROLE_TEACHERROLE_USER").matches(pattern));
	}
	
	@Test
	public void testAdminTeacherUser() {
		assertTrue(("ROLE_ADMINROLE_TEACHERROLE_USER").matches(pattern));
	}
	
	@Test
	public void testAdminTeacherIncorrect() {
		assertFalse(("ROLE_TEACHERROLE_ADMIN").matches(pattern));
	}
	
	@Test
	public void testAdminUserIncorrect() {
		assertFalse(("ROLE_USERROLE_ADMIN").matches(pattern));
	}
	
	@Test
	public void testTeacherUserIncorrect() {
		assertFalse(("ROLE_USERROLE_TEACHER").matches(pattern));
	}
	
	@Test
	public void testAdminTeacherUserIncorrect() {
		assertFalse(("ROLE_ADMINROLE_USERROLE_TEACHER").matches(pattern));
		assertFalse(("ROLE_TEACHERROLE_ADMINROLE_USER").matches(pattern));
		assertFalse(("ROLE_USERROLE_TEACHERROLE_ADMIN").matches(pattern));
	}
}
