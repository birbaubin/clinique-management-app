package dao.access;

import dao.models.Event;
import helpers.Validator;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EventAccessTest {

    @Test
    void testStore() {


        // creating a new valid event
        Event event = new Event(1, "nameTest", "2019-06-08 00:00:00", "descriptionTest");

        // storing event in database
        EventAccess.store(event);


        //search a event with the properties
        HashMap<String, String> eventProperties = new HashMap<>();

        eventProperties.put("name", event.getName());
        eventProperties.put("description", event.getDescription());

        // result of searsh
        ArrayList<HashMap> result = EventAccess.search(eventProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result match expectation
        assertTrue(result.get(0).get("name").equals(event.getName()));
        assertTrue(result.get(0).get("description").equals(event.getDescription()));

    }

    @Test
    void testUpdate() {


        // creating a new valid event
        Event event = new Event(1, "nameTest", "2019-06-08 00:00:00", "descriptionTest");

        // storing event in database
        EventAccess.store(event);


        //search a event with the properties
        HashMap<String, String> eventProperties = new HashMap<>();

        eventProperties.put("name", event.getName());
        eventProperties.put("description", event.getDescription());

        // result of event'id
        ArrayList<HashMap> result = EventAccess.search(eventProperties);
        event.setId(Integer.parseInt((String) result.get(0).get("id")));

        // updating event
        event.setName("nameTestUpdated");
        event.setDescription("descriptionTestUpdated");
        EventAccess.update(event);


        //search a event with the properties
        HashMap<String, String> updatedEventProperties = new HashMap<>();
        updatedEventProperties.put("id", String.valueOf(event.getId()));

        // result of event'id
        ArrayList<HashMap> resultUpdatedEvent = EventAccess.search(updatedEventProperties);
        resultUpdatedEvent.forEach(System.out::println);
        // testing if event was updated

        assertTrue(resultUpdatedEvent.get(0).get("name").equals("nameTestUpdated"));
        assertTrue(resultUpdatedEvent.get(0).get("description").equals("descriptionTestUpdated"));

    }

    @Test
    void testDelete() {

        // creating a new valid event
        Event event = new Event(1, "nameTest", "2019-06-08 00:00:00", "descriptionTest");

        // storing event in database
        EventAccess.store(event);


        //search a event with the properties
        HashMap<String, String> eventProperties = new HashMap<>();

        eventProperties.put("name", event.getName());
        eventProperties.put("description", event.getDescription());
 
        // result of event'id
        ArrayList<HashMap> result = EventAccess.search(eventProperties);
        event.setId(Integer.parseInt((String) result.get(0).get("id")));

        // deleting event
        EventAccess.delete(event.getId());


        //search for event with the properties
        HashMap<String, String> deletedEventProperties = new HashMap<>();
        deletedEventProperties.put("id", String.valueOf(event.getId()));


        // result of event'id
        ArrayList<HashMap> resultdeletedEvent = EventAccess.search(deletedEventProperties);
        resultdeletedEvent.forEach(System.out::println);

        // testing if event is deleted
        assertTrue(resultdeletedEvent.isEmpty());
    }

    @Test
    void testGetAll() {

        // geting all events twice to make sure that they are the same

        ObservableList<Event> firstListing = EventAccess.getAll();
        ObservableList<Event> secondListing = EventAccess.getAll();

        for (int i = 0; i < firstListing.size(); i++) {

            // testing if properties matches between the two lists

            assertTrue(firstListing.get(i).getName().equals(secondListing.get(i).getName()));
            assertTrue(firstListing.get(i).getDescription().equals(secondListing.get(i).getDescription()));
            assertTrue(firstListing.get(i).getDescription().equals(secondListing.get(i).getDescription()));

        }

    }

    @Test
    void testSearch() {

        // creating a new valid event
        Event event = new Event(1, "nameTest", "2019-06-08 00:00:00", "descriptionTest");

        // storing event in database
        EventAccess.store(event);


        //search a event with the properties
        HashMap<String, String> eventProperties = new HashMap<>();

        eventProperties.put("name", event.getName());
        eventProperties.put("description", event.getDescription());


        // result of searsh
        ArrayList<HashMap> result = EventAccess.search(eventProperties);

        // showing result
        result.forEach(System.out::println);

        // testing if result
        assertTrue(result.get(0).get("name").equals(event.getName()));
        assertTrue(result.get(0).get("description").equals(event.getDescription()));


    }
}