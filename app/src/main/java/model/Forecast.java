package model;

import java.util.List;

/**
 * Created by Millochka on 11/7/16.
 */
public class Forecast {

    int dt;
    Temp temp;
    List<Weather> weather;
    WeatherRespond.Wind wind;
    double pressure;
    double humidity;

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public int getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }



    public List<Weather> getWeather() {
        return weather;
    }

    public WeatherRespond.Wind getWind() {
        return wind;
    }



}
