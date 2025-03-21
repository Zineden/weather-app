package com.ziko.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
