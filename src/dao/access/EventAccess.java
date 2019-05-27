package dao.access;

import dao.models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class EventAccess{

    public static void store(Event event)
        {
            Access.store(event);
        }

        public static void update(Event event){

            Access.update(event);
        }

        public  static void delete(int id)
        {
            Access.delete(new Event().getTable(), id);
        }


        public static ObservableList getAll()
        {
            ArrayList<HashMap<String, String>> allEvents = Access.getAll(new Event().getTable());
            ObservableList events = FXCollections.observableArrayList();
            for(HashMap event: allEvents) {
                events.add(new Event(Integer.parseInt((String)event.get("id")),
                        (String) event.get("name"),
                        (String) (event.get("date")),
                        (String) event.get("description")));
            }

            return events;
        }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Event().getTable());}
    }

