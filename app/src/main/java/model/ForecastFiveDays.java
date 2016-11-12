package model;

import java.util.List;

/**
 * Created by Millochka on 11/7/16.
 */

public class ForecastFiveDays {

    City city;
    String cod;
    float message;
    int cnt;
    List<Forecast> list;

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public float getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Forecast> getList() {
        return list;
    }
}
