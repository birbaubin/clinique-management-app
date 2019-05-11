package sample.DAO;

import sample.Models.Payment;

import java.util.ArrayList;

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
        Access.delete("payments", id);
    }


    public static ArrayList getAll()
    {
        return Access.getAll("payments");
    }

}
