package controllers;


import com.jfoenix.controls.*;
import dao.access.CotisationAccess;
import dao.models.Cotisation;
import helpers.Validator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

            //assign table columns to user attributes
            amount.setCellValueFactory(new PropertyValueFactory("amount"));
            date.setCellValueFactory(new PropertyValueFactory("timeLimit"));
            description.setCellValueFactory(new PropertyValueFactory("description"));

            //get all cotisations
            cotisations = CotisationAccess.getAll();
            table.setItems(cotisations);


    }

    //runs when add "Ajouter" button clicked
    public void addCotisationButtonClicked(ActionEvent actionEvent)
    {
        showAddFields();
        action = actionEvent;
    }

    //runs when "Modifier" button clicked
    public void updateCotisationButtonClicked(ActionEvent actionEvent)
    {
        Cotisation selectedCotisation = table.getSelectionModel().getSelectedItem();
        if(selectedCotisation==null)
            showDialog("Modification d'une cotisation", "Veuillez selectionner une cotisation pour la modifier", root);
        else
        {
            showAddFields();
            addAmount.setText(String.valueOf(selectedCotisation.getAmount()));
            addDescription.setText(selectedCotisation.getDescription());
            action = actionEvent;
        }

    }


    //runs when "OK" button clicked
    public void okButtonClicked() throws Exception
    {

        //if we want to add
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

        //if we want to update
        if(action.getSource()==updateCotisationButton) {
            try {
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

            //show errors
            catch (NumberFormatException e) {
                showDialog("Modification d'une cotisation", "Veuillez entrer un montant valide", root);
            } catch (NullPointerException e) {
                showDialog("Modification d'une cotisation", "Veuillez entrer une date valide", root);
            } catch (Exception e) {
                e.printStackTrace();
                // showDialog("Modification d'une cotisation", e.getMessage(), root);
            }
        }
    }

    //hide inputs
    public void hideAddFields()
    {
        addDate.setVisible(false);
        addDescription.setVisible(false);
        addAmount.setVisible(false);
        okButton.setVisible(false);
        cancelButton.setVisible(false);
    }
    //show inputs to add or update
    public void showAddFields()
    {
        addDescription.clear();
        addDescription.setVisible(true);
        addDate.setVisible((true));
        addAmount.setVisible(true);
        addAmount.clear();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
    }
    //runs when "Supprimer" button clicked
    public void deleteCotisationButtonClicked()
    {

        Cotisation selectedCotisation = table.getSelectionModel().getSelectedItem(); //get selected user
        if(selectedCotisation==null) //if none
            showDialog("Suppression d'une cotisation", "Veuillez selectionner une cotisation pour la supprimer", root);
        else
        {
            CotisationAccess.delete(selectedCotisation.getId());
            refreshTable();
            showDialog("Suppression d'une cotisation", "La cotisation a été retirée avec succès", root);
        }

    }

    //reload data from db and show in table
    public void refreshTable()
    {
        ObservableList cotas = CotisationAccess.getAll();
        table.setItems(cotas);
    }

    //runs when "Evènements" button clicked
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

    //runs when "Membres" button clicked
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
        disconnect(profileButton);
    }


}
