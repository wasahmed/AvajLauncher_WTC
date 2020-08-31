package launcher.avaj;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        if (weatherProvider == null){
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        Random randomWeather = new Random();
        int i = randomWeather.nextInt(4);
        return weather[i];
    }
}
