package hr.java.tasks.weather.weatherin2.controller;

import hr.java.tasks.weather.weatherin2.domain.WeatherData;
import hr.java.tasks.weather.weatherin2.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WeatherController {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @GetMapping("/")
    public String getTemperature(Model model) {
        List<WeatherData> weatherDataList = weatherDataRepository.findLatestWeatherData();
        List<String> cityWeatherInfo = new ArrayList<>();

        for (WeatherData weatherData : weatherDataList) {
                cityWeatherInfo.add("The temperature in " + weatherData.getCity().getName() + " is " + weatherData.getTemperature()
                        + " and the humidity is " + weatherData.getHumidity());
        }
        model.addAttribute("cities", cityWeatherInfo);

        return "index";
    }
}
