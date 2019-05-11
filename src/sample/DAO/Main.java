package sample.DAO;

import sample.Models.Event;
import sample.Models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Event event  = new Event();
        event.setName("COnférence de bor");
        event.setDescription("Be there");
        event.setDate("2019-03-03");
        EventAccess.delete(3);

    }

}
