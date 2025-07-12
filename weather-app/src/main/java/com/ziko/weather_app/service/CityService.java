package com.ziko.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.ziko.weather_app.dto.GeoCodingResponse;

@Service
public class CityService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.geo.api.url}")
    private String geoApiUrl;

    public GeoCodingResponse searchCities(String namePrefix) {
        try {
            String url = UriComponentsBuilder.fromUriString(geoApiUrl).queryParam("name", namePrefix)
                    .queryParam("count", 10).queryParam("language", "en").queryParam("format", "json").build()
                    .toUriString();

            return restTemplate.getForObject(url, GeoCodingResponse.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to fetch cities from Open-Meteo", e);
        }
    }
}
