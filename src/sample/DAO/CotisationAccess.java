package sample.DAO;

import sample.Models.Cotisation;

import java.util.ArrayList;


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
        Access.delete("cotisations", id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll("cotisations");
    }
}
