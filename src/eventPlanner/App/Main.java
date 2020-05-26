package eventPlanner.App;

import eventPlanner.Class.Airport;
import eventPlanner.Class.Event;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main extends Application {

    //API Connection
    private String airportsURL = "https://dev.deltava.org/airports.ws";

    public static Scene master;
    public static AppData appData;
    public Event workingEvent = new Event();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        root = FXMLLoader.load(new File("src/eventPlanner/FXML/welcomeView.fxml").toURI().toURL());
        primaryStage.setTitle("Event Planner");
        master = new Scene(root, 750, 500);
        primaryStage.setScene(master);
        appData = new AppData();
        loadAirports();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setView(String path) throws IOException {
        master.setRoot(FXMLLoader.load(new File(path).toURI().toURL()));
    }

    //Runs at program startup, loads airports from DVA website
    public void loadAirports() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(airportsURL)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::parseAirportsJSON)
                .join();
    }

    public static String parseAirportsJSON(String responseBody) {
        JSONArray airports = new JSONArray(responseBody);
        Airport temp;

        for (int i = 0; i < airports.length(); i++) {

            //Initial Parsing of Data
            JSONObject airport = airports.getJSONObject(i);
            String iata = airport.getString("iata");
            String icao = airport.getString("icao");
            String name = airport.getString("name");
            Double lat = airport.getDouble("lat");
            Double lng = airport.getDouble("lng");

            //Create temp airport object to add to application array
            temp = new Airport(iata, lng, name, icao, lat);

            appData.addAirport(temp); //Add the new Airport object to the AppData ArrayList of Airports

            temp = null;
        }
        return null;
    }
}
