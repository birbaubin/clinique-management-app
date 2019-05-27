package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
    public JFXTextField addDate;

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
            name.setMinWidth(130);
            TableColumn<Event, String> description = new TableColumn<>("Description");
            description.setMinWidth(130);
            TableColumn<Event, String> date = new TableColumn<>("Date");
            date.setMinWidth(130);


            name.setCellValueFactory(new PropertyValueFactory("name"));
            date.setCellValueFactory(new PropertyValueFactory("date"));
            description.setCellValueFactory(new PropertyValueFactory("description"));



            events = EventAccess.getAll();
            table.setItems(events);
            table.getColumns().addAll(name, description, date);
            container.getChildren().addAll(table);
            AnchorPane.setTopAnchor(table, 40.0);
            AnchorPane.setLeftAnchor(table, 40.0);
            headerLabel.setText("Evènements");
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
    }

    public void updateEventButtonClicked()
    {

        addName.setVisible(true);
        addDescription.setVisible(true);
        addDate.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    public void okButtonClicked() throws Exception
    {
        if(headerLabel.getText().equals("Ajouter un évènement"))
        {
            System.out.println("Hello");
            String name = addName.getText();
            String date = addDate.getText();
            String description = addDescription.getText();


            Event event = new Event(name, date, description);
            Validator.validateForAddEvent(event);

            EventAccess.store(event);
            refreshTable();
            StackPane pane = new StackPane();
            root.getChildren().add(pane);
            showDialog("Ajout", "L'évènement a été ajouté avec succès", pane);
        }

        if(headerLabel.getText().equals(("Modifier les informations d'un évènement")))
        {
            String name = addName.getText();
            String description = addDescription.getText();
            String date = addDate.getText();
            Event event = table.getSelectionModel().getSelectedItem();
            event.setDate(date);
            event.setDescription(description);
            event.setName(name);
            Validator.validateForAddEvent(event);
            EventAccess.update(event);
            refreshTable();
            cancelButtonClicked();
            //showDialog("Ajout", "L'utilisateur a été ajouté avec succès");

        }


    }

    public void cancelButtonClicked()
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
        EventAccess.delete(selectedEvent.getId());
        refreshTable();
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

        }
    }



}
