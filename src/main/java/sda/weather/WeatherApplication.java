package sda.weather;

import sda.weather.application.Location;
import sda.weather.application.LocationDao;
import sda.weather.user.WeatherUser;

public class WeatherApplication {

    public static void main (String[] args){

//        WeatherUser weatherUser = new WeatherUser();
//        weatherUser.runUserInterface();

        LocationDao locationDao = new LocationDao();
        Location location1 = new Location("Warszawa", "52", "21", "Mazowieckie", "Polska");
        Location location2 = new Location("Wałbrzych", "50", "16", "Dolnośląskie", "Polska");
        Location location3 = new Location("Mińsk", "53", "27", "Mińskaja", "Białoruś");

        locationDao.add(location1);
        locationDao.add(location2);
        locationDao.add(location3);

        System.out.println(locationDao.returnAllRecords());

    }
}
