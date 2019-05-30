package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MembersController extends Controller implements Initializable{

public VBox container;
public StackPane root;
public JFXButton okButton;
public JFXButton cancelButton;

public JFXButton addMemberButton;
public JFXButton updateMemberButton;
public JFXButton deleteMemberButton;
public JFXButton membersViewButton;
public JFXButton cotisationsViewButton;
public JFXButton eventsViewButton;
public MenuButton profileButton;

public JFXTextField addFirstname;
public JFXTextField addLastname;
public JFXTextField addCne;
public JFXTextField addEmail;
public JFXPasswordField addPassword;
public JFXPasswordField confirmPassword;
public JFXTextField addLevel;


//table that contains data
public TableView<User> table;
private ObservableList users;
private ActionEvent action;
public TableColumn firstname;
public TableColumn lastname;
public TableColumn email;
public TableColumn cne;
public TableColumn level;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

         //assign attributes to columns of the table
            firstname.setCellValueFactory(new PropertyValueFactory("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            cne.setCellValueFactory(new PropertyValueFactory("cne"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            level.setCellValueFactory(new PropertyValueFactory("level"));

            //get all users and store data in table
            users = UserAccess.getAll();
            table.setItems(users);
    }


    //show input fieds to add or update a member
    public void showAddMembersFields()
    {
        addFirstname.setVisible(true);
        addLastname.setVisible(true);
        addCne.setVisible(true);
        addEmail.setVisible(true);
        addPassword.setVisible(true);
        confirmPassword.setVisible(true);
        addLevel.setVisible(true);
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        addFirstname.clear();
        addLastname.clear();
        addLevel.clear();
        addEmail.clear();
        addCne.clear();
        addPassword.clear();
        confirmPassword.clear();


    }


    //runs when "Ajouter" button is clicked
    public void addMemberButtonClicked(ActionEvent actionEvent)
    {
        showAddMembersFields(); //show inputs

        //tell the app that we are going add. We will use the source of the event to know whether the "Ajouter" or the "Modifier" button have been clicked
        action = actionEvent;
    }


    //runs when validation button "ok" is clicked
    public void okButtonClicked()
    {

        // it is the "Ajouter" button that thas has been clicked
        if(action.getSource()==addMemberButton)
        {
            //get values entered in inputs
            String firstname = String.valueOf(addFirstname.getText());
            String lastname = addLastname.getText();
            String email = addEmail.getText();
            String cne = addCne.getText();
            String password = addPassword.getText();
            String secondPassword = confirmPassword.getText();
            String level = addLevel.getText();

            //check if the two pwds are equal
            if(!password.equals(secondPassword))
                showDialog("Ajout d'un membre", "Les deux mots de passe ne correspondent pas", root);

            else{

                User user = new User(firstname, lastname, "user", cne, email, password, level);
                try{

                    Validator.validateForAddUser(user);  //validate data
                    UserAccess.store(user); //store user in db
                    refreshTable();
                    showDialog("Ajout", "L'utilisateur a été ajouté avec succès", root);
                    cancelButtonClicked(); //hide input fields
                }
                catch (Exception e)
                {
                    showDialog("Ajout d'un membre", e.getMessage(), root);
                }
            }

        }


        //if it is the "Modifier" that has been clicked
        if(action.getSource()==updateMemberButton)
        {

            //get input values
            String firstname = addFirstname.getText();
            String lastname = addLastname.getText();
            String email = addEmail.getText();
            String cne = addCne.getText();
            String level = addLevel.getText();
            String pass = addPassword.getText();
            String confirpass = confirmPassword.getText();

            //check if the two pwds are the same
            if(!pass.equals(confirpass))
                showDialog("Modification des informations d'un membre", "Les deux mots de passe ne correspondent pas", root);
            else{
                User user = table.getSelectionModel().getSelectedItem(); //get selected user
                user.setFirstname(firstname);
                user.setLastname((lastname));
                user.setEmail(email);
                user.setPassword(pass);
                user.setCne(cne);
                user.setLevel(level);
                try{


                    Validator.validateForAddUser(user); //validate
                    UserAccess.update(user);
                    refreshTable();
                    cancelButtonClicked(); //hide inputs
                    showDialog("Modification des informations d'un membre", "Les informations ont été modifiées avec succès", root);  //confirm
                }
                catch (Exception e){
                    showDialog("Modification des informations d'un membre", e.getMessage(), root); //show error
                }

            }

        }

    }


    //runs when "Annuler" button is clicked, it hides inputs
    public void cancelButtonClicked()
    {
        addFirstname.setVisible(false);
        addLastname.setVisible(false);
        addCne.setVisible((false));
        addEmail.setVisible((false));
        addPassword.setVisible((false));
        confirmPassword.setVisible((false));
        addLevel.setVisible((false));
        okButton.setVisible(false);
        cancelButton.setVisible(false);
    }


    //runs when "Modifier" button is clicked
    public void updateMemberButtonClicked(ActionEvent actionEvent) throws Exception
    {
        //get selected user
            User selectedMember = table.getSelectionModel().getSelectedItem();

            //if no user has been selected
            if(selectedMember==null)
            {
                showDialog("Modification", "Veuillez sélectionner un membre pour modifier ses informations", root);
                cancelButtonClicked();
            }

            else{

                //show inputs and make their default values to the old values
                showAddMembersFields();
                addFirstname.setText(selectedMember.getFirstname());
                addLastname.setText(selectedMember.getLastname());
                addEmail.setText(selectedMember.getEmail());
                addCne.setText(selectedMember.getCne());
                addLevel.setText(selectedMember.getLevel());

                // store the action to get the source.
               action = actionEvent;
            }

    }


    //runs whenn "Supprimer" button is clicked
    public void deleteMemberButtonClicked()
    {
        cancelButtonClicked(); //hide fields if they are shown

        User selectedMember = table.getSelectionModel().getSelectedItem(); //get selected user

        //if none selected
        if(selectedMember==null)
            showDialog("Suppression d'un membre", "Veuillez selectionner un membre pour le supprimer", root);
        else
        {
            //update
            UserAccess.delete(selectedMember.getId());
            refreshTable();
            showDialog("Suppression d'un membre", "L'utilisateur a été retiré avec succès", root);
        }

    }


    //relaod data from db and store in table
    public void refreshTable()
    {
        ObservableList users = UserAccess.getAll();
        table.setItems(users);
    }


    //runs when "Cotisations" button clicked
    public void loadCotisationsView(ActionEvent event)
    {

        //load cotisations view
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

    //runs when "Evènements" button clicked
    public void loadEventsView(ActionEvent event)
    {

        //show events view
        try{
            URL url = new File("src/views/events-view.fxml").toURI().toURL();
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



    //runs when "Déconnection" is selected from user profie button
    public void disconnect()
    {
        //load auth view
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
