package sample.DAO;

import sample.Models.Event;
import sample.Models.User;

import java.util.ArrayList;

public class EventAccess{

    public static void store(Event event)
        {
            Access.store(event);
        }

        public static void update(Event event, int id){

            Access.update(event, id);
        }

        public  static void delete(int id)
        {
            Access.delete("events", id);
        }


        public static ArrayList getAll()
        {
            return Access.getAll("events");
        }
    }

