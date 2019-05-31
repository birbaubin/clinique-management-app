package dao.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class AppConnection {

    private static Connection con = null;

    public static Connection getConnection()
    {
        if(con==null)
        {
            try{

                // get db properties
                Properties props = new Properties();
                props.load(new FileInputStream("env.properties"));

                String user = props.getProperty("user");
                String password = props.getProperty("password");
                String dburl = props.getProperty("dburl");

                // connect to database
                con = DriverManager.getConnection(dburl, user, password);

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

