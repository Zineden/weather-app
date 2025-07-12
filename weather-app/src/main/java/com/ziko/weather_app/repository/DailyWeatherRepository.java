package com.ziko.weather_app.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ziko.weather_app.model.DailyWeather;
import com.ziko.weather_app.model.Location;

public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Long> {
    List<DailyWeather> findByLocationAndTimestampAfterOrderByTime(Location location, LocalDateTime timestamp);

    Optional<DailyWeather> findTopByLocationOrderByTimestampDesc(Location location);

    void deleteByLocationId(Long locationId);
}
