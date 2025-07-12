package com.ziko.weather_app.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ziko.weather_app.model.HourlyWeather;
import com.ziko.weather_app.model.Location;

public interface HourlyWeatherRepository extends JpaRepository<HourlyWeather, Long> {
    List<HourlyWeather> findByLocationAndTimestampAfterOrderByTime(Location location, LocalDateTime timestamp);

    Optional<HourlyWeather> findTopByLocationOrderByTimestampDesc(Location location);

    void deleteByLocationId(Long locationId);
}
