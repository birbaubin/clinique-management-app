package controllers;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import dao.access.UserAccess;
import dao.models.User;

public class MainController {

public VBox container;
public Button members;
public TextField add_firstname;
public TextField add_lastname;
public TextField add_birthday;

public TextField add_email;

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
            TableColumn<User, String> birthday = new TableColumn<>("Date de naissance");
            birthday.setMinWidth(130);
            TableColumn<User, String> level = new TableColumn<>("Niveau");
            level.setMinWidth(90);

            firstname.setCellValueFactory(new PropertyValueFactory("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            birthday.setCellValueFactory(new PropertyValueFactory("birthday"));
            cne.setCellValueFactory(new PropertyValueFactory("cne"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            level.setCellValueFactory(new PropertyValueFactory("level"));


            ObservableList users = UserAccess.getAll();
            table.setItems(users);
            table.getColumns().addAll(firstname, lastname, cne, email, level, birthday);


            //add fields to add a new member

            container.getChildren().add(table);
            container.setPadding(new Insets(50, 50, 30, 50));
        }


    }

}
