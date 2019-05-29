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



    @Override
    public void initialize(URL location, ResourceBundle resources) {


            name.setCellValueFactory(new PropertyValueFactory("name"));
            date.setCellValueFactory(new PropertyValueFactory("date"));
            description.setCellValueFactory(new PropertyValueFactory("description"));

            events = EventAccess.getAll();
            table.setItems(events);

    }

    public void addEventButtonClicked(ActionEvent actionEvent)
    {
        showAddFields();
        action = actionEvent;
    }

    public void showAddFields()
    {
        addName.setVisible(true);
        addDate.setVisible(true);
        addDescription.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    public void updateEventButtonClicked(ActionEvent actionEvent)
    {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        if(selectedEvent==null)
            showDialog("Modification d'un évènement", "Veuillez selectionner un évènement pour le modifier", root);
        else
        {
           showAddFields();
           action = actionEvent;
        }

    }

    public void okButtonClicked()
    {
        if(action.getSource()==addEventButton)
        {
            String name = addName.getText();
            try {

            String date = String.valueOf(Date.valueOf(addDate.getValue()));
            String description = addDescription.getText();


            Event event = new Event(name, date, description);

            Validator.validateForAddEvent(event);

            EventAccess.store(event);

            StackPane pane = new StackPane();
            root.getChildren().add(pane);
            showDialog("Ajout", "L'évènement a été ajouté avec succès", pane);
            hideAddFields();
            }
            catch (NullPointerException e)
            {
                showDialog("Ajout d'un évènement", "veuilez entrer une date valide", root);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                showDialog("Ajout d'un évènement", e.getMessage(), root);
            }

            System.out.println("You are here");
        }

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

                Validator.validateForAddEvent(event);
                EventAccess.update(event);
                refreshTable();
                hideAddFields();
                showDialog("Mise à jour", "Les informations ont été modifiées avec succès", root);
            }
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


    }

    public void hideAddFields()
    {
        addDate.setVisible(false);
        addDescription.setVisible(false);
        addName.setVisible((false));
        okButton.setVisible(false);
        cancelButton.setVisible(false);
    }


    public void deleteEventButtonClicked()
    {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        if(selectedEvent==null)
            showDialog("Suppression d'un évènement", "Veuillez selectionner un évènement pour le supprimer", root);
        else
        {
            EventAccess.delete(selectedEvent.getId());
            refreshTable();
            showDialog("Suppression d'un évènement", "L'évènement a été retiré avec succès", root);
        }

    }

    public void refreshTable()
    {
        ObservableList events = EventAccess.getAll();
        table.setItems(events);
    }

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



}
