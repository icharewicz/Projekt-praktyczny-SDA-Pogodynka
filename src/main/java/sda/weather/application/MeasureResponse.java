package sda.weather.application;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class MeasureResponse {

    City city;
    Integer cnt; //liczba dni do przodu
    List<ListRequest> list = new ArrayList<>(); //zagnieżdżona lista

    //jak zwrócoć listę MeasureResponse ?
    public List<MeasureResponse> getMeasureResponses(){
        return new ArrayList<>();
    }

    @Data
    public static class City {
        String name;
    }

    @Data
    public static class ListRequest {
        Temp temp;
        Double pressure;
        Double humidity;
        Double speed; //wind speed
        Double deg; //Wind direction, degrees (meteorological)

        //zagnieżdżenie Temp -> biorę temeraturę  w dzień
        @Data
        public static class Temp {
            Double day;
        }
    }
}
