package sample.dao.access;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {


        /*Cotisation cota = new Cotisation();
        cota.setAmount(1007);
        cota.setDescription("Payer un gamous");
        cota.setTimeLimit("2019-06-06");
        CotisationAccess.store(cota);*/

        HashMap<String, String > pattern  = new HashMap<>();
        pattern.put("member_id", "18");
        pattern.put("text", "Hello bro");
        NotificationAccess.search(pattern);

       // EventAccess.delete(2);

    }

}
