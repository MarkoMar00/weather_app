package hr.java.tasks.weather.weatherin2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherIn2Application {

    public static void main(String[] args) {
        SpringApplication.run(WeatherIn2Application.class, args);
    }

}
