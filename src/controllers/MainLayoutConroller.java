package controllers;


import com.jfoenix.controls.JFXButton;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainLayoutConroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton evenementsButton;

    @FXML
    private JFXButton cotisationsButton;

    @FXML
    private JFXButton membresButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private Label pageTitle;

    @FXML
    AnchorPane container;

    @FXML
    void onCloseButton(ActionEvent event) {
        System.out.println("close");
        Platform.exit();
    }

    @FXML
    void onCotisationsButton(ActionEvent event) throws IOException {
        pageTitle.setText("Cotisations");
        container.getChildren().clear();
        container.getChildren().add(FXMLLoader.load(new File("src/views/cotisation-view.fxml").toURI().toURL()));
    }

    @FXML
    void onEvenementsButton(ActionEvent event) throws IOException {
        pageTitle.setText("Evenements");
        container.getChildren().clear();
        container.getChildren().add(FXMLLoader.load(new File("src/views/events-view.fxml").toURI().toURL()));

    }


    @FXML
    void onMembresButton(ActionEvent event) throws IOException {
        pageTitle.setText("Membres");
        container.getChildren().clear();
        container.getChildren().add(FXMLLoader.load(new File("src/views/members-view.fxml").toURI().toURL()));
    }


    @FXML
    void onLogoutButton(ActionEvent event) throws IOException {


        URL url = new File("src/views/auth-view.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        assert evenementsButton != null : "fx:id=\"evenementsButton\" was not injected: check your FXML file 'admin-main-layout.fxml'.";
        assert cotisationsButton != null : "fx:id=\"cotisationsButton\" was not injected: check your FXML file 'admin-main-layout.fxml'.";
        assert membresButton != null : "fx:id=\"membresButton\" was not injected: check your FXML file 'admin-main-layout.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'admin-main-layout.fxml'.";
        assert pageTitle != null : "fx:id=\"pageTitle\" was not injected: check your FXML file 'admin-main-layout.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'admin-main-layout.fxml'.";

    }
}
