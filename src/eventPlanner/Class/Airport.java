package eventPlanner.Class;

public class Airport {

    private String name, icao, iata;
    private Double lat, lng;

    public Airport(String iataCode, Double lngCord, String aptName, String icaoCode, Double latCord) {
        this.iata = iataCode;
        this.icao = icaoCode;
        this.name = aptName;
        this.lat = latCord;
        this.lng = lngCord;
    }

    public String toString() {
        return name;
    }

    //TODO: Add getter and setter methods
    public String getICAO() { return icao; }
    public String getIATA() { return iata; }
}
