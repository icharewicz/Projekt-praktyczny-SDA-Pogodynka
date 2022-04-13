package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//klasa łączy obiekt ObjectMapper z LocationService
public class LocationController {

    private final LocationService locationService = new LocationService();
    private final ObjectMapper objectMapper = new ObjectMapper(); // z com.fasterxml.jackson.databind.ObjectMapper

    //metoda dla dodawania nowej lokalizacji do bazy danych -> ciąg dalszy z WeatherClient
    public String addNewLocation(String locationName, String longitude, String latitude, String region, String countryName){
        Location location = locationService.addSomeNewLocation(locationName, longitude, latitude, region, countryName);
        try {
            return objectMapper.writeValueAsString(location);  //domyśna metoda z jackson
        } catch (JsonProcessingException e){
            throw new InternalServerException("InternalServerException");
        }
    }

    //metoda do odczytu listy lokalizacji -> ciąg dalszy z WeatherClient
    public String getAllLocations() {
        List<Location> locationList = locationService.readAllLocations();
        try {
            return objectMapper.writeValueAsString(locationList);
        } catch (JsonProcessingException e){
            throw new InternalServerException("InternalServerException");
        }
    }

}
