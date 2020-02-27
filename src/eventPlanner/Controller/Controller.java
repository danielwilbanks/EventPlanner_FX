package eventPlanner.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Controller {

    @FXML
    private BorderPane masterPane;



    public void Controller() {

    }

    public void initialize() {
        try
        {
            masterPane.setCenter(FXMLLoader.load(new File("src/eventPlanner/FXML/NewEventStd.fxml").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
