package sda.weather.application;

public class MeasureResponseMapper extends MeasureForecastClient {

    //klasa do mapowania zawartości odpowiedzi z serwisu, wydostajemy wartości z klas
    //zewnętrznych z MeasureResponse

    public static Measure mapToMeasureWithCityAndCnt(MeasureResponse measureResponse){

        String windDirection = "";
        double windDegree = measureResponse.getList().get(0).getDeg();

        if (windDegree >= 24 && windDegree < 68) {
            windDirection = "NE";
        } else if (windDegree >= 69 && windDegree < 113) {
            windDirection = "E";
        } else if (windDegree >= 114 && windDegree < 158) {
            windDirection = "SE";
        } else if (windDegree >= 159 && windDegree < 203) {
            windDirection = "S";
        } else if (windDegree >= 204 && windDegree < 248) {
            windDirection = "SW";
        } else if (windDegree >= 249 && windDegree < 293) {
            windDirection = "W";
        } else if (windDegree >= 294 && windDegree < 336) {
            windDirection = "E";
        } else windDirection = "N";

        double temperature = measureResponse.getList().get(0).getTemp().getDay();
        double pressure = measureResponse.getList().get(0).getPressure();
        double humidity = measureResponse.getList().get(0).getHumidity();
        double speed = measureResponse.getList().get(0).getSpeed();
        String city = measureResponse.getCity().getName();
        Integer cnt = measureResponse.getCnt();

        return new Measure(temperature, pressure, humidity, windDirection, speed, city, cnt);
    }
}
