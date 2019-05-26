package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.access.UserAccess;
import dao.models.User;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Controller {


    public AnchorPane container;
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
    public TableView<User> table;
    public ObservableList users;
    public void addMemberButtonClicked()
    {
        System.out.println("You are here");
        addFirstname.setVisible(true);
        addLastname.setVisible(true);
        addLevel.setVisible(true);
        addCne.setVisible(true);
        addEmail.setVisible(true);
        addPassword.setVisible(true);
        confirmPassword.setVisible(true);

    }

    public void showManageMembersButtons()
    {
        addMemberButton.setVisible(true);
        updateMemberButton.setVisible(true);
        deleteMemberButton.setVisible((true));
    }

    public void okButtonClicked() throws  Exception
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
        table.getItems().add(user);



    }
}
