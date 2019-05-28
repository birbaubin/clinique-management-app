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


    public static ObservableList getAll()
    {
        ArrayList<HashMap<String, String>> allCotisations = Access.getAll(new Cotisation().getTable());
        ObservableList cotisations = FXCollections.observableArrayList();
        for(HashMap cotisation: allCotisations) {
            cotisations.add(new Cotisation(Integer.parseInt((String)cotisation.get("id")),
                    Double.valueOf((String)cotisation.get("amount")),
                    (String) (cotisation.get("description")),
                    (String) cotisation.get("timeLimit")));
        }

        return cotisations;
    }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Cotisation().getTable());}
}
