package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sda.weather.exceptions.InternalServerException;

import java.util.List;

//klasa łączy obiekt ObjectMapper z LocationService
public class LocationController {

    private final LocationService locationService = new LocationService();
    private final ObjectMapper objectMapper = new ObjectMapper(); // z com.fasterxml.jackson.databind.ObjectMapper

    //metoda dla dodawania nowej lokalizacji do bazy danych -> ciąg dalszy z WeatherUser
    public String addNewLocation(String locationName, String longitude, String latitude, String region, String countryName){
        Location location = locationService.addSomeNewLocation(locationName, longitude, latitude, region, countryName);
        try {
            return objectMapper.writeValueAsString(location);  //domyślna metoda z jackson
        } catch (JsonProcessingException e){
            throw new InternalServerException("HTTP status code 500: Internal Server Error");
        }
    }

    //metoda do odczytu listy lokalizacji -> ciąg dalszy z WeatherUser
    public String getAllLocations() {
        List<Location> locationList = locationService.getAllLocations();
        try {
            return objectMapper.writeValueAsString(locationList);
        } catch (JsonProcessingException e){
            throw new InternalServerException("HTTP status code 500: Internal Server Error");
        }
    }

}
