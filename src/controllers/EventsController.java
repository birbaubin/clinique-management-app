package controllers;


import com.jfoenix.controls.*;
import dao.access.EventAccess;
import dao.models.Event;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.access.UserAccess;
import dao.models.User;
import java.sql.Date;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class EventsController extends Controller implements Initializable{

    public AnchorPane container;
    public StackPane root;
    public JFXButton addEventButton;
    public JFXButton updateEventButton;
    public JFXButton deleteEventButton;
    public JFXButton membersViewButton;
    public JFXButton cotisationsViewButton;
    public JFXButton eventsViewButton;
    public JFXTextField addName;
    public JFXTextArea addDescription;
    public DatePicker addDate;
    public TableColumn name;
    public TableColumn date;
    public TableColumn description;
    public JFXButton okButton;
    public JFXButton cancelButton;
    public TableView<Event> table;
    public ObservableList events;
    public ActionEvent action;
    public MenuButton profileButton;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
            //assign table colums to user attributes
            name.setCellValueFactory(new PropertyValueFactory("name"));
            date.setCellValueFactory(new PropertyValueFactory("date"));
            description.setCellValueFactory(new PropertyValueFactory("description"));


            //get all the events
            events = EventAccess.getAll();
            table.setItems(events);

    }


    //runs when "Ajouter" button is clicked
    public void addEventButtonClicked(ActionEvent actionEvent)
    {
        showAddFields();
        action = actionEvent; //get event. See MembersController
    }


    //show inputs
    public void showAddFields()
    {
        addName.setVisible(true);
        addDate.setVisible(true);
        addDescription.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        addName.clear();
        addDescription.clear();
    }


    //runs when "Modifier" button is clicked
    public void updateEventButtonClicked(ActionEvent actionEvent)
    {
        Event selectedEvent = table.getSelectionModel().getSelectedItem(); //get selected event

        if(selectedEvent==null) //if none selected
            showDialog("Modification d'un évènement", "Veuillez selectionner un évènement pour le modifier", root);
        else
        {
           showAddFields();
           addName.setText(selectedEvent.getName());
           addDescription.setText(selectedEvent.getDescription());
           action = actionEvent; //get event. See MembersController
        }
    }


    // runs when "OK" button clicked
    public void okButtonClicked()
    {
        //if we want to add
        if(action.getSource()==addEventButton)
        {
            try {
                String name = addName.getText();
            String date = String.valueOf(Date.valueOf(addDate.getValue()));
            String description = addDescription.getText();


            Event event = new Event(name, date, description);

            Validator.validateForAddEvent(event); //validate

            EventAccess.store(event); //store

            showDialog("Ajout", "L'évènement a été ajouté avec succès", root);
            hideAddFields();
            }
            //show errors
            catch (NullPointerException e)
            {
                showDialog("Ajout d'un évènement", "veuilez entrer une date valide", root);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                showDialog("Ajout d'un évènement", e.getMessage(), root);
            }

        }


        //if we want to update
       if(action.getSource()==updateEventButton)
        {
            String name = addName.getText();
            String description = addDescription.getText();
            try{
                String date = String.valueOf(Date.valueOf(addDate.getValue()));
                Event event = table.getSelectionModel().getSelectedItem();
                event.setDate(date);
                event.setDescription(description);
                event.setName(name);

                Validator.validateForAddEvent(event); //validate
                EventAccess.update(event); //update
                refreshTable();
                hideAddFields();
                showDialog("Mise à jour", "Les informations ont été modifiées avec succès", root);
            }
            //show errors
            catch (NullPointerException e)
            {
                showDialog("Ajout d'un évènement", "veuilez entrer une date valide", root);
            }
            catch (Exception e)
            {
                showDialog("Ajout d'un évènement", e.getMessage(), root);
            }
        }
    }


    //hide inputs
    public void hideAddFields()
    {
        addDate.setVisible(false);
        addDescription.setVisible(false);
        addName.setVisible((false));
        okButton.setVisible(false);
        cancelButton.setVisible(false);
    }


    //runs when "Supprimer" button clicked
    public void deleteEventButtonClicked()
    {
        Event selectedEvent = table.getSelectionModel().getSelectedItem(); //get selected event
        if(selectedEvent==null) // if none
            showDialog("Suppression d'un évènement", "Veuillez selectionner un évènement pour le supprimer", root);
        else
        {
            EventAccess.delete(selectedEvent.getId());
            refreshTable();
            showDialog("Suppression d'un évènement", "L'évènement a été retiré avec succès", root);
        }

    }


    //get data form db and store in table
    public void refreshTable()
    {
        ObservableList events = EventAccess.getAll();
        table.setItems(events);
    }


    //load cotisations view
    public void loadCotisationsView(ActionEvent event)
    {

        try{
            URL url = new File("src/views/cotisations-view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 980, 700));
            window.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //load members view
    public void loadMembersView(ActionEvent event)
    {

        try{
            URL url = new File("src/views/members-view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 980, 700));
            window.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //disconnect
   public void disconnect()
   {
       disconnect(profileButton); // see Controller class
   }
}
