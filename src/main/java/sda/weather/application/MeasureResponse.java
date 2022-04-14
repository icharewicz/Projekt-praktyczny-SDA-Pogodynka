package sda.weather.application;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeasureResponse {

    List<ListItem> list = new ArrayList<>();

    @Data
    public static class ListItem{

        Main main; //klasa wewnętrzna - abby odwzorować zagnieżdżenia JSONa,
        // możemy to również zrobić w osobnych klasach
        Wind wind; //klasa wewnętrzna
        String dt_txt;

        @Data
        public static class Main{
            double temp;
            double pressure;
            double humidity;
        }

        @Data
        public static class Wind{
            double speed;
            double deg;
        }
    }
}
