package com.ziko.weather_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziko.weather_app.dto.GeoCodingResponse;
import com.ziko.weather_app.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/search")
    public GeoCodingResponse searchCities(@RequestParam String prefix) {
        return cityService.searchCities(prefix);
    }

}
