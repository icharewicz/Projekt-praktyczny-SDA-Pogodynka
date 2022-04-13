package sda.weather.exceptions;

public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super(message);
    }
}
