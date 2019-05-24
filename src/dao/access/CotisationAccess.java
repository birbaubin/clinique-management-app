package dao.access;

import dao.models.Cotisation;

import java.util.ArrayList;
import java.util.HashMap;


public class CotisationAccess  {

    public static void store(Cotisation cotisation)
    {
        Access.store(cotisation);
    }

    public static void update(Cotisation cotisation, int id){

        Access.update(cotisation, id);
    }

    public  static void delete(int id)
    {
        Access.delete(new Cotisation().getTable(), id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll(new Cotisation().getTable());
    }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Cotisation().getTable());}
}
