package com.ziko.weather_app.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

public class DotenvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NonNull ConfigurableApplicationContext context) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        Map<String, Object> props = new HashMap<>();
        props.put("spring.datasource.url", dotenv.get("DB_URL"));
        props.put("spring.datasource.username", dotenv.get("DB_USERNAME"));
        props.put("spring.datasource.password", dotenv.get("DB_PASSWORD"));

        props.put("weather.api.url", dotenv.get("WEATHER_API_URL"));
        props.put("weather.api.params.current", dotenv.get("WEATHER_API_PARAMS_CURRENT"));
        props.put("weather.api.params.daily", dotenv.get("WEATHER_API_PARAMS_DAILY"));
        props.put("weather.api.params.hourly", dotenv.get("WEATHER_API_PARAMS_HOURLY"));
        props.put("weather.geo.api.url", dotenv.get("WEATHER_GEO_API_URL"));
        props.put("weather.geo.api.params", dotenv.get("WEATHER_GEO_API_PARAMS"));

        PropertySource<?> propertySource = new MapPropertySource("dotenv", props);
        context.getEnvironment().getPropertySources().addFirst(propertySource);
    }
}
