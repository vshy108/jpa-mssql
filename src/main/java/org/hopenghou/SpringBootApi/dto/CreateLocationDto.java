package org.hopenghou.SpringBootApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

public class CreateLocationDto {
    private String name;
    private GeoPoint center;
    private GeoPoint southWest;
    private GeoPoint northEast;
    private boolean isFavourite;

    // Constructors
    public CreateLocationDto() {
    }

    public CreateLocationDto(String name, GeoPoint center, GeoPoint southWest, GeoPoint northEast, boolean isFavourite) {
        this.name = name;
        this.center = center;
        this.southWest = southWest;
        this.northEast = northEast;
        this.isFavourite = isFavourite;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getCenter() {
        return center;
    }

    public void setCenter(GeoPoint center) {
        this.center = center;
    }

    public GeoPoint getSouthWest() {
        return southWest;
    }

    public void setSouthWest(GeoPoint southWest) {
        this.southWest = southWest;
    }

    public GeoPoint getNorthEast() {
        return northEast;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public void setNorthEast(GeoPoint northEast) {
        this.northEast = northEast;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    // Nested GeoPoint class
    public static class GeoPoint {
        private double lat;
        private double lng;

        public GeoPoint() {
        }

        public GeoPoint(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        @JsonProperty("lat")
        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        @JsonProperty("lng")
        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}