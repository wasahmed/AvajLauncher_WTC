package launcher.avaj.vehicles;

import launcher.avaj.Aircraft;
import launcher.avaj.Coordinates;
import launcher.avaj.Flyable;
import launcher.avaj.WeatherProvider;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherProvider weatherProvider;

    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower() {

    }
}
