package sda.weather.application;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//klasa representująca tabelę measures dla Hibernate
@Entity
@Data
@NoArgsConstructor
@Table(name = "measures")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measures_id")
    private int id;

    @Column(name = "temperature", nullable = false)
    private double temperature;  //temperatura C

    @Column(name = "pressure", nullable = false)
    private double pressure;     //ciśnienie

    @Column(name = "humidity", nullable = false)
    private double humidity;     //wilgotność

    @Column(name = "wind_direction", nullable = false)
    private String windDirection; //kierunek wiatru

    @Column(name = "wind_speed", nullable = false)
    private double windSpeed;    //prędkość wiatru

    public Measure(double temperature, double pressure, double humidity, String windDirection, double windSpeed) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }
}
