package com.ziko.weather_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ziko.weather_app.model.CurrentWeather;
import com.ziko.weather_app.model.Location;

public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather, Long> {
    Optional<CurrentWeather> findTopByLocationOrderByTimestampDesc(Location location);

    void deleteByLocationId(Long locationId);
}
