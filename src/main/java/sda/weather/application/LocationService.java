package sda.weather.application;

import sda.weather.exceptions.WrongDataException;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository = new LocationRepository();

    //dodaje lokację używająć klasy Location
    public Location addSomeNewLocation(String locationName, String longitude, String latitude, String region, String countryName){

        if (locationName == null || locationName.isBlank()) {
            throw new WrongDataException("Nazwa lokalizacji musi być uzupełniona!");
        }
        if (longitude == null || longitude.isBlank()) {
            throw new WrongDataException("Wartość długości geograficznej musi być uzupełniona!");
        }
        if (Integer.parseInt(longitude) < -180 || (Integer.parseInt(longitude) > 180)) {
            throw new WrongDataException("Proszę podać wartość długości z zakresu: od -90 do 90");
        }
        if (latitude == null || latitude.isBlank()) {
            throw new WrongDataException("Wartość szerokości geograficznej musi być uzupełniona!");
        }
        if (Integer.parseInt(latitude) < -90 || (Integer.parseInt(latitude) > 90)) {
            throw new WrongDataException("Proszę podać wartość szerokości z zakresu: od -90 do 90");
        }
        if (countryName == null || countryName.isBlank()) {
            throw new WrongDataException("Nazwa kraju musi być uzupełniona!");
        }

        Location location = new Location(locationName, longitude, latitude, region, countryName);
        Location entryNewLocation = locationRepository.createNewLocation(location);

        return entryNewLocation;
    }

    public List<Location> getAllLocations() {
        return locationRepository.getAllLocations();
    }
}


