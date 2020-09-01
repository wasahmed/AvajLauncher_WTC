package launcher.avaj.vehicles;

import launcher.avaj.*;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates){
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
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                simGenerator.writeMessage("Jetplane#" + this.name + "(" + this.id + ")" + " its a icy day");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                simGenerator.writeMessage("Jetplane#" + this.name + "(" + this.id + ")" + " its a icy day");
                break;
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10 , coordinates.getLatitude(), coordinates.getHeight() + 2);
                simGenerator.writeMessage("Jetplane#" + this.name + "(" + this.id + ")" + " its a icy day");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                simGenerator.writeMessage("Jetplane#" + this.name + "(" + this.id + ")" + " its a icy day");
                break;
        }
        if (coordinates.getHeight() > 100)
            this.coordinates = new Coordinates(coordinates.getLongitude(),coordinates.getLatitude(), 100);

        if (coordinates.getHeight() <= 0){
            simGenerator.writeMessage("Tower says: Jetplane#" + this.name + "(" + this.id + ")" + " landed");
            simGenerator.writeMessage("Tower says: Jetplane#" + this.name + "(" + this.id + ")" + " UNREGISTERED");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        SimGenerator simGenerator = new SimGenerator();
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        simGenerator.writeMessage("Tower says : Jetplane#" + this.name + "(" + this.id + ")" + " REGISTERED");
    }
}
