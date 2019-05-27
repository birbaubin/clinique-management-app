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
    //label on the top
    public Label headerLabel;
    public JFXTextField addName;
    public JFXTextArea addDescription;
    public DatePicker addDate;

    public JFXButton okButton;
    public JFXButton cancelButton;
    public TableView<Event> table;
    public ObservableList events;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(table==null)
        {
            table = new TableView<Event>();
            TableColumn<Event, String> name = new TableColumn<>("Nom de l'évènement");
            name.setMinWidth(150);
            TableColumn<Event, String> description = new TableColumn<>("Description");
            description.setMinWidth(230);
            TableColumn<Event, String> date = new TableColumn<>("Date");
            date.setMinWidth(150);


            name.setCellValueFactory(new PropertyValueFactory("name"));
            date.setCellValueFactory(new PropertyValueFactory("date"));
            description.setCellValueFactory(new PropertyValueFactory("description"));


            events = EventAccess.getAll();
            table.setItems(events);
            table.getColumns().addAll(name, description, date);
            container.getChildren().addAll(table);
            AnchorPane.setTopAnchor(table, 40.0);
            AnchorPane.setLeftAnchor(table, 100.0);

            headerLabel.setText("Evènements");
           // headerLabel.setVisible(true);
        }

    }

    public void addEventButtonClicked()
    {
        addName.setVisible(true);
        addDate.setVisible(true);
        addDescription.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        headerLabel.setText("Ajouter un évènement");
        headerLabel.setVisible(true);
    }

    public void updateEventButtonClicked()
    {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        if(selectedEvent==null)
            showDialog("Modification d'un évènement", "Veuillez selectionner un évènement pour le modifier", root);
        else
        {
            addDescription.setVisible(true);
            addDate.setVisible((true));
            addName.setVisible(true);
            okButton.setVisible(true);
            cancelButton.setVisible(true);
            headerLabel.setText("Modifier un évènement");

        }

    }

    public void okButtonClicked() throws Exception
    {
        System.out.println("You are here");
        System.out.println("Value of input:" + headerLabel.getText());
        if(headerLabel.getText().equals("Ajouter un évènement"))
        {
            System.out.println("Hello");
            String name = addName.getText();
            String date = String.valueOf(Date.valueOf(addDate.getValue()));
            String description = addDescription.getText();


            Event event = new Event(name, date, description);
            Validator.validateForAddEvent(event);

            EventAccess.store(event);
            refreshTable();
            StackPane pane = new StackPane();
            root.getChildren().add(pane);
            showDialog("Ajout", "L'évènement a été ajouté avec succès", pane);
            hideAddFields();
            System.out.println(date);
        }

       if(headerLabel.getText().equals(("Modifier un évènement")))
        {
            String name = addName.getText();
            String description = addDescription.getText();
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


    }

    public void hideAddFields()
    {
        addDate.setVisible(false);
        addDescription.setVisible(false);
        addName.setVisible((false));
        okButton.setVisible(false);
        cancelButton.setVisible(false);
        headerLabel.setText("Evènements");
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
            URL url = new File("src/views/cotisations-view.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 980, 700));
            window.show();
        }
        catch (Exception e)
        {

        }
    }

    public void loadMembersView(ActionEvent event)
    {

        try{
            URL url = new File("src/views/members-view.fxml").toURL();
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
