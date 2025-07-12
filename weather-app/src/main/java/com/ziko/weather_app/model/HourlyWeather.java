package com.ziko.weather_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class HourlyWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime time;
    private double temperature;
    private double apparentTemperature;
    private int weatherCode;
    private String description;
    private int humidity;
    private double precipitationAmount;
    private int precipitationProbability;
    private double windSpeed;
    private int windDirection;
    private double uvIndex;
    private boolean isDay;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public HourlyWeather() {
    }

    public HourlyWeather(LocalDateTime time, int weatherCode, String description, double temperature,
            double apparentTemperature, int humidity, double precipitationAmount, int precipitationProbability,
            double windSpeed, int windDirection, double uvIndex, boolean isDay, LocalDateTime timestamp,
            Location location) {
        this.time = time;
        this.weatherCode = weatherCode;
        this.description = description;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.precipitationAmount = precipitationAmount;
        this.precipitationProbability = precipitationProbability;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.uvIndex = uvIndex;
        this.isDay = isDay;
        this.timestamp = timestamp;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public int getHumidity() {
        return humidity;
    }

    public Double getPrecipitationAmount() {
        return precipitationAmount;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public boolean getIsDay() {
        return isDay;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setPrecipitationAmount(Double precipitationAmount) {
        this.precipitationAmount = precipitationAmount;
    }

    public void setPrecipitationProbability(int precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
