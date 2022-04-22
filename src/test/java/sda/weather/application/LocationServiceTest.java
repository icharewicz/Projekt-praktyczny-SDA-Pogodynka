package sda.weather.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sda.weather.exceptions.WrongDataException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    private final LocationService locationService = new LocationService();

    @Test
    void addSomeNewLocationShouldReturnNewLocation(){
        //given
        //when
        Location result = locationService.addSomeNewLocation("London",
        "20", "10", "LondonCity", "United Kingdom");
        //then
        assertEquals(result.getLocationName(), "London");
        assertEquals(result.getLongitude(), "20");
        assertEquals(result.getLatitude(), "10");
        assertEquals(result.getRegion(), "LondonCity");
        assertEquals(result.getCountryName(), "United Kingdom");
    }

    @Test
    void addSomeNewLocationShouldThrowExceptionWhenLocationNameIsNull(){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation(null,
        "20", "1", "NullCity", "United Kingdom"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
     void addSomeNewLocationShouldThrowExceptionWhenLocationNameIsEmpty(String input){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation(input,
                "30", "3", "EmptyCity", "United Kingdom"));
    }

    @Test
    void addSomeNewLocationShouldThrowExceptionWhenLongitudeIsNull(){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("New York",
                null, "74", "Mid-Atlantic", "United States"));
    }

    @Test
    void addSomeNewLocationShouldThrowExceptionWhenLongitudeIsLessThanMinus180(){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("New York",
                "-190", "70", "Mid-Atlantic", "United States"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void addSomeNewLocationShouldThrowExceptionWhenLatitudeIsEmpty(String input){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("Manchester",
                "53", input, "North West England", "United Kingdom"));
    }

    @Test
    void addSomeNewLocationShouldThrowExceptionWhenLatitudeIsAbove90(){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("Manchester",
                "53", "100", "North West England", "United Kingdom"));
    }

    @Test
    void addSomeNewLocationShouldThrowExceptionWhenCountryNameIsNull(){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("Vancouver",
                "49", "50", "Metro Vancouver", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void addSomeNewLocationShouldThrowExceptionWhenCountryNameIsEmpty(String input){
        //given, when, then
        assertThrows(WrongDataException.class, () -> locationService.addSomeNewLocation("Vancouver",
                "49", "50", "Metro Vancouver", input));
    }

    @Test
    void addSomeNewLocationShouldReturnNewLocationWhenRegionIsNull(){
        //given
        //when
        Location result = locationService.addSomeNewLocation("Ljubljana",
                "46", "14", null, "Slovenia");
        //then
        assertEquals(result.getLocationName(), "Ljubljana");
        assertEquals(result.getLongitude(), "46");
        assertEquals(result.getLatitude(), "14");
        assertEquals(result.getRegion(), " ");
        assertEquals(result.getCountryName(), "Slovenia");
    }

    @Test
    void getAllLocationsShouldReturnTwoObjects(){
        //given
        Location location1 = new Location("London","20", "10", "LondonCity", "United Kingdom");
        Location location2 = new Location("New York","30", "30", "Mid-Atlantic", "United States");
        List<Location> locationList = List.of(location1, location2);
        //when
        List<Location> newLocationList = locationService.getAllLocations();
        //then
        assertThat(newLocationList).hasSize(2);
    }
}