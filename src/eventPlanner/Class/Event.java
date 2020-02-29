package eventPlanner.Class;

import javafx.scene.image.Image;

import java.util.Date;
import java.util.Vector;

//Generic event class
public class Event {

    //Attributes
    private String eventName, description, featApt;
    private Date eventDate;
    private Integer network;
    private Vector<Route> routes;
    private Vector<Email> contactAddresses;
    private Image banner;
    private boolean signupsFlag, bookingsFlag, prefRoutingFlag;

    public Event() {

    }


}
