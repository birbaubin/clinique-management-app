package helpers;

import dao.models.Cotisation;
import dao.models.Event;
import dao.models.User;

public class Validator {

    public static void validateForAddUser(User user) throws Exception {

        if(user.getFirstname().equals("") || isNumeric(user.getFirstname()))
            throw new Exception("Veuillez entrer un prénom valide");

        if(user.getLastname().equals("") || isNumeric(user.getLastname()))
            throw new Exception("Veuillez entrer un nom valide");

        if(user.getCne().equals("")  || !isNumeric(user.getCne()))
            throw new Exception("Veuillez entrer le numéro de CNE valide");

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

    public static void validateForAddCotisation(Cotisation cotisation) throws Exception
    {
        if(cotisation.getDescription().equals(""))
            throw new Exception("Veuillez entrer une description");

        if(cotisation.getTimeLimit().equals(""))
            throw new Exception("Veuillez entrer une date");
    }

    public static boolean isNumeric(String string)
    {
        try{
            Double number = Double.parseDouble(string);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }




}
