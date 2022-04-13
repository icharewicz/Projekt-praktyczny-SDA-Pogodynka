package sda.weather;

import sda.weather.user.WeatherUser;

public class WeatherApplication {

    public static void main (String[] args){

        WeatherUser weatherUser = new WeatherUser();
        weatherUser.runUserInterface();
    }
}
