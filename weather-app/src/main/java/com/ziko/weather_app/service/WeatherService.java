package com.ziko.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ziko.specification.LocationSpecifications;
import com.ziko.weather_app.dto.WeatherResponse;
import com.ziko.weather_app.dto.WeatherResponse.CurrentWeatherDTO;
import com.ziko.weather_app.dto.WeatherResponse.DailyWeatherDTO;
import com.ziko.weather_app.dto.WeatherResponse.HourlyWeatherDTO;
import com.ziko.weather_app.model.*;
import com.ziko.weather_app.repository.*;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WeatherService {
    private final LocationRepository locationRepo;
    private final CurrentWeatherRepository currentRepo;
    private final HourlyWeatherRepository hourlyRepo;
    private final DailyWeatherRepository dailyRepo;
    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.params.current}")
    private String weatherCurrentParams;

    @Value("${weather.api.params.hourly}")
    private String weatherHourlyParams;

    @Value("${weather.api.params.daily}")
    private String weatherDailyParams;

    @Value("${weather.geo.api.url}")
    private String geoApiUrl;

    @Value("${weather.geo.api.params}")
    private String geoApiParams;

    public WeatherService(LocationRepository locationRepo, CurrentWeatherRepository currentRepo,
            HourlyWeatherRepository hourlyRepo, DailyWeatherRepository dailyRepo) {
        this.locationRepo = locationRepo;
        this.currentRepo = currentRepo;
        this.hourlyRepo = hourlyRepo;
        this.dailyRepo = dailyRepo;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    public void fetchWeather(Location loc) {
        try {
            Location location = fetchLocation(loc);
            boolean isCurrentStale = checkCurrentStale(location);
            boolean isDailyStale = checkDailyStale(location);
            boolean isHourlyStale = checkHourlyStale(location);

            if (!isCurrentStale && !isDailyStale && !isHourlyStale) {
                return;
            }

            String apiUrl = buildWeatherApiUrl(location, isCurrentStale, isDailyStale, isHourlyStale);
            WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl, WeatherResponse.class);

            if (weatherResponse == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data unavailable");
            }

            if (isCurrentStale && weatherResponse.getCurrentWeatherDTO() != null) {
                CurrentWeatherDTO current = weatherResponse.getCurrentWeatherDTO();
                CurrentWeather existing = currentRepo.findTopByLocationOrderByTimestampDesc(location).orElse(null);
                if (existing != null) {
                    existing.setWeatherCode(current.getWeatherCode());
                    existing.setDescription(current.getDescription());
                    existing.setTemperature(current.getTemperature());
                    existing.setApparentTemperature(current.getApparentTemperature());
                    existing.setIsDay(current.getIsDay());
                    existing.setHumidity(current.getHumidity());
                    existing.setPrecipitation(current.getPrecipitation());
                    existing.setWindSpeed(current.getWindSpeed());
                    existing.setWindDirection(current.getWindDirection());
                    existing.setTimestamp(LocalDateTime.now());
                    currentRepo.save(existing);
                } else {
                    currentRepo.save(new CurrentWeather(current.getWeatherCode(), current.getDescription(),
                            current.getTemperature(), current.getApparentTemperature(), current.getIsDay(),
                            current.getHumidity(), current.getPrecipitation(), current.getWindSpeed(),
                            current.getWindDirection(), LocalDateTime.now(), location));
                }
            }

            if (isDailyStale && weatherResponse.getDailyWeatherDTO() != null) {
                dailyRepo.deleteByLocationId(location.getId());
                DailyWeatherDTO daily = weatherResponse.getDailyWeatherDTO();
                if (daily.getTimes() == null || daily.getDescriptions() == null || daily.getWeatherCodes() == null
                        || daily.getTimes().size() != daily.getWeatherCodes().size()) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Daily weather data malformed");
                }
                for (int i = 0; i < daily.getTimes().size(); i++) {
                    dailyRepo.save(new DailyWeather(LocalDate.parse(daily.getTimes().get(i)),
                            daily.getWeatherCodes().get(i), daily.getDescriptions().get(i),
                            daily.getMaxTemperatures().get(i), daily.getMinTemperatures().get(i),
                            daily.getPrecipitationAmounts().get(i), daily.getSunriseTimes().get(i),
                            daily.getSunsetTimes().get(i), LocalDateTime.now(), location));
                }
            }

            if (isHourlyStale && weatherResponse.getHourlyWeatherDTO() != null) {
                hourlyRepo.deleteByLocationId(location.getId());
                HourlyWeatherDTO hourly = weatherResponse.getHourlyWeatherDTO();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                if (hourly.getTimes() == null || hourly.getDescriptions() == null || hourly.getWeatherCodes() == null
                        || hourly.getTimes().size() != hourly.getWeatherCodes().size()) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            "Hourly weather data malformed");
                }
                for (int i = 0; i < hourly.getTimes().size(); i++) {
                    hourlyRepo.save(new HourlyWeather(LocalDateTime.parse(hourly.getTimes().get(i), formatter),
                            hourly.getWeatherCodes().get(i), hourly.getDescriptions().get(i),
                            hourly.getTemperatures().get(i), hourly.getApparentTemperatures().get(i),
                            hourly.getHumidities().get(i), hourly.getPrecipitationAmounts().get(i),
                            hourly.getPrecipitationProbabilities().get(i), hourly.getWindSpeeds().get(i),
                            hourly.getWindDirections().get(i), hourly.getUvIndexes().get(i),
                            hourly.getIsDayList().get(i) == 1, LocalDateTime.now(), location));
                }
            }
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to fetch data from API", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e);
        }
    }

    private Location fetchLocation(Location loc) {
        return locationRepo
                .findOne(LocationSpecifications.matchLocation(loc)).orElseGet(() -> locationRepo.save(loc));
    }

    private boolean checkCurrentStale(Location loc) {
        return currentRepo.findTopByLocationOrderByTimestampDesc(loc)
                .map(data -> data.getTimestamp().isBefore(LocalDateTime.now().minusHours(1))).orElse(true);
    }

    private boolean checkDailyStale(Location loc) {
        return dailyRepo.findTopByLocationOrderByTimestampDesc(loc)
                .map(data -> data.getTimestamp().isBefore(LocalDateTime.now().minusDays(1))).orElse(true);
    }

    private boolean checkHourlyStale(Location loc) {
        return hourlyRepo.findTopByLocationOrderByTimestampDesc(loc)
                .map(data -> data.getTimestamp().isBefore(LocalDateTime.now().minusHours(6))).orElse(true);
    }

    private String buildWeatherApiUrl(Location loc, boolean includeCurrent, boolean includeDaily,
            boolean includeHourly) {
        StringBuilder url = new StringBuilder(weatherApiUrl).append("?latitude=").append(loc.getLatitude())
                .append("&longitude=").append(loc.getLongitude());
        if (includeCurrent)
            url.append("&").append(weatherCurrentParams);
        if (includeHourly)
            url.append("&").append(weatherHourlyParams);
        if (includeDaily)
            url.append("&").append(weatherDailyParams);

        url.append("&").append("timezone=auto");

        return url.toString();
    }

    public CurrentWeather getCurrentWeather(Location loc) {
        Location location = fetchLocation(loc);
        return currentRepo.findTopByLocationOrderByTimestampDesc(location).orElse(null);
    }

    public List<DailyWeather> getDailyWeather(Location loc) {
        Location location = fetchLocation(loc);
        return dailyRepo.findByLocationAndTimestampAfterOrderByTime(location, LocalDateTime.now().minusDays(1));
    }

    public List<HourlyWeather> getHourlyWeather(Location loc) {
        Location location = fetchLocation(loc);
        return hourlyRepo.findByLocationAndTimestampAfterOrderByTime(location, LocalDateTime.now().minusDays(1));
    }
}