package sample.DAO;

import sample.Models.User;
import sample.Models.Event;
import sample.database.AppConnection;

import java.sql.*;
import java.util.*;

public class Access {

    private static Connection con = AppConnection.getConnection();
    private static String request;


    public static void store(Model model)
    {

        //if we want to add a user...
       if(model instanceof User)
       {
           request = "insert into users (firstname, lastname, email, password, birthday, cne, level) values (?, ?, ?, ?, ?, ?, ?)";
           System.out.println(request);

          try{
               PreparedStatement st = con.prepareStatement(request);
               st.setString(1, ((User) model).getFirstname());
              st.setString(2, ((User) model).getLastname());
              st.setString(3, ((User) model).getEmail());
              st.setString(4, Hash.getSecurePassword(((User) model).getPassword()));
              st.setString(5, ((User) model).getBirthday());
              st.setString(6, ((User) model).getCne());
              st.setString(7, ((User) model).getLevel());
               st.execute();
           }
           catch (Exception e)
           {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }

         System.out.println("Data stored !");

       }

       else{

           //if it is an event...
           if(model instanceof Event)
           {
                request = "insert into events (name, description, date) values (?, ?, ?)";
                System.out.println(request);

               try{
                   PreparedStatement st = con.prepareStatement(request);
                   st.setString(1, ((Event) model).getName());
                   st.setString(2, ((Event) model).getDescription());
                   st.setString(3, ((Event) model).getDate());
                   st.execute();
               }
               catch (Exception e)
               {
                   System.out.println(e.getMessage());
                   e.printStackTrace();
               }
           }
       }
    }

    public static void update(Model model, int id)
    {
        if(model instanceof User)
        {
            request = "update users set firstname = ?, lastname = ?, birthday = ?, email = ?, password = ?, cne = ?, level = ? where id = ?";
            try{
                PreparedStatement st = con.prepareStatement(request);
                st.setString(1, ((User) model).getFirstname());
                st.setString(2, ((User) model).getLastname());
                st.setString(3, ((User) model).getBirthday());
                st.setString(4, ((User) model).getEmail());
                st.setString(5, Hash.getSecurePassword(((User) model).getPassword()));
                st.setString(6, ((User) model).getCne());
                st.setString(7, ((User) model).getLevel());
                st.setInt(8, id);
                st.execute();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void delete(String model, int id)
    {
        if(model =="user")
        {
            System.out.println("You want to delete a user");
            request = "delete from users where id = ?";

            try{
                PreparedStatement st = con.prepareStatement(request);
                st.setInt(1, id);
                st.execute();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static ArrayList getAll(String model) {

        ArrayList result = new ArrayList();
        if (model == "user") {
            request = "select * from users";
            ResultSet resultset = null;

            try {
                Statement st = con.createStatement();
                resultset = st.executeQuery(request);
                ResultSetMetaData rsmd = resultset.getMetaData();
                int columnNumber = resultset.getMetaData().getColumnCount();
                while (resultset.next()) {

                    HashMap<String, String> row = new HashMap<String, String>();
                    for(int i = 1; i<=columnNumber; i++) {
                        row.put(rsmd.getColumnName(i), resultset.getString(i));
                    }
                    result.add(row);
                }
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

}
