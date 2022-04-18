package sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sda.weather.exceptions.InternalServerException;
import sda.weather.exceptions.WrongDataException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

public class MeasureForecastClient {

    //f82e59dc93a8b7f9e70b58a25535f590 // moj: 02c4df71a1696055615492bab981102c
    private final String API_KEY = "f82e59dc93a8b7f9e70b58a25535f590";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MeasureForecastClient() {
        //jeśli w czasie deserializacji danych pobranych z serwisu
        //okaże się, że nie są pobrane wszystkie, czyli 1 do 1,
        //to wtedy poleciałby exceptios, a ten zapis, powoduje,
        //że exception nie poleci
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public MeasureResponse.ListItem getMeasureWithCity(String cityName, int daysToAdd){
        String uri = String.format("https://api.openweathermap.org/data/2.5/forecast/daily?q=%s&cnt=%d&appid=%s&units=metric", cityName, daysToAdd, API_KEY);
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            MeasureResponse measureResponse = objectMapper.readValue(responseBody, MeasureResponse.class);
            List<MeasureResponse.ListItem> listItems = measureResponse.getListItem();

            return listItems.stream().findFirst().orElseThrow(() -> new WrongDataException("Błędne dane w getMeasureWithCity"));

        } catch (Exception e) {
            throw new InternalServerException("Nieudana próba połączenia z serwisem, 502: " + e.getMessage());
        }

    }

    //old version without city
//    public MeasureResponse.ListItem getMeasureForecastClient(String cityName, int daysToAdd) {
//        // For temperature in Celsius and wind speed in meter/sec, use units=metric
//        String uri = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric", cityName, API_KEY);
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create(uri))
//                .build();
//        try {
//            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//            String responseBody = httpResponse.body();
//
//            MeasureResponse measureResponse = objectMapper.readValue(responseBody, MeasureResponse.class);
//            List<MeasureResponse.ListItem> list = measureResponse.getList();
//
//            return list.stream()
//                    .filter(dt -> dt.getDt_txt().equals(LocalDate.now().plusDays(daysToAdd) + " 12:00:00"))
//                    .findFirst()
//                    .orElseThrow(() -> new WrongDataException("Błędne dane podane przez użytkownika"));
//        } catch (Exception e) {
//            throw new InternalServerException("Nieudana próba połączenia z serwisem, 502: " + e.getMessage());
//        }
//    }

}
