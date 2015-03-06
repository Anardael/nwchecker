package test.java.com.nwchecker.server.validators;

import com.nwchecker.server.validators.RolesDescriptionValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(("ROLE_ADMIN;").matches(pattern));
    }

    @Test
    public void testTeacher() {
        assertTrue(("ROLE_TEACHER;").matches(pattern));
    }

    @Test
    public void testUser() {
        assertTrue(("ROLE_USER;").matches(pattern));
    }

    @Test
    public void testAdminTeacher() {
        assertTrue(("ROLE_ADMIN;ROLE_TEACHER;").matches(pattern));
    }

    @Test
    public void testAdminUser() {
        assertTrue(("ROLE_ADMIN;ROLE_USER;").matches(pattern));
    }

    @Test
    public void testTeacherUser() {
        assertTrue(("ROLE_TEACHER;ROLE_USER;").matches(pattern));
    }

    @Test
    public void testAdminTeacherUser() {
        assertTrue(("ROLE_ADMIN;ROLE_TEACHER;ROLE_USER;").matches(pattern));
    }

    @Test
    public void testAdminTeacherIncorrect() {
        assertFalse(("ROLE_TEACHER;ROLE_ADMIN;").matches(pattern));
    }

    @Test
    public void testAdminUserIncorrect() {
        assertFalse(("ROLE_USER;ROLE_ADMIN;").matches(pattern));
    }

    @Test
    public void testTeacherUserIncorrect() {
        assertFalse(("ROLE_USER;ROLE_TEACHER;").matches(pattern));
    }

    @Test
    public void testAdminTeacherUserIncorrect() {
        assertFalse(("ROLE_ADMIN;ROLE_USER;ROLE_TEACHER;").matches(pattern));
        assertFalse(("ROLE_TEACHER;ROLE_ADMIN;ROLE_USER;").matches(pattern));
        assertFalse(("ROLE_USER;ROLE_TEACHER;ROLE_ADMIN;").matches(pattern));
    }
}
