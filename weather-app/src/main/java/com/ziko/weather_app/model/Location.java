package com.ziko.weather_app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
    private String timezone;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private CurrentWeather currentWeather;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyWeather> dailyWeather;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourlyWeather> hourlyWeather;

    public Location() {
    }

    public Location(String city, String country, double latitude, double longitude, String admin1, String admin2,
            String admin3, String admin4, String timezone) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.admin1 = admin1;
        this.admin2 = admin2;
        this.admin3 = admin3;
        this.admin4 = admin4;
        this.timezone = timezone;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAdmin1() {
        return admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public String getAdmin4() {
        return admin4;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    public void setAdmin4(String admin4) {
        this.admin4 = admin4;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
