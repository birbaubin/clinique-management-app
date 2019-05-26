package dao.access;

import dao.models.Model;
import dao.database.AppConnection;

import java.sql.*;
import java.util.*;

public class Access {

    private static Connection con = AppConnection.getConnection();
    private static String request;


    public static void store(Model model)
    {
        //if we want to add a user...
        String request = "insert into "+model.getTable()+" (";

        //loop through the args and add them to the request

        System.out.println(model.getArgs());
        int nbreArgs = model.getArgs().length;
        for(int i = 0; i<nbreArgs-1; i++)
        {
            request+=model.getArgs()[i]+", ";
        }
        request+=model.getArgs()[model.getArgs().length-1]+") values (";



        //loop through the attributes and add them to the request

        int nbreAttributes = model.getAttributes().length;
        Object attribute = null;
        for(int i = 0; i < nbreAttributes-1; i++)
        {
            attribute = model.getAttributes()[i];
            if(attribute !=null)
                attribute = "'"+attribute+"'";
            request+= attribute+", ";
        }
        attribute = model.getAttributes()[nbreAttributes-1];
        if(attribute!=null)
            attribute = "'"+attribute+"'";
        request+=attribute+");";

        System.out.println(request);
       try{

            PreparedStatement st = con.prepareStatement(request);
            st.execute();
            System.out.println("Data stored");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void update(Model model, int id)
    {
            request = "update "+model.getTable()+" set ";

            //loop through args and attributes and add them to the request

            int nbreArgs = model.getArgs().length;
            for(int i = 0; i < nbreArgs-1; i++) {

                request+=model.getArgs()[i]+" = '"+model.getAttributes()[i]+"', ";
            }
            request+=model.getArgs()[nbreArgs-1]+" = '"+model.getAttributes()[nbreArgs-1]+"' where id = "+id+";";
           System.out.println(request);
        //" where id = "+id+";"
            try{
                PreparedStatement st = con.prepareStatement(request);
                st.execute();
                System.out.println("Data updated");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

    }

    public static void delete(String  model, int id)
    {
        request = "delete from "+model+" where id = "+id+";";
        System.out.println(request);
        try{
            PreparedStatement st = con.prepareStatement(request);
            st.execute();
            System.out.println("Data deleted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList getAll(String model) {

        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        request  = "select * from "+model+";";
        if(model.equals("users"))
        {
            request = "select * from "+model+" where not userType = 'admin'";
        }
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
        return result;
    }

    public static ArrayList search(HashMap<String, String> pattern, String model)
    {
        ArrayList result = new ArrayList();
        request = "select * from "+model+" where ";
        int counter = 0;
        for(String key:pattern.keySet())
        {
            if(counter>0)
                request+= " and "+key+" = '"+pattern.get(key)+"'";
            else
                request+= key+" = '"+pattern.get(key)+"'";
            counter++;
        }
        //System.out.println(request);

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
            //System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

}
