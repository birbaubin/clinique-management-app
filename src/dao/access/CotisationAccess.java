package dao.access;

import dao.models.Cotisation;
import dao.models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;


public class CotisationAccess  {

    public static void store(Cotisation cotisation)
    {
        Access.store(cotisation);
    }

    public static void update(Cotisation cotisation){

        Access.update(cotisation);
    }

    public  static void delete(int id)
    {
        Access.delete(new Cotisation().getTable(), id);
    }


    public static ObservableList<Cotisation> getAll()
    {
        ArrayList<HashMap<String, String>> allCotisations = Access.getAll(new Cotisation().getTable());
        ObservableList cotisations = FXCollections.observableArrayList();
        //loop throug arraylist and add to observablelist for table
        for(HashMap cotisation: allCotisations) {
            cotisations.add(new Cotisation(Integer.parseInt((String)cotisation.get("id")),
                    Double.valueOf((String)cotisation.get("amount")),
                    (String) (cotisation.get("description")),
                    new String((String) cotisation.get("timeLimit")).substring(0,10)));
        }

        return cotisations;
    }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Cotisation().getTable());}
}
