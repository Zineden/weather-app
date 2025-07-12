package com.ziko.weather_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class CurrentWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int weatherCode;
    private String description;
    private double temperature;
    private double apparentTemperature;
    private boolean isDay;
    private double humidity;
    private double precipitation;
    private double windSpeed;
    private int windDirection;
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public CurrentWeather() {
    }

    public CurrentWeather(int weatherCode, String description, double temperature,
            double apparentTemperature, boolean isDay, double humidity, double precipitation, double windSpeed,
            int windDirection, LocalDateTime timestamp, Location location) {
        this.weatherCode = weatherCode;
        this.description = description;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.isDay = isDay;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.timestamp = timestamp;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public boolean getIsDay() {
        return isDay;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
