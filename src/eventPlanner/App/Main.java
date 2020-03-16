package eventPlanner.App;

import eventPlanner.Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static Scene master;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        root = FXMLLoader.load(new File("src/eventPlanner/FXML/welcomeView.fxml").toURI().toURL());
        primaryStage.setTitle("Event Planner");
        master = new Scene(root, 750, 500);
        primaryStage.setScene(master);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setView(String path) throws IOException {
        master.setRoot(FXMLLoader.load(new File(path).toURI().toURL()));
    }
}
