package hr.java.tasks.weather.weatherin2.repository;

import hr.java.tasks.weather.weatherin2.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
