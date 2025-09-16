package hr.java.tasks.weather.weatherin2.scheduling;

import hr.java.tasks.weather.weatherin2.domain.WeatherData;
import hr.java.tasks.weather.weatherin2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class WeatherScheduler {

    @Autowired
    private WeatherService weatherService;
    @Value("${openweather.cities}")
    private String cities;

    @Scheduled(fixedRate = 3600000)
    public void fetchWeatherDataScheduled() {
        fetchWeatherData();
    }

    public void fetchWeatherData() {
        List<String> citiesList = Arrays.stream(cities.split(","))
                .map(String::trim)
                .toList();
        Optional<WeatherData> weatherData;

        for (String city : citiesList) {
            weatherData = weatherService.fetchAndSaveCityData(city);
            if (weatherData.isEmpty()) {
                System.err.println("Failed to get weather data for: " + city);
            } else {
                System.out.println("The temperature in " + city + " is " + weatherData.get().getTemperature()
                        + " and the humidity is " + weatherData.get().getHumidity());
            }
        }
    }
}
