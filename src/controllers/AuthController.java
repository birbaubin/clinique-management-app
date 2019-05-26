package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.access.Hash;
import dao.access.UserAccess;
import dao.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AuthController extends Controller{

    public Label authUsername, authPassword;
    public JFXTextField email;
    public JFXPasswordField password;

    public JFXButton okButton;

    public void okClicked(ActionEvent event) throws IOException
    {
        String username = email.getText();
        String pass = password.getText();
        if(username.equals("") || pass.equals(""))
            showAuthLabel("Veuillez remplir tous les champs", authUsername);
        else
        {
            checkAuthentication(username, pass, event);
        }

    }

    public void checkAuthentication(String email, String password, ActionEvent event)
    {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        //credentials.put("password", Hash.getSecurePassword(password));
        ArrayList<HashMap> result = UserAccess.search(credentials);
        if(result.isEmpty())
            showAuthLabel("Utilisateur inexistant", authUsername);

        else
        {
            if(!(result.get(0).get("password")).equals(Hash.getSecurePassword(password)))
                showAuthLabel("Mot de passe inexistant", authPassword);
            else
            {
                System.out.println("Welcoooooooooome");
                try{
                    URL url = new File("src/views/members-view.fxml").toURL();
                    Parent root = FXMLLoader.load(url);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root, 980, 700));
                    window.show();
                }
                catch (Exception e)
                {

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




}
