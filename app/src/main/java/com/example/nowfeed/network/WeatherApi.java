package com.example.nowfeed.network;

import com.example.nowfeed.model.WeatherRespond;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Millochka on 11/4/16.
 */

public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<WeatherRespond> fetchWeather(@Query("q") String location, @Query("APPID") String apiKey);
}
