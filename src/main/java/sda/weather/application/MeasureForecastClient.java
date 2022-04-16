package sda.weather.application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

public class MeasureForecastClient {

    private final String API_KEY = "02c4df71a1696055615492bab981102c";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MeasureForecastClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //jeśli w czasie deserializacji danych pobranych z serwisu
        //okaże się, że nie są pobrane wszystkie, czyli 1 do 1,
        //to wtedy poleciałby exceptios, a ten zapis, powoduje,
        //że exception nie poleci
        objectMapper.findAndRegisterModules();
    }

//    public MeasureResponse.ListItem getWeather(String cityName, int daysToAdd) {
//        // todo K -> C
//        String uri = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric", cityName, API_KEY);
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create(uri))
//                .build();
//        try {
//            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//            String responseBody = httpResponse.body();
//
//            MeasureResponse weatherResponse = objectMapper.readValue(responseBody, MeasureResponse.class);
//            List<MeasureResponse.ListItem> list = weatherResponse.getList();
//
//            return list.stream()
//                    .filter(dt -> dt.getDt_txt().equals(LocalDate.now().plusDays(daysToAdd) + " 12:00:00"))
//                    .findFirst()
//                    .orElseThrow(() -> new BadParametersGainFromUserException("Błędne parametry podane przez użytkownika"));
//        } catch (Exception e) {
//            System.out.println("Nieudana próba połączenia z serwisem: " + e.getMessage());
//            throw new BadGatawayException("Nieudana próba połączenia z serwisem, 502");
//        }
//    }

}
