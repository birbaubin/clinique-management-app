package views;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class AddMemberAlertBox extends Stage {

    private Scene scene;
    public AddMemberAlertBox() throws Exception
    {

        URL url = new File("src/views/add-member-view.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        //VBox root = new VBox();
        this.setScene(new Scene(root, 300, 500));
        this.setWidth(300);
        this.setHeight(550);
        this.show();
    }

}
