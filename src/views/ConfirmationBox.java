package views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ConfirmationBox extends Stage {

    private StackPane pane;
    private Scene scene;
    Label textDisplayed;
    public ConfirmationBox(String text)
    {
        pane = new StackPane();
        this.scene = new Scene(pane, 300, 200);
        textDisplayed = new Label(text);
        pane.getChildren().add(textDisplayed);
        this.setScene(scene);
        this.show();
    }
}
