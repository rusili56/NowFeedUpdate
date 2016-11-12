package com.example.nowfeed;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.nowfeed.model.Forecast;
import com.example.nowfeed.model.ForecastFiveDays;
import com.example.nowfeed.model.Instagram;
import com.example.nowfeed.network.WeatherApi;

import com.example.nowfeed.model.BestSeller;
import com.example.nowfeed.model.Instagram;
import com.example.nowfeed.model.TopStory;
import com.example.nowfeed.model.Weather;
import com.example.nowfeed.model.WeatherRespond;
import com.example.nowfeed.network.InstagramService;
import com.example.nowfeed.network.NYTimesService;
import com.example.nowfeed.network.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import model.Instagram;
import model.Weather;
import model.WeatherRespond;
import network.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    InstagramFragment instafrag = new InstagramFragment();

 List<Object> mCardsData= new ArrayList<>();
    private static final String TAG= "MainActivity";
    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION="NEWYORK,USA";
    public Retrofit mRetrofit;
    WeatherApi mWeatherApi;

    RecyclerView recyclerView;
    public static Drawable mWeatherIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopStoriesAPI();
        BestSellersAPI();
        mCardsData.add("Mila's Notes");
        InstagramAPI();
        WeatherAPI();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initializeRecView();
            }
        }, 4000);
    }

    public void InstagramAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InstagramService igService = retrofit.create(InstagramService.class);
        Call<Instagram> getRecentMedia = igService.getRecentMedia();
        getRecentMedia.enqueue(new Callback<Instagram>() {
            @Override
            public void onResponse(Call<Instagram> call, Response<Instagram> response) {
                if (response.isSuccessful()) {
                    Instagram igMedia = response.body();
                    mCardsData.add(igMedia);
                    instaPosition = mCardsData.size() - 1;
                    Log.d("igMedia1", response.body().getData().get(0).getImages().getlow_resolution().getUrl());
                }
            }

       mRetrofit=new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        mWeatherApi=mRetrofit.create(WeatherApi.class);
        Call<ForecastFiveDays> call= mWeatherApi.fetchFiveDays(LOCATION,API_KEY);
        call.enqueue(new Callback<ForecastFiveDays>() {
            @Override
            public void onFailure(Call<Instagram> call, Throwable t) {
                Log.d("Instagram", "failure");
            }
        });
    }

    public void BestSellersAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NYTimesService igService = retrofit.create(NYTimesService.class);
        Call<BestSeller> getRecentMedia = igService.getBestSellers();
        getRecentMedia.enqueue(new Callback<BestSeller>() {
            @Override
            public void onResponse(Call<BestSeller> call, Response<BestSeller> response) {
                if (response.isSuccessful()) {
                    BestSeller NYTBestSellers = response.body();
                    Log.d("NYT", response.body().getResults().get(0).getTitle());
                    mCardsData.add(NYTBestSellers);
                }
            }

            @Override
            public void onFailure(Call<BestSeller> call, Throwable t) {
                Log.d("NYT", "failure: " + t.toString());
            }
        });
    }

    public void TopStoriesAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NYTimesService igService = retrofit.create(NYTimesService.class);
        Call<TopStory> getRecentMedia = igService.getTopStories();
        getRecentMedia.enqueue(new Callback<TopStory>() {

            @Override
            public void onResponse(Call<TopStory> call, Response<TopStory> response) {
                if (response.isSuccessful()) {
                    TopStory NYTTopStories = response.body();
                    Log.d("NYT", response.body().getResults().get(0).getTitle());
                    mCardsData.add(NYTTopStories);
                }
            }

            @Override
            public void onFailure(Call<TopStory> call, Throwable t) {
                Log.d("NYT", "failure: " + t.toString());
            }
        });
    }

    public void WeatherAPI() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherRespond> call = weatherApi.fetchWeather(LOCATION, API_KEY);
        call.enqueue(new Callback<WeatherRespond>() {
            @Override
            public void onResponse(Call<ForecastFiveDays> call, Response<ForecastFiveDays> response) {
                if(response.isSuccessful()){

                    WeatherRespond weatherRespond = response.body();
                    List<Weather> weather=   weatherRespond.getWeather();

                    ForecastFiveDays weatherRespond = response.body();
                    List<Forecast> weatherForcast =  weatherRespond.getList();
                    mCardsData.add(weatherRespond);
                    mCardsData.add(new Instagram());
                    initializeRecView();

                }

            }

            @Override
            public void onFailure(Call<ForecastFiveDays> call, Throwable t) {

            }
        });



    }

    public void initializeRecView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData, this, getFragmentManager()));

    }

    public void onClickRemoveFrag(View view) {
        FragmentManager fm = CardAdapter.getFragMan();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(CardAdapter.getInstaFrag()).commit();
    }

    }


    @Override
    public void onClick(View view) {


    }
}
