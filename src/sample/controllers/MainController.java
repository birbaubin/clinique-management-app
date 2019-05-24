package sample.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.dao.access.Access;
import sample.dao.access.UserAccess;
import sample.dao.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainController {

public VBox container;
public Button members;
public TableView<User> table;


    public void membersButtonClicked()
    {
        if(table==null)
        {

            table = new TableView<User>();
            System.out.println(container);
       /* System.out.println("You want to see members");
        container.getChildren().add(new Label("You added me"));*/

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

            ObservableList users = FXCollections.observableArrayList();
            ArrayList<HashMap<String, String>> allUsers = UserAccess.getAll();
            for(HashMap user: allUsers){
                users.add(new User((String)user.get("firstname"),
                        (String)(user.get("lastname")),
                        (String)user.get("birthday"),
                        (String)user.get("cne"),
                        (String) user.get("email"),
                        (String)user.get("password"),
                        (String)user.get("level")));
            }
            //users.add(nigga);
            table.setItems(users);
            table.getColumns().addAll(firstname, lastname, cne, email, level, birthday);

            container.getChildren().add(table);
            container.setPadding(new Insets(50, 50, 30, 50));
        }



    }
}
