package com.example.nowfeed.model;

/**
 * Created by Millochka on 11/7/16.
 */

public class City {
    int id;
    String name;
    WeatherRespond.Coordinate coord;
    String country;
    int population;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WeatherRespond.Coordinate getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
