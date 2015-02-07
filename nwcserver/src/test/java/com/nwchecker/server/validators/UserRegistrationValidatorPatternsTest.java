package test.java.com.nwchecker.server.validators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nwchecker.server.validators.UserRegistrationValidator;

public class UserRegistrationValidatorPatternsTest {

	private String patternUsername;
	private String patternDisplayName;
	private String patternEmail;
	private String patternPassword;
	
	@Before
	public void setUp() {
		UserRegistrationValidator validator = new UserRegistrationValidator();
		patternUsername = validator.getPatternUsername();
		patternDisplayName = validator.getPatternDisplayName();
		patternEmail = validator.getPatternEmail();
		patternPassword = validator.getPatternPassword();
	}
	

	@Test
	public void testUsernameEmpty() {
		assertFalse(("").matches(patternUsername));
	}
	
	@Test
	public void testUsernameLength() {
		assertFalse(("u").matches(patternUsername));
		assertTrue(("us").matches(patternUsername));
		assertTrue(("usernameusernam").matches(patternUsername));
		assertFalse(("usernameusername").matches(patternUsername));
	}
	
	@Test
	public void testUsernameSeparators() {
		assertTrue(("user_name").matches(patternUsername));
		assertTrue(("user-name").matches(patternUsername));
		assertFalse(("_user-name").matches(patternUsername));
		assertFalse(("user name").matches(patternUsername));
	}
	
	@Test
	public void testUsernameCyrillic() {
		assertFalse(("юзернейм").matches(patternUsername));
	}
	
	@Test
	public void testDisplayNameEmpty() {
		assertFalse(("").matches(patternDisplayName));
	}
	
	@Test
	public void testDisplayNameLength() {
		assertFalse(("a").matches(patternDisplayName));
		assertFalse(("A").matches(patternDisplayName));
		assertTrue(("tSs").matches(patternDisplayName));
		assertTrue(("TestTestTestTes").matches(patternDisplayName));
		assertFalse(("TestTestTestTest").matches(patternDisplayName));
	}
	
	@Test
	public void testDisplayNameCyrillic() {
		assertTrue(("Вася").matches(patternDisplayName));
		assertTrue(("пЕтро").matches(patternDisplayName));
	}
	
	@Test
	public void testDisplayNameSeparators() {
		assertTrue(("Test2Вася5").matches(patternDisplayName));
		assertTrue(("Test2_Вася5").matches(patternDisplayName));
		assertTrue(("Test2-Вася5").matches(patternDisplayName));
		assertFalse(("Test2 Вася5").matches(patternDisplayName));
	}
	
	@Test
	public void testEmailEmpty() {
		assertFalse(("").matches(patternEmail));
	}
	
	@Test
	public void testEmailCorrect() {
		assertTrue(("test@test.ts").matches(patternEmail));
		assertTrue(("message9@male.nr").matches(patternEmail));
		assertTrue(("message095@male.nr").matches(patternEmail));
		assertTrue(("message9.test@male.nr").matches(patternEmail));
		assertTrue(("message9.test.ts@male.nr").matches(patternEmail));
	}
	
	@Test
	public void testEmailIncorrect() {
		assertFalse(("_test@test.ts.ts").matches(patternEmail));
		assertFalse((".test@test.ts").matches(patternEmail));
		assertFalse(("1test@test.ts.ts").matches(patternEmail));
		assertFalse(("test.test.ts").matches(patternEmail));
	}
	
	@Test
	public void testPasswordEmpty() {
		assertFalse(("").matches(patternPassword));
	}
	
	@Test
	public void testPasswordLength() {
		assertFalse(("Pass1").matches(patternPassword));
		assertTrue(("Pass12").matches(patternPassword));
		assertFalse(("Pass1Pass1Pass1Pass1Pass1Pass1Pas").matches(patternPassword));
	}
	
	@Test
	public void testPasswordCorrect() {
		assertTrue(("Abc12345").matches(patternPassword));
		assertTrue(("fAnat9").matches(patternPassword));
		assertTrue(("5maTch").matches(patternPassword));
	}
	
	@Test
	public void testPasswordIncorrect() {
		assertFalse(("abc12345").matches(patternPassword));
		assertFalse(("fAnat").matches(patternPassword));
		assertFalse(("5MATCH").matches(patternPassword));
	}
	
	@Test
	public void testPasswordCyrillic() {//BUG
		assertFalse(("ТесТую12345").matches(patternPassword));
		assertFalse(("PassПароль12345").matches(patternPassword));
	}
}
