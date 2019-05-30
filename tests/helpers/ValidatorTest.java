package helpers;

import com.mysql.jdbc.AssertionFailedException;
import dao.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testValidateForAddUser() {

        User user = new User(1,"","","","","","","");

    }

    @Test
    void testValidateForAddEvent() {

    }

    @Test
    void testValidateForAddCotisation() {

    }

    @Test
    void testIsNumeric() {

        String numeric = new String("1234");
        String notNumeric = new String("abcd");

        assertTrue(Validator.isNumeric(numeric));
        assertFalse(Validator.isNumeric(notNumeric));

    }
}