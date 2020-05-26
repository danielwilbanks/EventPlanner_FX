/*
*       AppData class holds universal data (airports, aircraft, past events, etc) parsed from DVA API's
* */

package eventPlanner.App;

import eventPlanner.Class.Airport;

import java.util.ArrayList;

public class AppData {
    private ArrayList<Airport> airports;

    public AppData() {
        airports = new ArrayList<Airport>();
    }

    public ArrayList<Airport> getAirports() { return airports; }

    public void addAirport(Airport apt) {
        airports.add(apt);
    }


}
