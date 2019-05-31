package helpers;

import com.mysql.jdbc.AssertionFailedException;
import dao.access.CotisationAccess;
import dao.access.EventAccess;
import dao.access.UserAccess;
import dao.models.Cotisation;
import dao.models.Event;
import dao.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testValidateForAddUser() {

        User user = new User(1,"Ahmed","","","","","","");

        try {
            Validator.validateForAddUser(user);
            UserAccess.store(user);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //search a user with the properties 
        HashMap<String, String> userProperties = new HashMap<>();

        userProperties.put("firstname", user.getFirstname());

       ArrayList<User> result  = UserAccess.search(userProperties);

        result.forEach(System.out::println);

        assertTrue(result.isEmpty());

    }

    @Test
    void testValidateForAddEvent() {

        Event event = new Event(1,"ourTestEventName","","");

        try {
            Validator.validateForAddEvent(event);
            EventAccess.store(event);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //search a event with the properties 
        HashMap<String, String> eventProperties = new HashMap<>();

        eventProperties.put("name", event.getName());

        ArrayList<Event> result  = EventAccess.search(eventProperties);

        result.forEach(System.out::println);

        assertTrue(result.isEmpty());


    }

    @Test
    void testValidateForAddCotisation() {

        Cotisation cotisation = new Cotisation(1,1000,"ourTestCotisationDescription","");

        try {
            Validator.validateForAddCotisation(cotisation);
            CotisationAccess.store(cotisation);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //search a cotisation with the properties 
        HashMap<String, String> cotisationProperties = new HashMap<>();

        cotisationProperties.put("description", cotisation.getDescription());

        ArrayList<Cotisation> result  = CotisationAccess.search(cotisationProperties);

        result.forEach(System.out::println);

        assertTrue(result.isEmpty());


    }

    @Test
    void testIsNumeric() {

        String numeric = new String("1234");
        String notNumeric = new String("abcd");

        assertTrue(Validator.isNumeric(numeric));
        assertFalse(Validator.isNumeric(notNumeric));

    }
}