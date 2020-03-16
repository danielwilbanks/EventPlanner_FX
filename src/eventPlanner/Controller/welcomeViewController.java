package eventPlanner.Controller;

import eventPlanner.App.Main;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

public class welcomeViewController {

    public welcomeViewController() {}

    public void initialize() {

    }

    public void newEventClicked() {
        try {
            Main.setView("src/eventPlanner/FXML/templateSelection.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
