package eventPlanner.Class;

import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

//Generic event class
public class Event {

    //Attributes
    private String eventName, description, featApt;
    private Date eventDate;
    private Integer network;
    private ArrayList<Route> routes;
    private ArrayList<Email> contactAddresses;
    private Image banner;
    private boolean signupsFlag, bookingsFlag, prefRoutingFlag;

    public Event() {

    }

    //Accessors and mutators
    public String getName() { return eventName; }
    public String getDescription() { return description; }
    public String getFeatApt() { return featApt; }
    public Date getDate() { return eventDate; }
    public Integer getNetwork() { return network; }
    public ArrayList getRoutes() { return routes; }
    public ArrayList getEmails() { return contactAddresses; }
    public Image getBanner() { return banner; }
    public boolean reqSignups() { return signupsFlag; }
    public boolean reqBookings() { return bookingsFlag; }
    public boolean hasPrefRouting() { return prefRoutingFlag; }

        //Accessors specific to array lists

    //Route ArrayList methods
    public void addRoute(Route r) {
        if (routes == null)
            routes = new ArrayList<Route>();

        routes.add(r);
    }

    public Route getRouteAtIndex(int i) {
        return routes.get(i);
    }

}
