package launcher.avaj.vehicles;

import launcher.avaj.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates){
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
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -5);
                simGenerator.writeMessage("Baloon#" + this.name + "(" + this.id + ")" + " its a rainy day");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -3);
                simGenerator.writeMessage("Baloon#" + this.name + "(" + this.id + ")" + " its a foggy day");
                break;
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                simGenerator.writeMessage("Baloon#" + this.name + "(" + this.id + ")" + " its a sunny day");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() -15);
                simGenerator.writeMessage("Baloon#" + this.name + "(" + this.id + ")" + " its a icy day");
                break;
        }
        if (this.coordinates.getHeight() > 100)
            this.coordinates = new Coordinates(coordinates.getLongitude(),coordinates.getLatitude(), 100);

        if (this.coordinates.getHeight() <= 0){
            simGenerator.writeMessage("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " landed");
            simGenerator.writeMessage("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " UNREGISTERED");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        SimGenerator simGenerator = new SimGenerator();
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        simGenerator.writeMessage("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " REGISTERED");
    }
}
