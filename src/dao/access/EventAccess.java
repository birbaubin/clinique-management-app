package dao.access;

import dao.models.Event;

import java.util.ArrayList;
import java.util.HashMap;

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
            Access.delete(new Event().getTable(), id);
        }


        public static ArrayList getAll()
        {
            return Access.getAll(new Event().getTable());
        }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Event().getTable());}
    }

