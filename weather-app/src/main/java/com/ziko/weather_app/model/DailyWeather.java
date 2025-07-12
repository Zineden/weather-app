package com.ziko.weather_app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class DailyWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate time;
    private double maxTemperature;
    private double minTemperature;
    private double precipitationAmount;
    private String sunrise;
    private String sunset;
    private int weatherCode;
    private String description;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public DailyWeather() {
    }

    public DailyWeather(LocalDate time, int weatherCode, String description, double maxTemperature,
            double minTemperature, double precipitationAmount,
            String sunrise, String sunset, LocalDateTime timestamp, Location location) {
        this.time = time;
        this.weatherCode = weatherCode;
        this.description = description;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.precipitationAmount = precipitationAmount;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.timestamp = timestamp;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getTime() {
        return time;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Double getPrecipitationAmount() {
        return precipitationAmount;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setPrecipitationAmoun(Double precipitationAmount) {
        this.precipitationAmount = precipitationAmount;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
