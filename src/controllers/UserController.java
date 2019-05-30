package controllers;
import com.jfoenix.controls.JFXButton;
import dao.access.CotisationAccess;
import dao.access.EventAccess;
import dao.models.Cotisation;
import dao.models.Event;
import dao.models.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserController extends Controller implements Initializable {


    public static User user;
    public JFXButton cotisationsButton;
    public JFXButton eventsButton;
    public Label usernameLabel;
    public VBox container;
    private String flag; //permits not to load the same data twice in a same view
    public Label viewTitle;
    private Label headerTitle;
    private StackPane headerPane;
    public MenuButton profileButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        flag = "";
        user = AuthController.connectedUser; //get connected user
        String username = user.getFirstname()+" "+user.getLastname();
        usernameLabel.setText(username);
        headerTitle = new Label();
        container.getChildren().add(headerTitle);
        headerTitle.setFont(Font.font("Calibri", 2));
        headerTitle.setTextFill(Color.web("#26302e", 0.8));
        container.setSpacing(30);
        container.setPadding(new Insets( 60, 20, 20, 20));
    }


    //load events in the main container
    public void loadEvents()
    {
        //if not already seeing events
        if(!flag.equals("events"))
        {
            ObservableList<Event> events = EventAccess.getAll(); //get all events
            flag = "events";
            container.getChildren().clear();
            insertHeader("Voici la liste des évènements organisés par votre club"); //add the header
            viewTitle.setVisible(true);
            viewTitle.setText("Evènements");
            for(Event event: events)
            {
                VBox pane = new VBox();
                pane.setSpacing(15);
                Label nameText = new Label("Nom de l'évènement: ");
                Label descriptionText = new Label("Description: ");
                Label dateText = new Label("Date de l'évènement: ");
                nameText.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
                nameText.setTextFill(Color.web("#144c41", 0.8));
                dateText.setFont(Font.font("Calibri", FontWeight.BOLD,16));
                dateText.setTextFill(Color.web("#144c41", 0.8));
                descriptionText.setFont(Font.font("Calibri", FontWeight.BOLD,16));
                descriptionText.setTextFill(Color.web("#144c41", 0.8));

                Label name = new Label(String.valueOf(event.getName()));
                Label description = new Label(event.getDescription());
                Label date = new Label(event.getDate());
                HBox hBox = new HBox(10);
                hBox.getChildren().addAll(nameText, name);
                pane.getChildren().add(hBox);
                hBox = new HBox(10);
                hBox.getChildren().addAll(descriptionText, description);
                pane.getChildren().add(hBox);
                hBox = new HBox(10);
                hBox.getChildren().addAll(dateText, date);
                pane.getChildren().addAll(hBox);

                pane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 5, 0.2, 0.0, 0.0);" +
                        "-fx-background-color: white;");
                pane.setMaxWidth(600);
                pane.setMinHeight(140);
                pane.setPadding(new Insets(20,20,20,20));
                container.getChildren().add(pane);

            }

        }


    }

    private void insertHeader(String text)
    {
        headerTitle.setText(text);
        headerTitle.setFont(Font.font("calibri", 12));
        headerPane = new StackPane();
        headerPane.getChildren().add(headerTitle);
        headerPane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 5, 0.2, 0.0, 0.0);" +
                "-fx-background-color: #a6b2ad;");
        headerPane.setMaxWidth(400);
        headerPane.setMaxHeight(100);
        headerPane.setPadding(new Insets(30, 30, 30, 30));
        container.getChildren().add(headerPane);
    }

    public void loadCotisations()
    {
        if(!flag.equals("cotisations"))
        {
            viewTitle.setVisible(true);
            viewTitle.setText("Cotisations");
            ObservableList<Cotisation> cotisations = CotisationAccess.getAll();
            flag = "cotisations";
            container.getChildren().clear();
            insertHeader("Voici la liste des cotisations de votre club");
            headerTitle.setText("Les cotisations du club. Les avez vous toutes payées?");
            //container.getChildren().add(headerTitle);
            for(Cotisation cotisation: cotisations)
            {
                VBox pane = new VBox();
                pane.setSpacing(15);
                Label amountText = new Label("Somme ");
                Label descriptionText = new Label("Description: ");
                Label dateText = new Label("Date limite de paiement: ");
                amountText.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
                amountText.setTextFill(Color.web("#144c41", 0.8));
                dateText.setFont(Font.font("Calibri", FontWeight.BOLD,16));
                dateText.setTextFill(Color.web("#144c41", 0.8));
                descriptionText.setFont(Font.font("Calibri", FontWeight.BOLD,16));
                descriptionText.setTextFill(Color.web("#144c41", 0.8));

                Label amount = new Label(String.valueOf(cotisation.getAmount()));
                Label description = new Label(cotisation.getDescription());
                Label date = new Label(cotisation.getTimeLimit());
                HBox hBox = new HBox(10);
                hBox.getChildren().addAll(amountText, amount);
                pane.getChildren().add(hBox);
                hBox = new HBox(10);
                hBox.getChildren().addAll(descriptionText, description);
                pane.getChildren().add(hBox);
                hBox = new HBox(10);
                hBox.getChildren().addAll(dateText, date);
                pane.getChildren().addAll(hBox);

                pane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 5, 0.2, 0.0, 0.0);" +
                        "-fx-background-color: white;");
                pane.setMaxWidth(600);
                pane.setMinHeight(140);
                pane.setPadding(new Insets(20,20,20,20));
                container.getChildren().add(pane);

            }
        }
    }

    //load profile infos
    public void  loadProfileInfos()
    {

        if(!flag.equals("profile"))
        {
            flag = "profile";
            insertHeader("Vos informations");
            container.getChildren().clear();
            viewTitle.setText("Profile");
            VBox pane = new VBox(); //pane containes box of the infos
            pane.setSpacing(15);
            Label lastnameText = new Label("NOM");
            Label firstnameText = new Label("PRENOM(S): ");
            Label cneText = new Label("CNE");
            Label emailText = new Label("EMAIL");
            Label levelText = new Label("LEVEL");
            Label titleLabels[] = new Label[]{lastnameText, firstnameText, cneText, emailText, levelText}; //labels for attributes names

            Label firstname = new Label(String.valueOf(user.getFirstname()));
            Label lastname = new Label(user.getLastname());
            Label cne = new Label(user.getCne());
            Label email = new Label(user.getEmail());
            Label level = new Label(user.getLevel());
            Label labels[] = new Label[]{firstname, lastname, cne, email, level}; //values for attributes values

            //style attributes names
            for(Label label: titleLabels)
            {
                label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
                label.setTextFill(Color.web("#144c41", 0.8));
            }

            //add attributes names and values to containers
           for(int i = 0; i < labels.length; i++) {
               HBox hBox = new HBox(10);
               hBox.getChildren().addAll(titleLabels[i], labels[i]);
               pane.getChildren().add(hBox);
           }

                pane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);" +
                        "-fx-background-color: white;");
                pane.setMaxWidth(600);
                pane.setMinHeight(140);
                pane.setPadding(new Insets(20,20,20,20));
                container.getChildren().add(pane);

            }

        container.setSpacing(50);

    }

    public void disconnect(ActionEvent event)
    {
        disconnect(profileButton);
    }
}
