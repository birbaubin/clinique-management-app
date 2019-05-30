package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import dao.access.UserAccess;
import dao.models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Controller {

    public void showDialog(String heading, String body, StackPane pane)
    {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton ok = new JFXButton();
        content.setActions(ok);
        pane.getChildren().add(ok);
        ok.setOnAction(Event ->{
            dialog.close();
        });

        dialog.show();

    }


    //disconnect
    public void disconnect(Node element)
    {
        try{
            URL url = new File("src/views/auth-view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage window = (Stage)(element.getScene().getWindow());
            window.setScene(new Scene(root, 750, 600));

            window.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
