package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.access.UserAccess;
import dao.models.User;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import views.AddMemberAlertBox;
import views.ConfirmationBox;

import java.net.URL;
import java.util.ResourceBundle;


public class MembersController extends Controller implements Initializable{

public AnchorPane container;
public StackPane root;
public Button members;
public JFXButton addMemberButton;
public JFXButton updateMemberButton;
public JFXButton deleteMemberButton;
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
            firstname.setMinWidth(150);
            TableColumn<User, String> lastname = new TableColumn<>("Nom");
            lastname.setMinWidth(150);
            TableColumn<User, String> cne = new TableColumn<>("Cne");
            cne.setMinWidth(150);
            TableColumn<User, String> email = new TableColumn<>("email");
            email.setMinWidth(180);
            TableColumn<User, String> level = new TableColumn<>("Niveau");
            level.setMinWidth(130);

            firstname.setCellValueFactory(new PropertyValueFactory("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            cne.setCellValueFactory(new PropertyValueFactory("cne"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            level.setCellValueFactory(new PropertyValueFactory("level"));


            users = UserAccess.getAll();
            table.setItems(users);
            table.getColumns().addAll(firstname, lastname, cne, email, level);
            container.getChildren().addAll(table);
            AnchorPane.setTopAnchor(table, 10.0);
            AnchorPane.setLeftAnchor(table, 70.0);
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

    public void okButtonClicked() throws  Exception
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
            User user = new User(firstname, lastname, "user", cne, email, password, level);
            Validator.validateForAddUser(user);

            UserAccess.store(user);
            refreshTable();
            StackPane pane = new StackPane();
            root.getChildren().add(pane);
            showDialog("Ajout", "L'utilisateur a été ajouté avec succès", pane);
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
            User user = table.getSelectionModel().getSelectedItem();
            user.setFirstname(firstname);
            user.setLastname((lastname));
            user.setEmail(email);
            user.setPassword(pass);
            user.setCne(cne);
            user.setLevel(level);
            UserAccess.update(user);
            refreshTable();
            cancelButtonClicked();
            //showDialog("Ajout", "L'utilisateur a été ajouté avec succès");

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
            showUpdateMembersFields();
            addFirstname.setPromptText(selectedMember.getFirstname());
            addLastname.setPromptText(selectedMember.getLastname());
            addCne.setPromptText(selectedMember.getCne());
            addEmail.setPromptText(selectedMember.getEmail());
            addLevel.setPromptText(selectedMember.getLevel());
            headerLabel.setText("Modifier les informations d'un membre");

    }

    public void deleteMemberButtonClicked()
    {
        ObservableList  allMembers;

        allMembers = table.getItems();
        User selectedMember = table.getSelectionModel().getSelectedItem();
        UserAccess.delete(selectedMember.getId());
        refreshTable();
    }

    public void refreshTable()
    {
        ObservableList users = UserAccess.getAll();
        table.setItems(users);
    }




}
