package sda.weather;

import sda.weather.client.WeatherClient;

public class WeatherApplication {

    public static void main (String[] args){

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.runClientInterface();
    }
}
