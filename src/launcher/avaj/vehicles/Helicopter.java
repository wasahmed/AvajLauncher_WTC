package launcher.avaj.vehicles;

import launcher.avaj.*;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        SimGenerator simGenerator = new SimGenerator();
        int updatedLongitude = this.coordinates.getLongitude();
        int updatedLatitude = this.coordinates.getLatitude();
        int updatedHeight = this.coordinates.getHeight();

        switch (this.weatherTower.getWeather(this.coordinates)){
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                simGenerator.writeMessage("HELICOPTER#" + this.name + "(" + this.id + ")" + " its a rainy day");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                simGenerator.writeMessage("HELICOPTER#" + this.name + "(" + this.id + ")" + " its a foggy day");
                break;
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10 , coordinates.getLatitude(), coordinates.getHeight() + 2);
                simGenerator.writeMessage("HELICOPTER#" + this.name + "(" + this.id + ")" + " its a sunny day");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                simGenerator.writeMessage("HELICOPTER#" + this.name + "(" + this.id + ")" + " its a snowy day");
                break;
        }
        if (updatedHeight > 100)
            this.coordinates = new Coordinates(updatedLongitude,updatedLatitude, 100);

        if (updatedHeight <= 0){
            simGenerator.writeMessage("Tower says: HELICOPTER#" + this.name + "(" + this.id + ")" + " landed");
            simGenerator.writeMessage("Tower says: HELICOPTER#" + this.name + "(" + this.id + ")" + " UNREGISTERED");
            this.weatherTower.unregister(this);
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        SimGenerator simGenerator = new SimGenerator();
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        simGenerator.writeMessage("Tower says: HELICOPTER#" + this.name + "(" + this.id + ")" + " REGISTERED");
    }
}
