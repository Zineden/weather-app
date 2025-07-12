package com.ziko.weather_app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodingResponse {
    @JsonProperty("results")
    public List<Location> results;

    public List<Location> getResults() {
        return results;
    }

    public void setResults(List<Location> results) {
        this.results = results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {

        @JsonProperty("name")
        public String city;
        @JsonProperty("country")
        public String country;
        @JsonProperty("latitude")
        private double latitude;
        @JsonProperty("longitude")
        private double longitude;

        private String timezone;

        private String admin1;
        private String admin2;
        private String admin3;
        private String admin4;

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getTimezone() {
            return timezone;
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
    }
}
