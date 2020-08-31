package launcher.avaj.vehicles;

import launcher.avaj.Coordinates;
import launcher.avaj.Flyable;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type.toUpperCase()) {
            case "HELICOPTER":
                return new Helicopter(name, coordinates);
            case "JETPLANE":
                return new JetPlane(name, coordinates);
            case "BALOON":
                return new Baloon(name, coordinates);
            default:
                return null;
        }
    }
}
