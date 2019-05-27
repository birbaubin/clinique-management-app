package controllers;

import dao.models.Event;
import dao.models.User;

public class Validator {

    public static void validateForAddUser(User user) throws Exception {
        if(user.getFirstname().equals(""))
            throw new Exception("Veuillez entrer un prénom");

        if(user.getLastname().equals(""))
            throw new Exception("Veuillez entrer un nom");

        if(user.getCne().equals(""))
            throw new Exception("Veuillez entrer le numéro de CNE");

        if(user.getEmail().equals(""))
            throw new Exception("Veuillez entrer un email");

        if(user.getPassword().equals(""))
            throw new Exception("Veuillez entrer un mot de passe");

        if(user.getLevel().equals(""))
            throw new Exception("Veuillez entrer le niveau d\'études");

    }

    public static void validateForAddEvent(Event event) throws Exception {
        if (event.getDate().equals(""))
            throw new Exception("Veuillez entrer une date");

        if (event.getName().equals(""))
            throw new Exception("Veuillez entrer un nom");

        if (event.getDescription().equals(""))
            throw new Exception("Veuillez entrer une description");
    }


}
