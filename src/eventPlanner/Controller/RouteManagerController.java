package eventPlanner.Controller;

import eventPlanner.App.Main;
import eventPlanner.Class.Airport;
import eventPlanner.Class.Route;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class RouteManagerController {

    @FXML
    RadioButton rnavRB, nonrnavRB;

    @FXML
    ToggleButton ebToggle, wbToggle;

    @FXML
    Button addCustomBtn, resetCustomBtn;

    @FXML
    ComboBox depFieldCustomCB, arrFieldCustomCB, depSearchCB, arrSearchCB;

    @FXML
    TextField depFieldCustomTxt, arrFieldCustomTxt, routingTxt, distanceTxt, timeTxt;

    @FXML
    CheckBox pilotDefXB, signupXB;

    public RouteManagerController() {}

    public void initialize() {

        //Assign toggle groups
        ToggleGroup RVSMToggleGroup = new ToggleGroup();
        ToggleGroup RNAVToggleGroup = new ToggleGroup();

        //Populate airport selection boxes
        depFieldCustomCB.setItems(FXCollections.observableArrayList(Main.appData.getAirports()));
        arrFieldCustomCB.setItems(FXCollections.observableArrayList(Main.appData.getAirports()));
        depSearchCB.setItems(FXCollections.observableArrayList(Main.appData.getAirports()));
        arrSearchCB.setItems(FXCollections.observableArrayList(Main.appData.getAirports()));

        rnavRB.setToggleGroup(RNAVToggleGroup);
        nonrnavRB.setToggleGroup(RNAVToggleGroup);
        ebToggle.setToggleGroup(RVSMToggleGroup);
        wbToggle.setToggleGroup(RVSMToggleGroup);
    }

    //Custom Route Add Button Handler
    public void addCustomClicked() {

        if (!validateCustom())
            return;

        //Route variables
        Airport dept = (Airport) depFieldCustomCB.getValue(), dest = (Airport) arrFieldCustomCB.getValue();
        String route = routingTxt.getText(), length = timeTxt.getText();
        int distance = Integer.parseInt(distanceTxt.getText());
        Route.RVSM rvsmDir;

        boolean rnav;

        if (rnavRB.isSelected())
            rnav = true;
        else
            rnav = false;

        if (ebToggle.isSelected())
            rvsmDir = Route.RVSM.EASTBOUND;
        else
            rvsmDir = Route.RVSM.WESTBOUND;


        Route newRoute = new Route(dept, dest, route, rnav, distance, length, rvsmDir, pilotDefXB.isSelected(), signupXB.isSelected());
    }

    //Custom Route Reset Button Handler
    public void resetCustomClicked() {

    }

    /*
    *                                                                                           Validators
    * */

    //Validates custom route fields
    public boolean validateCustom() {

        boolean missingReq = false, invalidField = false;

        //Check for a departure field
        if (depFieldCustomCB.getValue() == null) {
            missingReq = true;
            depFieldCustomCB.setStyle("-fx-border-color: red");
        } else {
            depFieldCustomCB.setStyle("-fx-border-color: none");
        }

        //Check for arrival field
        if (arrFieldCustomCB.getValue() == null) {
            missingReq = true;
            arrFieldCustomCB.setStyle("-fx-border-color: red");
        } else {
            arrFieldCustomCB.setStyle("-fx-border-color: none");
        }

        //Check for routing
        if (routingTxt.getText().isEmpty()) {
            missingReq = true;
            routingTxt.setStyle("-fx-border-color: red");
        } else {
            routingTxt.setStyle("-fx-border-color: none");
        }

        //Check the distance
        if (distanceTxt.getText().isEmpty()) {
            missingReq = true;
            distanceTxt.setStyle("-fx-border-color: red");
        } else if (Integer.parseInt(distanceTxt.getText()) < 1 || Integer.parseInt(distanceTxt.getText()) > 10000) { //Valid range for distance is 1-10,000nm
            invalidField = true;
            distanceTxt.setStyle("-fx-border-color: red");
        } else {
            distanceTxt.setStyle("-fx-border-color: none");
        }

        //Check the ETE
        if (!timeTxt.getText().matches("^([0-9][0-9]):([0-9][0-9])$")) {
            invalidField = true;
            timeTxt.setStyle("-fx-border-color: red");
        } else {
            String[] ete = timeTxt.getText().split(":");

            if (Integer.parseInt(ete[0]) < 0 || Integer.parseInt(ete[0]) > 23 || Integer.parseInt(ete[1]) < 0 || Integer.parseInt(ete[1]) > 59) {
                invalidField = true;
                timeTxt.setStyle("-fx-border-color: red");
            } else {
                timeTxt.setStyle("-fx-border-color: none");
            }
        }

        //Check for RVSM direction
        if (!ebToggle.isSelected() && !wbToggle.isSelected()) {
            missingReq = true;
            ebToggle.setStyle("-fx-border-color: red");
            wbToggle.setStyle("-fx-border-color: red");
        } else {
            ebToggle.setStyle("-fx-border-color: none");
            wbToggle.setStyle("-fx-border-color: none");
        }

        if (invalidField || missingReq)
            return false;
        else
            return true;
    }
}
