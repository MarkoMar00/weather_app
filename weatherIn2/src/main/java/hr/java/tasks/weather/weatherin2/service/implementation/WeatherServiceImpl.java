package hr.java.tasks.weather.weatherin2.service.implementation;

import hr.java.tasks.weather.weatherin2.domain.City;
import hr.java.tasks.weather.weatherin2.domain.WeatherData;
import hr.java.tasks.weather.weatherin2.domain.WeatherDataDTO;
import hr.java.tasks.weather.weatherin2.repository.CityRepository;
import hr.java.tasks.weather.weatherin2.repository.WeatherDataRepository;
import hr.java.tasks.weather.weatherin2.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;


@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherDataRepository weatherDataRepository;
    private CityRepository cityRepository;

    @Value("${openweather.api.key}")
    private String apiKey;
    @Value("${openweather.api.url}")
    private String apiUrl;

    public WeatherServiceImpl(WeatherDataRepository weatherDataRepository, CityRepository cityRepository) {
        this.weatherDataRepository = weatherDataRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<WeatherData> fetchAndSaveCityData(String city) {

        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherDataDTO weatherDataDTO = restTemplate.getForObject(url, WeatherDataDTO.class);

            Optional<City> cityDB = cityRepository.findByName(city);
            if (cityDB.isEmpty()) {
                City newCity = new City();
                newCity.setName(city);
                cityRepository.save(newCity);
                cityDB = cityRepository.findByName(city);
            }

            WeatherData weatherData = mapFromDto(weatherDataDTO);
            weatherData.setFetchedAt(Instant.now());
            weatherData.setCity(cityDB.get());
            weatherDataRepository.save(weatherData);

            return Optional.of(weatherData);
        } catch (HttpClientErrorException e) {
            System.err.println("Caught client error: " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Caught server error: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Caught unexpected exception: " + e);
        }
        return Optional.empty();
    }

    private WeatherData mapFromDto(WeatherDataDTO weatherDataDTO) {
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature(weatherDataDTO.getMain().getTemp());
        weatherData.setHumidity(weatherDataDTO.getMain().getHumidity());

        return weatherData;
    }
}


