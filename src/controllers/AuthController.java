package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.access.Hash;
import dao.access.UserAccess;
import dao.models.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


//Controls authentication
public class AuthController extends Controller {


    //labels for credentials errors. They appear when there is an auth error
    public Label authUsername, authPassword;

    @FXML
    //auth inputs
    public JFXTextField email;
    public JFXPasswordField password;

    //authenticated user. Variable is initialised when auth succeeds
    public static User connectedUser;


    //validation button
    public JFXButton okButton;

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    //is called when validation button is clicked
    public void okClicked(ActionEvent event) throws IOException
    {
        String username = email.getText();
        String pass = password.getText();

        //check if user entered email an pswd
        if(username.equals("") || pass.equals(""))
            showAuthLabel("Veuillez remplir tous les champs", authUsername);
        else
        {

            checkAuthentication(username, pass, event);
        }

    }

    //checks if credentials are correct
    public void checkAuthentication(String email, String password, ActionEvent event)
    {



        //search a user with the entered email
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        ArrayList<HashMap> result = UserAccess.search(credentials);

        //if no result, show error
        if(result.isEmpty())
            showAuthLabel("Utilisateur inexistant", authUsername);

        else
        {

            //if email exists, verify the entered pass
            if(!(result.get(0).get("password")).equals(Hash.getSecurePassword(password)))
                showAuthLabel("Mot de passe incorrect ", authPassword);

            //if all credentials are correct, check userType
            else
            {
                //if admin, load admin view (members by default)
                if(result.get(0).get("userType").equals("admin"))
                {
                    try{
                        URL url = new File("src/views/layout/admin-main-layout.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });

                        //move around here
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - xOffset);
                                stage.setY(event.getScreenY() - yOffset);
                            }
                        });

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //it is a user
                else {

                    //store user infos in a user model
                    connectedUser = new User(Integer.parseInt((String)result.get(0).get("id")),
                            (String)result.get(0).get("firstname"),
                            (String)result.get(0).get("lastname"),
                            "user",
                            (String)result.get(0).get("cne"),
                            (String)result.get(0).get("email"),
                            (String)result.get(0).get("password"),
                            (String)result.get(0).get("level"));


                    //load simple user view
                    try{
                        URL url = new File("src/views/layout/user-main-layout.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });

                        //move around here
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - xOffset);
                                stage.setY(event.getScreenY() - yOffset);
                            }
                        });

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public void showAuthLabel(String message, Label label)
    {
        authUsername.setVisible(false);
        authPassword.setVisible(false);
        label.setVisible(true);
        label.setText(message);
    }


     //is called when validation button is clicked
    public void onCloseButton(ActionEvent event) throws IOException
    {
        Platform.exit();

    }
}
