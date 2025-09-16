package hr.java.tasks.weather.weatherin2.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<WeatherData> weatherData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }
}
