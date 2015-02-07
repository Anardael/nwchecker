package test.java.com.nwchecker.server.validators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nwchecker.server.validators.UserProfileValidator;

public class UserProfileValidatorPatternsTest {

	private String patternDisplayName;
	private String patternDepartment;
	private String patternInfo;
	
	@Before
	public void setUp() {
		UserProfileValidator validator = new UserProfileValidator();
		patternDisplayName = validator.getPatternDisplayName();
		patternDepartment = validator.getPatternDepartment();
		patternInfo = validator.getPatternInfo();
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
