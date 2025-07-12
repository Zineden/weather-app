package com.ziko.weather_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ziko.weather_app.dto.WeatherDataResponse;
import com.ziko.weather_app.model.*;
import com.ziko.weather_app.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/fetch")
    public ResponseEntity<Void> fetchWeather(@RequestBody Location location) {
        try {
            weatherService.fetchWeather(location);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/all")
    public ResponseEntity<WeatherDataResponse> getAllWeather(@RequestBody Location location) {
        CurrentWeather current = weatherService.getCurrentWeather(location);
        List<DailyWeather> daily = weatherService.getDailyWeather(location);
        List<HourlyWeather> hourly = weatherService.getHourlyWeather(location);

        if (current == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Current weather data not available");
        }
        if (daily == null || daily.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Daily weather data not available");
        }
        if (hourly == null || hourly.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hourly weather data not available");
        }

        WeatherDataResponse response = new WeatherDataResponse(current, daily, hourly);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/current")
    public ResponseEntity<CurrentWeather> getCurrent(@RequestBody Location location) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(location));
    }

    @PostMapping("/daily")
    public ResponseEntity<List<DailyWeather>> getDaily(@RequestBody Location location) {
        return ResponseEntity.ok(weatherService.getDailyWeather(location));
    }

    @PostMapping("/hourly")
    public ResponseEntity<List<HourlyWeather>> getHourly(@RequestBody Location location) {
        return ResponseEntity.ok(weatherService.getHourlyWeather(location));
    }
}
