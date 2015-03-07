package test.java.com.nwchecker.server.validators;

import com.nwchecker.server.validators.UserEditValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <h1>UserEditValidator Pattern Test</h1>
 * Test for UserEditValidator regular expressions.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
public class UserEditValidatorPatternsTest {

	private String patternPassword;
	private String patternDisplayName;
	private String patternEmail;
	private String patternDepartment;
	private String patternInfo;
	
	@Before
	public void setUp() {
		UserEditValidator validator = new UserEditValidator();
		patternPassword = validator.getPatternPassword();
		patternDisplayName = validator.getPatternDisplayName();
		patternEmail = validator.getPatternEmail();
		patternDepartment = validator.getPatternDepartment();
		patternInfo = validator.getPatternInfo();
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
	
	@Test
	public void testDisplayNameEmpty() {
		assertFalse(("").matches(patternDisplayName));
	}
	
	@Test
	public void testDisplayNameLength() {
		assertFalse(("a").matches(patternDisplayName));
		assertFalse(("A").matches(patternDisplayName));
		assertTrue(("tSs").matches(patternDisplayName));
		assertTrue(("TestTestTestTest").matches(patternDisplayName));
		assertFalse(("TestTestTestTestT").matches(patternDisplayName));
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
		assertFalse(("_/test@test.ts.ts").matches(patternEmail));
		assertFalse((".test@test.ts").matches(patternEmail));
		assertFalse(("1_*test@test.ts.ts").matches(patternEmail));
		assertFalse(("test.test.ts").matches(patternEmail));
	}
	
	@Test
	public void testDepartmentEmpty() {
		assertTrue(("").matches(patternDepartment));
	}
	
	@Test
	public void testDepartmentCorrect() {
		assertTrue(("Department test").matches(patternDepartment));
		assertTrue(("Department \"test\"").matches(patternDepartment));
		assertTrue(("Department-test").matches(patternDepartment));
	}
	
	@Test
	public void testDepartmentIncorrect() {
		assertFalse(("Department test.!?").matches(patternDepartment));
		assertFalse(("Department, test").matches(patternDepartment));
	}
	
	@Test
	public void testDepartmentCyrillic() {
		assertTrue(("Це тест").matches(patternDepartment));
		assertTrue(("М'ятний тест").matches(patternDepartment));
	}
	
	@Test
	public void testInfoEmpty() {
		assertTrue(("").matches(patternInfo));
	}
	
	@Test
	public void testInfoCorrect() {
		assertTrue(("Info test, TEST. It is tESt!test? \"652test\"").matches(patternInfo));
	}
	
	@Test
	public void testInfoIncorrect() {
		assertFalse(("Test incorrect -$").matches(patternInfo));
	}
	
	@Test
	public void testInfoCyrillic() {
		assertTrue(("Це такий тест").matches(patternInfo));
	}
}
