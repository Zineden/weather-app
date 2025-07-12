package com.ziko.weather_app.dto;

import java.util.List;

import com.ziko.weather_app.model.CurrentWeather;
import com.ziko.weather_app.model.DailyWeather;
import com.ziko.weather_app.model.HourlyWeather;

public class WeatherDataResponse {
    private CurrentWeather currentWeather;
    private List<DailyWeather> dailyWeatherList;
    private List<HourlyWeather> hourlyWeatherList;

    public WeatherDataResponse() {
    }

    public WeatherDataResponse(CurrentWeather currentWeather, List<DailyWeather> dailyWeatherList,
            List<HourlyWeather> hourlyWeatherList) {
        this.currentWeather = currentWeather;
        this.dailyWeatherList = dailyWeatherList;
        this.hourlyWeatherList = hourlyWeatherList;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public List<DailyWeather> getDailyWeatherList() {
        return dailyWeatherList;
    }

    public void setDailyWeatherList(List<DailyWeather> dailyWeatherList) {
        this.dailyWeatherList = dailyWeatherList;
    }

    public List<HourlyWeather> getHourlyWeatherList() {
        return hourlyWeatherList;
    }

    public void setHourlyWeatherList(List<HourlyWeather> hourlyWeatherList) {
        this.hourlyWeatherList = hourlyWeatherList;
    }
}
