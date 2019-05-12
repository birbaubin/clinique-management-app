package sample.dao.database;

import java.sql.Connection;


public class Main {

    public static void main(String [] args)
    {
        Connection con = AppConnection.getConnection();
        System.out.println("Connexion established");
    }
}
