import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach.
    public void setUp() {
        login = new Login("Kyle", "Nair");
    }

    // ==========================================
    // 1. Username Unit Tests
    // ==========================================

    @Test
    public void testCheckUserName_CorrectlyFormatted() {
        // Test Data: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_IncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!!"
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testRegisterUser_IncorrectUsernameMessaging() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expected, actual);
    }

    // ==========================================
    // 2. Password Complexity Unit Tests
    // ==========================================

    @Test
    public void testCheckPasswordComplexity_Success() {
        // Test Data: "Ch&&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Failure() {
        // Test Data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testRegisterUser_IncorrectPasswordMessaging() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals(expected, actual);
    }

    // ==========================================
    // 3. Cell Phone Number Unit Tests
    // ==========================================

    @Test
    public void testCheckCellPhoneNumber_CorrectlyFormatted() {
        // Test Data: "+27838968976"
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_IncorrectlyFormatted() {
        // Test Data: "08966553"
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUser_IncorrectCellMessaging() {
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(expected, actual);
    }

    // ==========================================
    // 4. Login Authentication Unit Tests
    // ==========================================

    @Test
    public void testLoginUser_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "WrongPassword"));
    }

    @Test
    public void testReturnLoginStatus_SuccessMessaging() {
        String expected = "Welcome Kyle, Nair it is great to see you.";
        String actual = login.returnLoginStatus(true);
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatus_FailureMessaging() {
        String expected = "Username or password incorrect, please try again.";
        String actual = login.returnLoginStatus(false);
        assertEquals(expected, actual);
    }
}
