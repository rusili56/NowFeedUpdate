package com.example.nowfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity implements ViewGroup.OnClickListener{

 List<Object> mCardsData= new ArrayList<>();
    Button refresh;
    private static final String TAG= "MainActivity";
    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION="NEWYORK,USA";

RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardsData.add("Mila's Notes");



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
                    mCardsData.add(weather.get(0));
                    mCardsData.add(new Instagram());
                    initializeRecView();


                }

            }

            @Override
            public void onFailure(Call<WeatherRespond> call, Throwable t) {

            }
        });



    }

    public void initializeRecView(){
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData));

    }


    @Override
    public void onClick(View view) {


    }
}
