package sda.weather.application;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeasureResponse {

    List<ListItem> listItem = new ArrayList<>();

    @Data
    public static class ListItem {

        List list;
        String city;
        Integer cnt; //liczba dni do przodu

        @Data
        public static class List{
           // String dt;
            Temp temp;
            double pressure;
            double humidity;
            double speed; //wind speed
            double deg; //Wind direction, degrees (meteorological)

            //zagnieżdżenie Temp -> biorę temeraturę  w dzień
            @Data
            public static class Temp {
                double day;
            }
        }
    }
}
