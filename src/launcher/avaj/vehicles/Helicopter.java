package launcher.avaj.vehicles;

import launcher.avaj.*;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        int updatedLongitude = this.coordinates.getLongitude();
        int updatedLatitude = this.coordinates.getLatitude();
        int updatedHeight = this.coordinates.getHeight();

        switch (this.weatherTower.getWeather(this.coordinates)){
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10 , coordinates.getLatitude(), coordinates.getHeight() + 2);
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                break;
        }
        if (updatedHeight > 100)
            this.coordinates = new Coordinates(updatedLongitude,updatedLatitude, 100);
    }

    @Override
    public void registerTower(WeatherProvider weatherProvider) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
