package com.ziko.weather_app.util;

import java.util.Map;

public class WeatherCodeInterpreter {
    private static final Map<Integer, String> WEATHER_CODES = Map.ofEntries(
            Map.entry(0, "Clear Sky"),
            Map.entry(1, "Mainly Clear"),
            Map.entry(2, "Partly Cloudy"),
            Map.entry(3, "Overcast"),
            Map.entry(45, "Fog"),
            Map.entry(48, "Depositing Rime Fog"),
            Map.entry(51, "Light Drizzle"),
            Map.entry(53, "Moderate Drizzle"),
            Map.entry(55, "Dense Drizzle"),
            Map.entry(56, "Light Freezing Drizzle"),
            Map.entry(57, "Dense Freezing Drizzle"),
            Map.entry(61, "Slight Rain"),
            Map.entry(63, "Moderate Rain"),
            Map.entry(65, "Heavy Rain"),
            Map.entry(66, "Light Freezing Rain"),
            Map.entry(67, "Heavy Freezing Rain"),
            Map.entry(71, "Slight Snowfall"),
            Map.entry(73, "Moderate Snowfall"),
            Map.entry(75, "Heavy Snowfall"),
            Map.entry(77, "Snow Grains"),
            Map.entry(80, "Slight Rain Showers"),
            Map.entry(81, "Moderate Rain Showers"),
            Map.entry(82, "Violent Rain Showers"),
            Map.entry(85, "Slight Snow Showers"),
            Map.entry(86, "Heavy Snow Showers"),
            Map.entry(95, "Slight or Moderate Thunderstorm"),
            Map.entry(96, "Thunderstorm with Slight Hail"),
            Map.entry(99, "Thunderstorm with Heavy Hail"));

    public static String interpret(int weatherCode) {
        return WEATHER_CODES.getOrDefault(weatherCode, "Unknown Weather");
    }
}
