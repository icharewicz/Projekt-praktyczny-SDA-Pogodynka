package sda.weather.user;

import sda.weather.application.LocationController;
import sda.weather.application.Measure;
import sda.weather.application.MeasureRepository;

import java.util.Scanner;

public class WeatherUser {

    private final LocationController locationController = new LocationController();
    //private final WeatherController weatherController = new WeatherController();
    private final Scanner scanner = new Scanner(System.in);

    //metoda którą uruchamiamy w WeatherApplication
    public void runUserInterface() {

        boolean isRunning = true;

        do {
            System.out.println("Witaj w aplikacji pogodowej! Wybierz, co chcesz zrobic: ");

            System.out.println("Żeby dodać nową lokalizację do sprawdzenia wybierz -> 1");
            System.out.println("Żeby wyświetlić listę dodanych lokalizacji wybierz -> 2");
            System.out.println("Żeby sprawdzić pogodę w wybranej lokalizacji wybierz -> 3");
            System.out.println("Żeby wyjść z aplikacji wybierz -> 4");

            //pobieramy opcję od użytkownika
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> addLocation(); //metoda dla dodawania nowej lokalizacji do bazy danych, jest w main
                case 2 -> getListOfLocations(); //metoda do odczytu listy lokalizacji
                //case 3 -> getWeather(); //pobieranie danych pogodowych
                case 4 -> { System.out.println("Zamknięcie aplikacji..."); isRunning = false;}
                default -> System.out.println("Nie rozpoznano opcji");
            }

        } while (isRunning);
    }

    //metoda dla dodawania nowej lokalizacji do bazy danych
    //Użytkownik powinien móc dodawać lokalizację do bazy danych wprowadzając poniższe wartości: id,
    //długość i szerokość geograficzna, region, nazwa kraju
    private void addLocation(){
        scanner.nextLine();
        System.out.println("Podaj nazwę miasta: ");
        String locationName = scanner.nextLine();

        System.out.println("Podaj długość geograficzną jako liczbę całkowitą w zakresie od -180(W) do 180(E):");
        String longitude = scanner.nextLine();
        System.out.println("Podaj szerokość geograficzną jako liczbę całkowitą w zakresie od -90(S) do 90(N):");
        String latitude = scanner.nextLine();
        System.out.print("Podaj region: ");
        String region = scanner.nextLine();
        System.out.print("Podaj nazwę kraju: ");
        String countryName = scanner.nextLine();
        String result = locationController.addNewLocation(locationName, longitude, latitude, region, countryName); //obiekt LocationController, metoda dodaje lokalizacje
        System.out.println("Nowa lokalizacja: " + result);
       //wszystkie zmienne String bo obiekt JSON ObjectMapper pracuje ze stringami
    }

    //metoda do odczytu listy lokalizacji
    private void getListOfLocations(){
        String result = locationController.getAllLocations(); //obiekt LocationController, metoda pobiera wszystkie lokalizacje
        //result = result.replaceAll() //kasowanie znaków, sprawdzić jak bez
        System.out.println("Lista lokalizacji: " + result);
    }




    //pobieranie danych pogodowych
//    private void getWeather(){
//        System.out.println("Podaj nazwe Miasta: ");
//        String cityName = scanner.nextLine();
//        System.out.println("Na ile dni naprzód chcesz sprawdzić pogodę (maksymalnie 30 dni): ");
//        int numberOfDays = scanner.nextInt();
//        if (numberOfDays <= 0 || numberOfDays > 30){
//            System.out.println("Podałeś liczbę dni poza zakresem.");
//        } else {
//            //dodać funkconalność z datą!
//            String result = weatherController.getWeatherValuse(cityName, numberOfDays); //obiekt WeatherController, metoda która pobiera dane o temp.
//            System.out.println("Prognoza pogody w " + cityName + " to: " + result);
//        }
//    }

    //wąsy main
}
