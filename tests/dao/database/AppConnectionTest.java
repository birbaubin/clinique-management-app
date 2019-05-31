package dao.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class AppConnectionTest {

    @Test
    void testGetConnection() {

        Connection firstAppConnection = AppConnection.getConnection();
        Connection secondAppConnection = AppConnection.getConnection();

        assertTrue(firstAppConnection == secondAppConnection);

    }
}