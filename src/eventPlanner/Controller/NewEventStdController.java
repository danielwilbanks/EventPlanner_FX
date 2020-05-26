package eventPlanner.Controller;

import eventPlanner.App.Main;
import eventPlanner.Class.Airport;
import eventPlanner.Class.Route;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewEventStdController {

    public NewEventStdController() {}

    @FXML
    RadioButton vatsimRB, ivaoRB, pilotEdgeRB;

    @FXML
    Label titleLabel, bannerLbl, bannerErrorLbl;

    @FXML
    TextField eventNameTxt, featAptTxt, startTimeTxt, endTimeTxt;

    @FXML
    TextArea atcEmailTxt, descriptionTxt, currentRoutes;

    @FXML
    ScrollPane viewPane;

    @FXML
    ComboBox airportsCB;

    @FXML
    DatePicker datepicker;

    @FXML
    Button imgButton;

    private File banner;
    private ArrayList<Route> routes;

    public void initialize() {

        //Assigning toggle group for VATSIM, IVAO or PilotEdge selection
        ToggleGroup networkGroup = new ToggleGroup();
        vatsimRB.setToggleGroup(networkGroup);
        ivaoRB.setToggleGroup(networkGroup);
        pilotEdgeRB.setToggleGroup(networkGroup);

        airportsCB.setItems(FXCollections.observableArrayList(Main.appData.getAirports()));
        routes = new ArrayList<Route>();

        featAptTxt.textProperty().addListener((ov, oldText, newText) -> {
            searchAirports(newText);
        });
    }

    //Changes the text of the event name label when the text of the event name field is updated
    public void nameTextChanged() {
        if (!eventNameTxt.getText().isEmpty())
            titleLabel.setText(eventNameTxt.getText());
        else
            titleLabel.setText("New Event - Standard");
    }

    //Review button click handler method
    public void reviewButtonClicked() {
        validate();
    }

    //Save button click handler method
    public void saveButtonClicked() {
        validate(); //Validate all fields
    }

    //Cancel button click handler method
    public void cancelBtnClicked() {

    }

    //Image button clicked handler method
    public void imgButtonClicked() {
        if (imgButton.getText().equals("Browse")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Banner Image");
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG and JPG Images", "png", "jpg", "jpeg"));
            banner = fileChooser.showOpenDialog(null);
            Image bannerImg = new Image(banner.toURI().toString()); //Image objects used to check pixel size below

            //Validate the image selected
            if (bannerImg.getHeight() > 256 || bannerImg.getWidth() > 800 || banner.length() > 131072) { //Banner image size restrictions are 800px256p and 131kb
                bannerErrorLbl.setVisible(true);
            } else {
                bannerErrorLbl.setVisible(false);
                imgButton.setText("Remove");
                bannerLbl.setText(banner.getName());
            }

            bannerImg = null;
        } else { //Removes the banner image
            bannerLbl.setText("No file selected");
            imgButton.setText("Browse");
            banner = null; //Clear the banner image object so it cannot be used to create/edit the event once removed
        }
    }

    //Method to search the list of airports assigned to the combo box for an airport matching the code entered
    public void searchAirports(String value) {

        if (value == null || value.isEmpty()  || value.length() < 3 || value.length() > 4) {
            airportsCB.getSelectionModel().clearSelection();
        } else {
            //For loop checks each airports ICAO and IATA code against the value in the string, and if it matches one, it displays that airport in the combo box
            for (Airport i : Main.appData.getAirports()) {
                if (value.length() == 3 && value.toUpperCase().equals(i.getIATA())) {
                    airportsCB.getSelectionModel().select(i);
                }
                else if (value.toUpperCase().equals(i.getICAO())) {
                    airportsCB.getSelectionModel().select(i);
                }
            }
        }

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
            eventNameTxt.setStyle("-fx-border-color: none");
        }

        //Checking for a featured airport
        if (airportsCB.getValue() == null) {
            missingReq = true;
            airportsCB.setStyle("-fx-border-color: red");
        } else {
            airportsCB.setStyle("-fx-border-color: none");
        }

        //Checking for a valid date, not in the past
        LocalDate date = datepicker.getValue();

        if (datepicker.getValue() == null) {
            missingReq = true;
            datepicker.setStyle("-fx-border-color: red");
        } else if (date.isBefore(LocalDate.now())) {
            invalidField = true;
            datepicker.setStyle("-fx-border-color: red");
        } else {
            datepicker.setStyle("-fx-border-color: none");
        }

        //Checking the start time
        if (!startTimeTxt.getText().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")) {
            invalidField = true;
            startTimeTxt.setStyle("-fx-border-color: red");
        } else { //Format checks out
            String startTime[] = startTimeTxt.getText().split(":");
            if (Integer.parseInt(startTime[0]) < 0 || Integer.parseInt(startTime[0]) > 23) { //Checking the hours value. Valid range 0-23
                invalidField = true;
                startTimeTxt.setStyle("-fx-border-color: red");
            } else if (Integer.parseInt(startTime[1]) < 0 || Integer.parseInt(startTime[1]) > 59) { //Checking the minutes value. Valid range 0-59
                invalidField = true;
                startTimeTxt.setStyle("-fx-border-color: red");
            } else {
                startTimeTxt.setStyle("-fx-border-color: none");
            }
        }

        //Checking the end time
        if (!endTimeTxt.getText().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")) {
            invalidField = true;
            endTimeTxt.setStyle("-fx-border-color: red");
        } else { //Format checks out
            String endTime[] = endTimeTxt.getText().split(":");
            if (Integer.parseInt(endTime[0]) < 0 || Integer.parseInt(endTime[0]) > 23) { //Checking the hours value. Valid range 0-23
                invalidField = true;
                endTimeTxt.setStyle("-fx-border-color: red");
            } else if (Integer.parseInt(endTime[1]) < 0 || Integer.parseInt(endTime[1]) > 59) { //Checking the minutes value. Valid range 0-59
                invalidField = true;
                endTimeTxt.setStyle("-fx-border-color: red");
            } else {
                endTimeTxt.setStyle("-fx-border-color: none");
            }
        }

        //ATC Contact Address Validation
        String atcAddressRaw = atcEmailTxt.getText();
        String [] addresses = atcAddressRaw.split("\n");
        boolean invalidEmail = false;

        for (String s : addresses) {
            if (!s.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) { //Email validation regex
                invalidEmail = true;
                invalidField = true;
            }
        }

        if (!invalidEmail) {
            atcEmailTxt.setStyle("-fx-border-color: none");
        } else {
            atcEmailTxt.setStyle("-fx-border-color: red");
        }

        //Description validation
        if (descriptionTxt.getText().isEmpty()) {
            missingReq = true;
            descriptionTxt.setStyle("-fx-border-color: red");
        } else {
            descriptionTxt.setStyle("-fx-border-color: none");
        }

        //Check for routes ToDo: Add validation for minimum route req's such as more than one route, and to include at least one inbd and one outbnd route
        if (routes.isEmpty()) {
            missingReq = true;
            currentRoutes.setStyle("-fx-border-color: red");
        } else {
            currentRoutes.setStyle("-fx-border-color: none");
        }

        //Check for a banner image
        if (banner == null) {
            missingReq = true;
            bannerErrorLbl.setVisible(true);
        } else {
            bannerErrorLbl.setVisible(false);
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
