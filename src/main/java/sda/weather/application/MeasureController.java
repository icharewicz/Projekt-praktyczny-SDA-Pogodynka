package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sda.weather.exceptions.InternalServerException;

//klasa do mapowań measure z danymi z serwisu, używa ObjectMapper
public class MeasureController {

    private final MeasureService measureService = new MeasureService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String returnMeasureValuesWithCityAndCnt(String cityName, int daysToAdd){
        Measure measure = measureService.createMeasureWithCityAndCnt(cityName, daysToAdd);
        try {
            return objectMapper.writeValueAsString(measure);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("HTTP status code 500: Internal Server Error");
        }
    }

}
