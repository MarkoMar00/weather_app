package hr.java.tasks.weather.weatherin2.repository;

import hr.java.tasks.weather.weatherin2.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    @Query(value = """
        SELECT w.* 
        FROM weather w
        INNER JOIN (
            SELECT city_id, MAX(fetched_at) AS max_date
            FROM weather
            GROUP BY city_id
        ) latest
        ON w.city_id = latest.city_id AND w.fetched_at = latest.max_date
        """, nativeQuery = true)
    List<WeatherData> findLatestWeatherData();
}
