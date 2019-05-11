package sample.DAO;

import sample.Models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {


        //NotificationAccess.delete(3);
        //EventAccess.delete(3);
        Cotisation cota = new Cotisation();
        cota.setAmount(1007);
        cota.setDescription("Payer un gamous");
        cota.setTimeLimit("2019-06-06");
        CotisationAccess.store(cota);

    }

}
