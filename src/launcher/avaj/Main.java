package launcher.avaj;

import launcher.avaj.vehicles.AircraftFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        try{
            if (args.length > 1)
                throw new Exception("only takes one argument");
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line != null){
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                String[] sims = line.split(" ");
                if (sims.length != 1)
                    throw new Exception("invalid 1st line");
                if (simulations < 0){
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null){
                    String[] count = line.split(" ");
                    if (count.length != 5)
                        throw new Exception("invalid scenario file");
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables){
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++){
                    weatherTower.changeWeather();
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Couldn't find file " + args[0]);
        }catch (IOException e){
            System.out.println("There was an error while reading the file " + args[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Specify scenario file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
