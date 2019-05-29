package controllers;


import com.jfoenix.controls.*;
import dao.access.CotisationAccess;
import dao.access.EventAccess;
import dao.models.Cotisation;
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


public class CotisationsController extends Controller implements Initializable{

    public StackPane root;
    public JFXButton addCotisationButton;
    public JFXButton updateCotisationButton;
    public JFXButton deleteCotisationButton;
    public JFXButton membersViewButton;
    public JFXButton cotisationsViewButton;
    public JFXButton eventsViewButton;
    //label on the top
    public Label headerLabel;
    public JFXTextField addAmount;
    public JFXTextArea addDescription;
    public DatePicker addDate;

    public JFXButton okButton;
    public JFXButton cancelButton;
    public TableView<Cotisation> table;
    private ObservableList cotisations;
    private ActionEvent action;
    public TableColumn amount;
    public TableColumn date;
    public TableColumn description;
    public MenuButton profileButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


            amount.setCellValueFactory(new PropertyValueFactory("amount"));
            date.setCellValueFactory(new PropertyValueFactory("timeLimit"));
            description.setCellValueFactory(new PropertyValueFactory("description"));


            cotisations = CotisationAccess.getAll();
            table.setItems(cotisations);

    }

    public void addCotisationButtonClicked(ActionEvent actionEvent)
    {
        showAddFields();
        action = actionEvent;
    }

    public void updateCotisationButtonClicked(ActionEvent actionEvent)
    {
        Cotisation selectedCotisation = table.getSelectionModel().getSelectedItem();
        if(selectedCotisation==null)
            showDialog("Modification d'une cotisation", "Veuillez selectionner une cotisation pour la modifier", root);
        else
        {
            showAddFields();
            action = actionEvent;
        }

    }

    public void okButtonClicked() throws Exception
    {
        if(action.getSource()==addCotisationButton)
        {
            System.out.println("Hello");
            Double amount = Double.valueOf(addAmount.getText());
            String date = String.valueOf(Date.valueOf(addDate.getValue()));
            String description = addDescription.getText();


            Cotisation cotisation = new Cotisation(amount, description, date);

            CotisationAccess.store(cotisation);
            refreshTable();
            StackPane pane = new StackPane();
            root.getChildren().add(pane);
            showDialog("Ajout d'une cotisation", "La cotisation a été ajoutée avec succès", pane);
            hideAddFields();
            System.out.println(date);
        }

        if(action.getSource()==updateCotisationButton)
        {
            try{
                Double amount = Double.valueOf(addAmount.getText());
                String description = String.valueOf(addDescription.getText());
                String date = String.valueOf(Date.valueOf(addDate.getValue()));
                Cotisation cotisation = table.getSelectionModel().getSelectedItem();
                cotisation.setTimeLimit(date);
                cotisation.setDescription(description);
                cotisation.setAmount(amount);
                Validator.validateForAddCotisation(cotisation);
                CotisationAccess.update(cotisation);
                refreshTable();
                hideAddFields();
                showDialog("Mise à jour", "Les informations ont été modifiées avec succès", root);
            }
            catch (NumberFormatException e)
            {
                showDialog("Modification d'une cotisation", "Veuillez entrer un montant valide", root);
            }
            catch (NullPointerException e)
            {
                showDialog("Modification d'une cotisation", "Veuillez entrer une date valide", root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
               // showDialog("Modification d'une cotisation", e.getMessage(), root);
            }



        }


    }

    public void hideAddFields()
    {
        addDate.setVisible(false);
        addDescription.setVisible(false);
        addAmount.setVisible(false);
        okButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    public void showAddFields()
    {

        addDescription.setVisible(true);
        addDate.setVisible((true));
        addAmount.setVisible(true);
        okButton.setVisible(true);
        cancelButton.setVisible(true);
    }


    public void deleteCotisationButtonClicked()
    {
        Cotisation selectedCotisation = table.getSelectionModel().getSelectedItem();
        if(selectedCotisation==null)
            showDialog("Suppression d'une cotisation", "Veuillez selectionner une cotisation pour la supprimer", root);
        else
        {
            EventAccess.delete(selectedCotisation.getId());
            refreshTable();
            showDialog("Suppression d'une cotisation", "La cotisation a été retirée avec succès", root);
        }

    }

    public void refreshTable()
    {
        ObservableList cotas = CotisationAccess.getAll();
        table.setItems(cotas);
    }

    public void loadEventsView(ActionEvent event)
    {

        try{
            URL url = new File("src/views/events-view.fxml").toURI().toURL();
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

    public void disconnect()
    {

        try{
            URL url = new File("src/views/auth-view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage window = (Stage)(profileButton.getScene().getWindow());
            window.setScene(new Scene(root, 750, 600));

            window.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
