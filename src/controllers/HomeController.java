package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class HomeController {

    public void membersButtonClicked(ActionEvent event) throws Exception
    {
        URL url = new File("src/views/members-view.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 980, 700));
        window.show();
    }
}
