package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sda.weather.exceptions.InternalServerException;

public class MeasureService {


//    private final MeasureForecastClient measureForecastClient = new MeasureForecastClient();
//    private final MeasureResponseMapper measureResponseMapper = new MeasureResponseMapper();
//    private final MeasureRepository measureRepository = new MeasureRepository();
//
//    Measure createMeasure(String cityName, Integer daysToAdd) {
//        MeasureResponse.ListItem weatherList = measureForecastClient.getWeather(cityName,daysToAdd);
//        Measure weather = measureResponseMapper.mapToWeather(weatherList);
//        return measureRepository.saveNewWeatherForecast(weather);
//    }


//    private final MeasureRepository measureRepository = new MeasureRepository();
//
//    private final WeatherService weatherService = new WeatherService();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public String getWeatherValues(String cityName, Integer daysToAdd) {
//        Weather weather = weatherService.getWeather(cityName, daysToAdd);
//        try {
//            return objectMapper.writeValueAsString(weather);
//        } catch (JsonProcessingException e) {
//            throw new InternalServerException("HTTP 500 internal server error");
//        }
//    }

}
