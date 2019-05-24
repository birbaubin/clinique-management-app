package dao.access;

import dao.models.Payment;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentAccess  {

    public static void store(Payment payment)
    {
        Access.store(payment);
    }

    public static void update(Payment payment, int id){

        Access.update(payment, id);
    }

    public  static void delete(int id)
    {
        Access.delete(new Payment().getTable(), id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll(new Payment().getTable());
    }

    public static ArrayList search(HashMap<String, String> pattern){ return Access.search(pattern, new Payment().getTable());}
}
