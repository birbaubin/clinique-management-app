package dao.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppConnection {

    private static Connection con = null;

    public static Connection getConnection()
    {
        if(con==null)
        {
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/club-app", "root", "");
            }
            catch(Exception e)
            {
                System.out.println("Error occured");
                e.printStackTrace();
            }

        }
        return con;

    }
}

