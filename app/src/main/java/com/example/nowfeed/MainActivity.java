package com.example.nowfeed;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.nowfeed.RecyclerView.CardAdapter;
import com.example.nowfeed.RecyclerView.InstagramFragment;
import com.example.nowfeed.model.BestSellersPOJO;
import com.example.nowfeed.model.InstagramMediaPOJO;
import com.example.nowfeed.model.TopStoriesPOJO;
import com.example.nowfeed.model.Weather;
import com.example.nowfeed.model.WeatherRespond;
import com.example.nowfeed.network.InstagramService;
import com.example.nowfeed.network.NYTimesService;
import com.example.nowfeed.network.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    InstagramFragment instafrag = new InstagramFragment();

    List<Object> mCardsData = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION = "NEWYORK,USA";
    int instaPosition;

    RecyclerView recyclerView;

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
        Call<InstagramMediaPOJO> getRecentMedia = igService.getRecentMedia();
        getRecentMedia.enqueue(new Callback<InstagramMediaPOJO>() {
            @Override
            public void onResponse(Call<InstagramMediaPOJO> call, Response<InstagramMediaPOJO> response) {
                if (response.isSuccessful()) {
                    InstagramMediaPOJO igMedia = response.body();
                    mCardsData.add(igMedia);
                    instaPosition = mCardsData.size() - 1;
                    Log.d("igMedia1", response.body().getData().get(0).getImages().getlow_resolution().getUrl());
                }
            }

            @Override
            public void onFailure(Call<InstagramMediaPOJO> call, Throwable t) {
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
        Call<BestSellersPOJO> getRecentMedia = igService.getBestSellers();
        getRecentMedia.enqueue(new Callback<BestSellersPOJO>() {
            @Override
            public void onResponse(Call<BestSellersPOJO> call, Response<BestSellersPOJO> response) {
                if (response.isSuccessful()) {
                    BestSellersPOJO NYTBestSellers = response.body();
                    Log.d("NYT", response.body().getResults().get(0).getTitle());
                    mCardsData.add(NYTBestSellers);
                }
            }

            @Override
            public void onFailure(Call<BestSellersPOJO> call, Throwable t) {
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
        Call<TopStoriesPOJO> getRecentMedia = igService.getTopStories();
        getRecentMedia.enqueue(new Callback<TopStoriesPOJO>() {

            @Override
            public void onResponse(Call<TopStoriesPOJO> call, Response<TopStoriesPOJO> response) {
                if (response.isSuccessful()) {
                    TopStoriesPOJO NYTTopStories = response.body();
                    Log.d("NYT", response.body().getResults().get(0).getTitle());
                    mCardsData.add(NYTTopStories);
                }
            }

            @Override
            public void onFailure(Call<TopStoriesPOJO> call, Throwable t) {
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
            public void onResponse(Call<WeatherRespond> call, Response<WeatherRespond> response) {
                if (response.isSuccessful()) {

                    WeatherRespond weatherRespond = response.body();
                    List<Weather> weather = weatherRespond.getWeather();

                    Log.d(TAG, weather.get(0).getDescription());
                    Log.d(TAG, weather.get(0).getMain());
                    Log.d(TAG, weather.get(0).getIcon());
                    mCardsData.add(weatherRespond);
                    Log.d("mCardsData Size", "" + mCardsData.size());
                }
            }

            @Override
            public void onFailure(Call<WeatherRespond> call, Throwable t) {
            }
        });
    }

    public void initializeRecView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData, this));
    }

    public void onClickRemoveFrag(View view) {
        FragmentManager fm = CardAdapter.getFragMan();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(CardAdapter.getInstaFrag()).commit();
    }

}
