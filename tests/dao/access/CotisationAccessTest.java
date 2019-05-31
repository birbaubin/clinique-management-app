package dao.access;

import dao.models.Cotisation;
import helpers.Validator;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CotisationAccessTest {

    @Test
    void testStore() {


        // creating a new valid cotisation
        Cotisation cotisation = new Cotisation(1, 100,  "descriptionTest","2019-06-08 00:00:00");

        // storing cotisation in database
        CotisationAccess.store(cotisation);


        //search a cotisation with the properties
        HashMap<String, String> cotisationProperties = new HashMap<>();

        cotisationProperties.put("amount", String.valueOf(cotisation.getAmount()));
        cotisationProperties.put("description", cotisation.getDescription());

        // result of searsh
        ArrayList<HashMap> result = CotisationAccess.search(cotisationProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result match expectation
        assertTrue(Double.valueOf((String) result.get(0).get("amount")) ==  cotisation.getAmount());
        assertTrue(result.get(0).get("description").equals(cotisation.getDescription()));

    }

    @Test
    void testUpdate() {


        // creating a new valid cotisation
        Cotisation cotisation = new Cotisation(1, 100,  "descriptionTest","2019-06-08 00:00:00");

        // storing cotisation in database
        CotisationAccess.store(cotisation);


        //search a cotisation with the properties
        HashMap<String, String> cotisationProperties = new HashMap<>();

        cotisationProperties.put("amount", String.valueOf(cotisation.getAmount()));
        cotisationProperties.put("description", cotisation.getDescription());

        // result of cotisation'id
        ArrayList<HashMap> result = CotisationAccess.search(cotisationProperties);
        cotisation.setId(Integer.parseInt((String) result.get(0).get("id")));

        // updating cotisation
        cotisation.setAmount(200);
        cotisation.setDescription("descriptionTestUpdated");
        CotisationAccess.update(cotisation);


        //search a cotisation with the properties
        HashMap<String, String> updatedCotisationProperties = new HashMap<>();
        updatedCotisationProperties.put("id", String.valueOf(cotisation.getId()));

        // result of cotisation'id
        ArrayList<HashMap> resultUpdatedCotisation = CotisationAccess.search(updatedCotisationProperties);
        resultUpdatedCotisation.forEach(System.out::println);
        // testing if cotisation was updated

        assertTrue(resultUpdatedCotisation.get(0).get("amount").equals("200"));
        assertTrue(resultUpdatedCotisation.get(0).get("description").equals("descriptionTestUpdated"));

    }

    @Test
    void testDelete() {

        // creating a new valid cotisation
        Cotisation cotisation = new Cotisation(1, 100,  "descriptionTest","2019-06-08 00:00:00");

        // storing cotisation in database
        CotisationAccess.store(cotisation);


        //search a cotisation with the properties
        HashMap<String, String> cotisationProperties = new HashMap<>();

        cotisationProperties.put("amount", String.valueOf(cotisation.getAmount()));
        cotisationProperties.put("description", cotisation.getDescription());

        // result of cotisation'id
        ArrayList<HashMap> result = CotisationAccess.search(cotisationProperties);
        cotisation.setId(Integer.parseInt((String) result.get(0).get("id")));

        // deleting cotisation
        CotisationAccess.delete(cotisation.getId());


        //search for cotisation with the properties
        HashMap<String, String> deletedCotisationProperties = new HashMap<>();
        deletedCotisationProperties.put("id", String.valueOf(cotisation.getId()));


        // result of cotisation'id
        ArrayList<HashMap> resultdeletedCotisation = CotisationAccess.search(deletedCotisationProperties);
        resultdeletedCotisation.forEach(System.out::println);

        // testing if cotisation is deleted
        assertTrue(resultdeletedCotisation.isEmpty());
    }

    @Test
    void testGetAll() {

        // geting all cotisations twice to make sure that they are the same

        ObservableList<Cotisation> firstListing = CotisationAccess.getAll();
        ObservableList<Cotisation> secondListing = CotisationAccess.getAll();

        for (int i = 0; i < firstListing.size(); i++) {

            // testing if properties matches between the two lists

            assertTrue(firstListing.get(i).getAmount() == secondListing.get(i).getAmount());
            assertTrue(firstListing.get(i).getDescription().equals(secondListing.get(i).getDescription()));
            assertTrue(firstListing.get(i).getDescription().equals(secondListing.get(i).getDescription()));

        }

    }

    @Test
    void testSearch() {

        // creating a new valid cotisation
        Cotisation cotisation = new Cotisation(1, 100,  "descriptionTest","2019-06-08 00:00:00");

        // storing cotisation in database
        CotisationAccess.store(cotisation);


        //search a cotisation with the properties
        HashMap<String, String> cotisationProperties = new HashMap<>();

        cotisationProperties.put("amount", String.valueOf(cotisation.getAmount()));
        cotisationProperties.put("description", cotisation.getDescription());


        // result of searsh
        ArrayList<HashMap> result = CotisationAccess.search(cotisationProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result
        System.out.println(result.get(0).get("amount") + ":   :"+cotisation.getAmount());
        assertTrue(Double.valueOf((String) result.get(0).get("amount")) ==  cotisation.getAmount());
        assertTrue(result.get(0).get("description").equals(cotisation.getDescription()));


    }
}