package com.example.nowfeed;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nowfeed.model.InstagramMediaPOJO;
import com.example.nowfeed.model.NYTimesPOJO;
import com.example.nowfeed.model.TwitterPOJO;
import com.example.nowfeed.network.InstagramAPIs;

import java.util.ArrayList;
import java.util.List;

import com.example.nowfeed.model.Weather;
import com.example.nowfeed.model.WeatherRespond;
import com.example.nowfeed.network.InstagramService;
import com.example.nowfeed.network.NYTimesService;
import com.example.nowfeed.network.TwitterService;
import com.example.nowfeed.network.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ViewGroup.OnClickListener{

 List<Object> mCardsData= new ArrayList<>();
    Button refresh;
    private static final String TAG= "MainActivity";
    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION="NEWYORK,USA";

RecyclerView recyclerView;
    public static Drawable mWeatherIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardsData.add("Mila's Notes");
        InstagramAPI();
        WeatherAPI();
    }

    public void InstagramAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InstagramService igService = retrofit.create(InstagramService.class);
        Call<InstagramMediaPOJO> getRecentMedia = igService.getRecentMedia();
        getRecentMedia.enqueue(new Callback<InstagramMediaPOJO>() {
            @Override
            public void onResponse(Call<InstagramMediaPOJO> call, Response<InstagramMediaPOJO> response) {
                InstagramMediaPOJO igMedia = response.body();
                mCardsData.add(igMedia);
                Log.d("igMedia1", response.body().getData().get(0).getImages().getlow_resolution().getUrl());
            }
            @Override
            public void onFailure(Call<InstagramMediaPOJO> call, Throwable t) {}
        });
    }

    public void TwitterAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitterService igService = retrofit.create(TwitterService.class);
        Call<TwitterPOJO> getRecentMedia = igService.();
        getRecentMedia.enqueue(new Callback<TwitterPOJO>() {
            @Override
            public void onResponse(Call<TwitterPOJO> call, Response<TwitterPOJO> response) {

            }
            @Override
            public void onFailure(Call<TwitterPOJO> call, Throwable t) {}
        });
    }

    public void NYTAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NYTimesService igService = retrofit.create(NYTimesService.class);
        Call<NYTimesPOJO> getRecentMedia = igService.();
        getRecentMedia.enqueue(new Callback<NYTimesPOJO>() {
            @Override
            public void onResponse(Call<NYTimesPOJO> call, Response<NYTimesPOJO> response) {

            }
            @Override
            public void onFailure(Call<NYTimesPOJO> call, Throwable t) {}
        });
    }

    public void WeatherAPI(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        final WeatherApi weatherApi=retrofit.create(WeatherApi.class);
        Call<WeatherRespond> call= weatherApi.fetchWeather(LOCATION,API_KEY);
        call.enqueue(new Callback<WeatherRespond>() {
            @Override
            public void onResponse(Call<WeatherRespond> call, Response<WeatherRespond> response) {
                if(response.isSuccessful()){

                    WeatherRespond weatherRespond = response.body();
                    List<Weather> weather=   weatherRespond.getWeather();

                    Log.d(TAG,weather.get(0).getDescription());
                    Log.d(TAG,weather.get(0).getMain());
                    Log.d(TAG,weather.get(0).getIcon());
                    mCardsData.add(weatherRespond);
                    Log.d("mCardsData Size", "" + mCardsData.size());
                    initializeRecView();
                }
            }
            @Override
            public void onFailure(Call<WeatherRespond> call, Throwable t) {}
        });
    }

    public void initializeRecView(){
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData));
    }

    @Override
    public void onClick(View view) {}
}
