package com.example.nowfeed;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.nowfeed.model.Forecast;
import com.example.nowfeed.model.ForecastFiveDays;
import com.example.nowfeed.model.Instagram;
import com.example.nowfeed.network.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ViewGroup.OnClickListener{

 List<Object> mCardsData= new ArrayList<>();
    private static final String TAG= "MainActivity";
    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION="NEWYORK,USA";
    public Retrofit mRetrofit;
    WeatherApi mWeatherApi;

    public static void setFragment(boolean fragment) {
        isFragment = fragment;
    }

    public static boolean isFragment=false;

    RecyclerView recyclerView;
    public static Drawable mWeatherIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mCardsData.add("Mila's Notes");



       mRetrofit=new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        mWeatherApi=mRetrofit.create(WeatherApi.class);
        Call<ForecastFiveDays> call= mWeatherApi.fetchFiveDays(LOCATION,API_KEY);
        call.enqueue(new Callback<ForecastFiveDays>() {
            @Override
            public void onResponse(Call<ForecastFiveDays> call, Response<ForecastFiveDays> response) {
                if(response.isSuccessful()){


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

    public void initializeRecView(){
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData,getFragmentManager()));
//        if(isFragment){
//            getFragmentManager().beginTransaction().add(R.id.weather_CV,new WeatherFragment(),TAG).commit();
//
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);


    }


    @Override
    public void onClick(View view) {


    }
}
