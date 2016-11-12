package network;

import model.Forecast;
import model.ForecastFiveDays;
import model.WeatherRespond;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Millochka on 11/4/16.
 */

public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<WeatherRespond> fetchWeather(@Query("q") String location, @Query("APPID") String apiKey);

    @GET("data/2.5/forecast/daily")
    Call<ForecastFiveDays> fetchFiveDays(@Query("q") String location, @Query("APPID") String apiKey);

    @GET("data/2.5/forecast")
    Call<Forecast> fetchForcast(@Query("q") String location, @Query("APPID") String apiKey);
}
