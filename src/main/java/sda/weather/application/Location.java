package sda.weather.application;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//tworzenie tabeli dla wpisu lokalizacji
@Entity
@Data //geter, seter, konstruktor ze wszystk.arg, toString, equals, hashcode
@NoArgsConstructor //konstruktor bezargumentowy
public class Location {

    @Id
    @GeneratedValue()

}
