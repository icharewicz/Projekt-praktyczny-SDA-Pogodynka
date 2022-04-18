package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sda.weather.exceptions.InternalServerException;

public class MeasureService {

    //klasa łączy mapowane dane z serwisu

    private final MeasureForecastClient measureForecastClient = new MeasureForecastClient();
    private final MeasureResponseMapper measureResponseMapper = new MeasureResponseMapper();
    private final MeasureDao measureDao = new MeasureDao();

    Measure createMeasureWithCityAndCnt(String cityName, Integer daysToAdd){
        MeasureResponse.ListItem measureList  = measureForecastClient.getMeasureWithCity(cityName, daysToAdd);
        Measure measure = measureResponseMapper.mapToMeasureWithCityAndCnt(measureList);
        return measureDao.add(measure);
    }

    //old version without city
//    Measure createMeasureWithoutCity(String cityName, Integer daysToAdd) {
//        MeasureResponse.ListItem measureList = measureForecastClient.getMeasureForecastClient(cityName,daysToAdd);
//        Measure measure = measureResponseMapper.mapToMeasure(measureList);
//        return measureDao.add(measure);
//    }

}
