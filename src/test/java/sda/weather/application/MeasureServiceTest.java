package sda.weather.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasureServiceTest {

    MeasureService measureService = new MeasureService();

    @Test
    void createMeasureWithCityAndCntShouldReturnMeasureWhenCityIsLondonAndCntTwo(){
        //when
        Measure result = measureService.createMeasureWithCityAndCnt("London", 2);
        //then
        assertTrue(result.getTemperature() > -90);
        assertFalse(result.getPressure() > 1083);
        assertTrue(result.getHumidity() > 0);
        assertTrue(result.getHumidity() > 0);
    }

    @Test
    void createMeasureWithCityAndCntShouldThrowExceptionWhenCityIsEmpty() {
        assertThrows(Exception.class, () -> measureService.createMeasureWithCityAndCnt(" ", 1));
    }

}