package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MembersController extends Controller implements Initializable{

public AnchorPane container;
public StackPane root;
public JFXButton addMemberButton;
public JFXButton updateMemberButton;
public JFXButton deleteMemberButton;
public JFXButton membersViewButton;
public JFXButton cotisationsViewButton;
public JFXButton eventsViewButton;
//label on the top
public Label headerLabel;
public JFXTextField addFirstname;
public JFXTextField addLastname;
public JFXTextField addCne;
public JFXTextField addEmail;
public JFXTextField addPassword;
public JFXTextField confirmPassword;
public JFXTextField addLevel;
public JFXButton okButton;
public JFXButton cancelButton;
public TableView<User> table;
public ObservableList users;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(table==null)
        {
            table = new TableView<User>();
            TableColumn<User, String> firstname = new TableColumn<>("Prénom");
            firstname.setMinWidth(120);
            TableColumn<User, String> lastname = new TableColumn<>("Nom");
            lastname.setMinWidth(120);
            TableColumn<User, String> cne = new TableColumn<>("Cne");
            cne.setMinWidth(120);
            TableColumn<User, String> email = new TableColumn<>("email");
            email.setMinWidth(150);
            TableColumn<User, String> level = new TableColumn<>("Niveau");
            level.setMinWidth(90);

            firstname.setCellValueFactory(new PropertyValueFactory("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            cne.setCellValueFactory(new PropertyValueFactory("cne"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            level.setCellValueFactory(new PropertyValueFactory("level"));


            users = UserAccess.getAll();
            table.setItems(users);
            table.getColumns().addAll(firstname, lastname, cne, email, level);
            table.setEditable(true);
            container.getChildren().addAll(table);
            AnchorPane.setTopAnchor(table, 40.0);
            AnchorPane.setLeftAnchor(table, 40.0);
            headerLabel.setText("Membres");
        }

    }

    public void showAddMembersFields()
    {
        addFirstname.setVisible(true);
        addLastname.setVisible(true);
        addCne.setVisible((true));
        addEmail.setVisible((true));
        addPassword.setVisible((true));
        confirmPassword.setVisible((true));
        addLevel.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        headerLabel.setText("Ajouter un membre");
    }

    public void showUpdateMembersFields()
    {

        addFirstname.setVisible(true);
        addLastname.setVisible(true);
        addCne.setVisible((true));
        addEmail.setVisible((true));
        addLevel.setVisible((true));
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        addPassword.setVisible(true);
        confirmPassword.setVisible(true);
    }

    public void okButtonClicked()
    {
        if(headerLabel.getText().equals("Ajouter un membre"))
        {
            System.out.println("Hello");
            String firstname = addFirstname.getText();
            String lastname = addLastname.getText();
            String email = addEmail.getText();
            String cne = addCne.getText();
            String password = addPassword.getText();
            String secondPassword = confirmPassword.getText();
            String level = addLevel.getText();

            System.out.println("firstname: "+firstname+" lastname: "+lastname);
            if(!password.equals(secondPassword))
                showDialog("Ajout d'un membre", "Les deux mots de passe ne correspondent pas", root);
            else{
                User user = new User(firstname, lastname, "user", cne, email, password, level);
                try{
                    Validator.validateForAddUser(user);
                    UserAccess.store(user);
                    refreshTable();
                    StackPane pane = new StackPane();
                    root.getChildren().add(pane);
                    showDialog("Ajout", "L'utilisateur a été ajouté avec succès", pane);
                    cancelButtonClicked();
                }
                catch (Exception e)
                {
                    showDialog("Ajout d'un memebre", e.getMessage(), root);
                }
            }




        }

        if(headerLabel.getText().equals(("Modifier les informations d'un membre")))
        {
            String firstname = addFirstname.getText();
            String lastname = addLastname.getText();
            String email = addEmail.getText();
            String cne = addCne.getText();
            String level = addLevel.getText();
            String pass = addPassword.getText();
            String confirpass = confirmPassword.getText();
            if(!pass.equals(confirpass))
                showDialog("Ajout d'un membre", "Les deux mots de passe ne correspondent pas", root);
            else{
                User user = table.getSelectionModel().getSelectedItem();
                user.setFirstname(firstname);
                user.setLastname((lastname));
                user.setEmail(email);
                user.setPassword(pass);
                user.setCne(cne);
                user.setLevel(level);
                try{
                    Validator.validateForAddUser(user);
                    UserAccess.update(user);
                    refreshTable();
                    cancelButtonClicked();
                    showDialog("Ajout", "Les informations ont été modifiées avec succès", root);
                }
                catch (Exception e){
                    showDialog("Modification des informations d'un membre", e.getMessage(), root);
                }

            }

        }

    }

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
        headerLabel.setText("Membres");
    }


    public void updateMemberButtonClicked() throws Exception
    {

            User selectedMember = table.getSelectionModel().getSelectedItem();
            if(selectedMember==null)
                showDialog("Modification", "Veuillez sélectionner un membre pour modifier ses informations", root);
            else{
                showUpdateMembersFields();
                addFirstname.setPromptText(selectedMember.getFirstname());
                addLastname.setPromptText(selectedMember.getLastname());
                addCne.setPromptText(selectedMember.getCne());
                addEmail.setPromptText(selectedMember.getEmail());
                addLevel.setPromptText(selectedMember.getLevel());
                headerLabel.setText("Modifier les informations d'un membre");
            }

    }

    public void deleteMemberButtonClicked()
    {
        User selectedMember = table.getSelectionModel().getSelectedItem();
        if(selectedMember==null)
            showDialog("Suppression d'un membre", "Veuillez selectionner un membre pour le supprimer", root);
        else
        {
            UserAccess.delete(selectedMember.getId());
            refreshTable();
            showDialog("Suppression d'un membre", "L'utilisateur a été retiré avec succès", root);
        }

    }

    public void refreshTable()
    {
        ObservableList users = UserAccess.getAll();
        table.setItems(users);
    }

    public void loadCotisationsView(ActionEvent event)
    {
        System.out.println("you vant to see cotisations");
        try{
            URL url = new File("src/views/cotisations-view.fxml").toURL();
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

    public void loadEventsView(ActionEvent event)
    {
        System.out.println("you vant to see events");

        try{
            URL url = new File("src/views/events-view.fxml").toURL();
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
