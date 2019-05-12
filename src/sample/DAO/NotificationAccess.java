package sample.DAO;

import sample.Models.Notification;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationAccess {

    public static void store(Notification notif)
    {
        Access.store(notif);
    }

    public static void update(Notification notif, int id){

        Access.update(notif, id);
    }

    public  static void delete(int id)
    {
        Access.delete(new Notification().getTable(), id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll(new Notification().getTable());
    }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Notification().getTable());}

}
