package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/views/auth-view.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Manage efficiently your club");
        primaryStage.setScene(new Scene(root, 750, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
