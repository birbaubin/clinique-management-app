package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.access.UserAccess;
import dao.models.User;
import views.AddMemberAlertBox;

public class MainController {

public AnchorPane container;
public Button members;
public JFXButton addElement;
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

public JFXDialog dialog;

public TableView<User> table;


    public void membersButtonClicked()
    {
        if(table==null)
        {
            table = new TableView<User>();
            TableColumn<User, String> firstname = new TableColumn<>("Prénom");
            firstname.setMinWidth(100);
            TableColumn<User, String> lastname = new TableColumn<>("Nom");
            lastname.setMinWidth(100);
            TableColumn<User, String> cne = new TableColumn<>("Cne");
            cne.setMinWidth(100);
            TableColumn<User, String> email = new TableColumn<>("email");
            email.setMinWidth(130);
            TableColumn<User, String> level = new TableColumn<>("Niveau");
            level.setMinWidth(90);

            firstname.setCellValueFactory(new PropertyValueFactory("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            cne.setCellValueFactory(new PropertyValueFactory("cne"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            level.setCellValueFactory(new PropertyValueFactory("level"));


            ObservableList users = UserAccess.getAll();
            table.setItems(users);
            table.getColumns().addAll(firstname, lastname, cne, email, level);
            addElement.setVisible(true);
            container.getChildren().addAll(table);
            AnchorPane.setTopAnchor(table, 10.0);
            AnchorPane.setLeftAnchor(table, 10.0);
            headerLabel.setText("Membres");
        }


    }

    public void addElementButtonClicked()
    {
        if(headerLabel.getText().equals("Membres"))
        {
            System.out.println("Display in");
            try{

                AddMemberAlertBox box = new AddMemberAlertBox();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void okButtonClicked()
    {
        System.out.println("ok clicked");
    }


}
