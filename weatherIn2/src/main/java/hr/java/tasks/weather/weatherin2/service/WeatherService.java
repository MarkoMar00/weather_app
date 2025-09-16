package hr.java.tasks.weather.weatherin2.service;

import hr.java.tasks.weather.weatherin2.domain.WeatherData;

import java.util.Optional;

public interface WeatherService {
    Optional<WeatherData> fetchAndSaveCityData(String city);
}
