package sample.DAO;

import sample.Models.Notification;

import java.util.ArrayList;

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
        Access.delete("notifications", id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll("notifications");
    }


}
