package eventPlanner.Class;

public class Route {

    //Enums
    public enum RVSM {WESTBOUND, EASTBOUND}

    //Attributes
    private Airport departure, destination;
    private String route, length;
    private RVSM direction;
    private boolean rnav, pilotDefined, signupsRequired;
    private int distance, bearing;

    public Route(Airport dept, Airport dest, String r, boolean rn, int d, String l, RVSM dir, boolean pd, boolean su) {
        departure = dept;
        destination = dest;
        route = r;
        rnav = rn;
        distance = d;
        length = l;
        direction = dir;
        pilotDefined = pd;
        signupsRequired = su;
    }


}
