package dao.access;

import dao.models.User;
import helpers.Validator;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserAccessTest {

    @Test
    void testStore() {


        // creating a new valid user
        User user = new User(1, "firstnameTest", "lastnameTest", "user", "12345", "firstnameTest.lastnameTest@gmail.com", "password", "2ite");

        // storing user in database
        UserAccess.store(user);


        //search a user with the properties
        HashMap<String, String> userProperties = new HashMap<>();

        userProperties.put("firstname", user.getFirstname());
        userProperties.put("lastname", user.getLastname());
        userProperties.put("cne", user.getCne());

        // result of searsh
        ArrayList<HashMap> result = UserAccess.search(userProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result match expectation
        assertTrue(result.get(0).get("firstname").equals(user.getFirstname()));
        assertTrue(result.get(0).get("lastname").equals(user.getLastname()));
        assertTrue(result.get(0).get("cne").equals(user.getCne()));

    }

    @Test
    void testUpdate() {


        // creating a new valid user
        User user = new User(1, "firstnameTest", "lastnameTest", "user", "12345", "firstnameTest.lastnameTest@gmail.com", "password", "2ite");

        // storing user in database
        UserAccess.store(user);


        //search a user with the properties
        HashMap<String, String> userProperties = new HashMap<>();

        userProperties.put("firstname", user.getFirstname());
        userProperties.put("lastname", user.getLastname());
        userProperties.put("cne", user.getCne());

        // result of user'id
        ArrayList<HashMap> result = UserAccess.search(userProperties);
        user.setId(Integer.parseInt((String) result.get(0).get("id")));

        // updating user
        user.setFirstname("firstnameTestUpdated");
        user.setLastname("lastnameTestUpdated");
        UserAccess.update(user);


        //search a user with the properties
        HashMap<String, String> updatedUserProperties = new HashMap<>();
        updatedUserProperties.put("id", String.valueOf(user.getId()));

        // result of user'id
        ArrayList<HashMap> resultUpdatedUser = UserAccess.search(updatedUserProperties);
        resultUpdatedUser.forEach(System.out::println);
        // testing if user was updated

         assertTrue(resultUpdatedUser.get(0).get("firstname").equals("firstnameTestUpdated"));
         assertTrue(resultUpdatedUser.get(0).get("lastname").equals("lastnameTestUpdated"));

    }

    @Test
    void testDelete() {

        // creating a new valid user
        User user = new User(1, "firstnameTest", "lastnameTest", "user", "12345", "firstnameTest.lastnameTest@gmail.com", "password", "2ite");

        // storing user in database
        UserAccess.store(user);


        //search a user with the properties
        HashMap<String, String> userProperties = new HashMap<>();

        userProperties.put("firstname", user.getFirstname());
        userProperties.put("lastname", user.getLastname());
        userProperties.put("cne", user.getCne());

        // result of user'id
        ArrayList<HashMap> result = UserAccess.search(userProperties);
        user.setId(Integer.parseInt((String) result.get(0).get("id")));

        // deleting user
        UserAccess.delete(user.getId());


        //search for user with the properties
        HashMap<String, String> deletedUserProperties = new HashMap<>();
        deletedUserProperties.put("id", String.valueOf(user.getId()));


        // result of user'id
        ArrayList<HashMap> resultdeletedUser = UserAccess.search(deletedUserProperties);
        resultdeletedUser.forEach(System.out::println);

        // testing if user is deleted
        assertTrue(resultdeletedUser.isEmpty());
    }

    @Test
    void testGetAll() {

        // geting all users twice to make sure that they are the same

        ObservableList<User> firstListing = UserAccess.getAll();
        ObservableList<User> secondListing = UserAccess.getAll();

        for (int i = 0; i < firstListing.size(); i++) {

            // testing if properties matches between the two lists

            assertTrue(firstListing.get(i).getFirstname().equals(secondListing.get(i).getFirstname()));
            assertTrue(firstListing.get(i).getLastname().equals(secondListing.get(i).getLastname()));
            assertTrue(firstListing.get(i).getLastname().equals(secondListing.get(i).getLastname()));

        }

    }

    @Test
    void testSearch() {

        // creating a new valid user
        User user = new User(1, "firstnameTest", "lastnameTest", "user", "12345", "firstnameTest.lastnameTest@gmail.com", "password", "2ite");

        // storing user in database
        UserAccess.store(user);


        //search a user with the properties
        HashMap<String, String> userProperties = new HashMap<>();

        userProperties.put("firstname", user.getFirstname());
        userProperties.put("lastname", user.getLastname());
        userProperties.put("cne", user.getCne());

        // result of searsh
        ArrayList<HashMap> result = UserAccess.search(userProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result
        assertTrue(result.get(0).get("firstname").equals(user.getFirstname()));
        assertTrue(result.get(0).get("lastname").equals(user.getLastname()));
        assertTrue(result.get(0).get("cne").equals(user.getCne()));

    }
}