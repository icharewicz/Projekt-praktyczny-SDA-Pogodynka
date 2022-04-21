package sda.weather.application;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//tworzenie tabeli dla wpisu lokalizacji
@Entity
@Data //geter, seter, konstruktor ze wszystk.arg, toString, equals, hashcode
@NoArgsConstructor //konstruktor bezargumentowy
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "location_id")
    private Integer id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "location_longitude")
    private String longitude;

    @Column(name = "location_latitude")
    private String latitude;

    @Column(name = "location_region")
    private String region;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    public Location(String locationName, String longitude, String latitude, String region, String countryName) {
        this.locationName = locationName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.countryName = countryName;
    }

}
