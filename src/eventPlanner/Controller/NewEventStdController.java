package eventPlanner.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class NewEventStdController {

    public NewEventStdController() {}

    @FXML
    RadioButton vatsimRB, ivaoRB, pilotEdgeRB;

    @FXML
    Label titleLabel;

    @FXML
    TextField eventNameTxt, featAptTxt;

    @FXML
    ScrollPane viewPane;

    public void initialize() {

        //Assigning toggle group for VATSIM, IVAO or PilotEdge selection
        ToggleGroup networkGroup = new ToggleGroup();
        vatsimRB.setToggleGroup(networkGroup);
        ivaoRB.setToggleGroup(networkGroup);
        pilotEdgeRB.setToggleGroup(networkGroup);
    }

    //Changes the text of the event name label when the text of the event name field is updated
    public void nameTextChanged() {
        if (!eventNameTxt.getText().isEmpty())
            titleLabel.setText(eventNameTxt.getText());
        else
            titleLabel.setText("New Event - Standard");
    }

    //Save button click handler method
    public void saveButtonClicked() {
        validate(); //Validate all fields
    }

    //Form validation method
    public boolean validate() {

        boolean missingReq = false;
        boolean invalidField = false;

        //Checking event name is filled in
        if (eventNameTxt.getText().isEmpty()) {
            missingReq = true;
            eventNameTxt.setStyle("-fx-border-color: red");
        } else {
            eventNameTxt.setStyle("-fx-border-color: none;");
        }

        if (missingReq || invalidField)
        {
            viewPane.setVvalue(0);
            return false;
        } else
            return true;
    }

    //Method to open the route manager in another window
    public void openRouteManager() {
        try {
            FXMLLoader rmLoader = new FXMLLoader(new File("src/eventPlanner/FXML/RouteManager.fxml").toURI().toURL());
            Parent rmRoot = (Parent) rmLoader.load();
            Stage rmStage = new Stage();
            rmStage.setTitle("Route Management");
            rmStage.setScene(new Scene(rmRoot));
            rmStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error launching route manager.");
        }
    }
}
