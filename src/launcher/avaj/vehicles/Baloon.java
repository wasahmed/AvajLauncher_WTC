package launcher.avaj.vehicles;

import launcher.avaj.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        int updatedLongitude = this.coordinates.getLongitude();
        int updatedLatitude = this.coordinates.getLatitude();
        int updatedHeight = this.coordinates.getHeight();

        switch (this.weatherTower.getWeather(this.coordinates)){
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -5);
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -3);
                break;
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -15);
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
