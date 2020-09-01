package launcher.avaj.vehicles;

import launcher.avaj.Coordinates;
import launcher.avaj.Flyable;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (height <= 0){
            System.out.println("wrong height somewhere");
            System.exit(1);
        }
        try{
            Coordinates coordinates = new Coordinates(longitude, latitude, height);
            switch (type.toUpperCase()) {
                case "HELICOPTER":
                    return new Helicopter(name, coordinates);
                case "JETPLANE":
                    return new JetPlane(name, coordinates);
                case "BALOON":
                    return new Baloon(name, coordinates);
                default:
                    throw new IncorrectAircraftException("Helicopter, Baloon, Jetplane");
            }
        }catch (IncorrectAircraftException e){
            System.out.println(e.getMessage());
            throw new IncorrectAircraftException();
        }
    }
}

class IncorrectAircraftException extends RuntimeException{
    public IncorrectAircraftException(){
        super();
    }

    public IncorrectAircraftException(String e){
        super(e);
    }
}