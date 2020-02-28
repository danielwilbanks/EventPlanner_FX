package eventPlanner.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class RouteManagerController {

    @FXML
    RadioButton rnavRB, nonrnavRB;

    @FXML
    ToggleButton ebToggle, wbToggle;

    public RouteManagerController() {}

    public void initialize() {

        //Assign toggle groups
        ToggleGroup RVSMToggleGroup = new ToggleGroup();
        ToggleGroup RNAVToggleGroup = new ToggleGroup();

        rnavRB.setToggleGroup(RNAVToggleGroup);
        nonrnavRB.setToggleGroup(RNAVToggleGroup);
        ebToggle.setToggleGroup(RVSMToggleGroup);
        wbToggle.setToggleGroup(RVSMToggleGroup);
    }

}
