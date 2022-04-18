package sda.weather.application;

public class MeasureResponseMapper extends MeasureForecastClient {

    //klasa do mapowania zawartości odpowiedzi z serwisu, wydostajemy wartości z klas
    //zewnętrznych z MeasureResponse

    public static Measure mapToMeasureWithCityAndCnt(MeasureResponse.ListItem listItem){

        String windDirection = "";
        double windDegree = listItem.list.getDeg();

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

        //double temp = listItem.temp.getDay();
        double temperature = listItem.list.temp.getDay();
        double pressure = listItem.list.getPressure();
        double humidity = listItem.list.getHumidity();
        double speed = listItem.list.getSpeed();
        String city = listItem.city.getName();
        Integer cnt = listItem.getCnt();


        return new Measure(temperature, pressure, humidity, windDirection, speed, city, cnt);
    }

    //bez miasta i cnt
//    public static Measure mapToMeasure(MeasureResponse.ListItem listItem) {
//        String windDirection = "";
//
//        double windDegree = listItem.getDeg();
//        //wind direction: North N 0 - 23 and 337 - 360; Northeast NE 24 - 68;
//        // East E 69 - 113; Southeast SE 114 - 158; South S 159 - 203;
//        //Southwest SW 204 - 248; West W 249 - 293; Northwest NW 294 - 336
//        if (windDegree >= 24 && windDegree < 68) {
//            windDirection = "NE";
//        } else if (windDegree >= 69 && windDegree < 113) {
//            windDirection = "E";
//        } else if (windDegree >= 114 && windDegree < 158) {
//            windDirection = "SE";
//        } else if (windDegree >= 159 && windDegree < 203) {
//            windDirection = "S";
//        } else if (windDegree >= 204 && windDegree < 248) {
//            windDirection = "SW";
//        } else if (windDegree >= 249 && windDegree < 293) {
//            windDirection = "W";
//        } else if (windDegree >= 294 && windDegree < 336) {
//            windDirection = "E";
//        } else windDirection = "N";
//
//        //double temp = listItem.temp.getDay();
//        //double temperature = temp.getDay();
//        double pressure = listItem.getPressure();
//        double humidity = listItem.getHumidity();
//        double speed = listItem.getSpeed();
//
//        return new Measure(10, pressure, humidity, windDirection, speed);
//    }

}
