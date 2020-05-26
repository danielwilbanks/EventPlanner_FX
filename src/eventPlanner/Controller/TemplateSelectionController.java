package eventPlanner.Controller;

import eventPlanner.App.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TemplateSelectionController {


    public void standardTemplateClicked() {
        try {
            Main.setView("src/eventPlanner/FXML/NewEventStd.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
