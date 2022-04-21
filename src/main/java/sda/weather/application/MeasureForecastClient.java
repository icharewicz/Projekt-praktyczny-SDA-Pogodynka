package sda.weather.application;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import sda.weather.exceptions.InternalServerException;
import sda.weather.exceptions.WrongDataException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public MeasureResponse getMeasureWithCity(String cityName, int daysToAdd){
        String uri = String.format("https://api.openweathermap.org/data/2.5/forecast/daily?q=%s&cnt=%d&appid=%s&units=metric", cityName, daysToAdd, API_KEY);
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            MeasureResponse measureResponse = objectMapper.readValue(responseBody, MeasureResponse.class);
//           MeasureResponse measureResponseList = objectMapper.readValue(responseBody, MeasureResponse.class);
      //      List<MeasureResponse> measureResponseList = measureResponse.getMeasureResponses();
            //return measureResponseList.stream().findFirst().get();
            //return listItems.stream().findFirst().orElseThrow(() -> new WrongDataException("Błędne dane w getMeasureWithCity"));

            return measureResponse;

        } catch (Exception e) {
            throw new InternalServerException("Nieudana próba połączenia z serwisem, 502: " + e.getMessage());
        }
    }

}
