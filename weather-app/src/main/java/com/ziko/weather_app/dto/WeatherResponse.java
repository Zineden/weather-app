package com.ziko.weather_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ziko.weather_app.util.WeatherCodeInterpreter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("current")
    private CurrentWeatherDTO currentWeatherDTO;

    @JsonProperty("hourly")
    private HourlyWeatherDTO hourlyWeatherDTO;

    @JsonProperty("daily")
    private DailyWeatherDTO dailyWeatherDTO;

    public CurrentWeatherDTO getCurrentWeatherDTO() {
        return currentWeatherDTO;
    }

    public HourlyWeatherDTO getHourlyWeatherDTO() {
        return hourlyWeatherDTO;
    }

    public DailyWeatherDTO getDailyWeatherDTO() {
        return dailyWeatherDTO;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentWeatherDTO {
        @JsonProperty("temperature_2m")
        private double temperature;
        @JsonProperty("apparent_temperature")
        private double apparentTemperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("is_day")
        private boolean isDay;
        @JsonProperty("relative_humidity_2m")
        private int humidity;
        @JsonProperty("precipitation")
        private double precipitation;
        @JsonProperty("wind_speed_10m")
        private double windSpeed;
        @JsonProperty("wind_direction_10m")
        private int windDirection;

        public double getTemperature() {
            return temperature;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public int getWeatherCode() {
            return weatherCode;
        }

        public boolean getIsDay() {
            return isDay;
        }

        public int getHumidity() {
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

        public String getDescription() {
            return WeatherCodeInterpreter.interpret(weatherCode);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HourlyWeatherDTO {
        @JsonProperty("time")
        private List<String> times;
        @JsonProperty("temperature_2m")
        private List<Double> temperatures;
        @JsonProperty("apparent_temperature")
        private List<Double> apparentTemperatures;
        @JsonProperty("weather_code")
        private List<Integer> weatherCodes;
        @JsonProperty("relative_humidity_2m")
        private List<Integer> humidities;
        @JsonProperty("precipitation_probability")
        private List<Integer> precipitationProbabilities;
        @JsonProperty("precipitation")
        private List<Double> precipitationAmounts;
        @JsonProperty("wind_speed_10m")
        private List<Double> windSpeeds;
        @JsonProperty("wind_direction_10m")
        private List<Integer> windDirections;
        @JsonProperty("uv_index")
        private List<Double> uvIndexes;
        @JsonProperty("is_day")
        private List<Integer> isDayList;

        public List<String> getTimes() {
            return times;
        }

        public List<Double> getTemperatures() {
            return temperatures;
        }

        public List<Double> getApparentTemperatures() {
            return apparentTemperatures;
        }

        public List<Integer> getWeatherCodes() {
            return weatherCodes;
        }

        public List<Integer> getHumidities() {
            return humidities;
        }

        public List<Integer> getPrecipitationProbabilities() {
            return precipitationProbabilities;
        }

        public List<Double> getPrecipitationAmounts() {
            return precipitationAmounts;
        }

        public List<Double> getWindSpeeds() {
            return windSpeeds;
        }

        public List<Integer> getWindDirections() {
            return windDirections;
        }

        public List<Double> getUvIndexes() {
            return uvIndexes;
        }

        public List<Integer> getIsDayList() {
            return isDayList;
        }

        public List<String> getDescriptions() {
            return weatherCodes.stream().map(WeatherCodeInterpreter::interpret).collect(Collectors.toList());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyWeatherDTO {
        @JsonProperty("time")
        private List<String> times;
        @JsonProperty("temperature_2m_max")
        private List<Double> maxTemperatures;
        @JsonProperty("temperature_2m_min")
        private List<Double> minTemperatures;
        @JsonProperty("weather_code")
        private List<Integer> weatherCodes;
        @JsonProperty("precipitation_sum")
        private List<Double> precipitationAmounts;
        @JsonProperty("sunrise")
        private List<String> sunriseTimes;
        @JsonProperty("sunset")
        private List<String> sunsetTimes;

        public List<String> getTimes() {
            return times;
        }

        public List<Double> getMaxTemperatures() {
            return maxTemperatures;
        }

        public List<Double> getMinTemperatures() {
            return minTemperatures;
        }

        public List<Integer> getWeatherCodes() {
            return weatherCodes;
        }

        public List<Double> getPrecipitationAmounts() {
            return precipitationAmounts;
        }

        public List<String> getSunriseTimes() {
            return sunriseTimes;
        }

        public List<String> getSunsetTimes() {
            return sunsetTimes;
        }

        public List<String> getDescriptions() {
            return weatherCodes.stream().map(WeatherCodeInterpreter::interpret).collect(Collectors.toList());
        }
    }
}
