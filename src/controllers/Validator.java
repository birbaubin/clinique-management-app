package controllers;

import dao.models.User;

public class Validator {

    public static void validateForAddUser(User user) throws Exception {
        if(user.getFirstname()==null)
            throw new Exception("Veuillez entrer un prénom");

        if(user.getLastname()==null)
            throw new Exception("Veuillez entrer un nom");

        if(user.getCne()==null)
            throw new Exception("Veuillez entrer le numéro de CNE");

        if(user.getEmail()==null)
            throw new Exception("Veuillez entrer un email");

        if(user.getPassword()==null)
            throw new Exception("Veuillez entrer un mot de passe");

        if(user.getLevel()==null)
            throw new Exception("Veuillez entrer le niveau d\'études");

    }


}
