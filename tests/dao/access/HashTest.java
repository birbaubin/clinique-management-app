package dao.access;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @Test
    void testGetSecurePassword() {

        String myPassword = "password";

        String firstHash = Hash.getSecurePassword(myPassword);
        String secureHash = Hash.getSecurePassword(myPassword);


        assertTrue(firstHash.equals(secureHash));
    }
}