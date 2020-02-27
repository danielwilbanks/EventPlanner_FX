package eventPlanner.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javax.swing.*;

public class NewEventStdController {

    public NewEventStdController() {}

    @FXML
    RadioButton vatsimRB, ivaoRB, pilotEdgeRB;

    @FXML
    Label titleLabel;

    @FXML
    TextField eventNameTxt;

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
}
