package com.example.nowfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.example.nowfeed.model.Weather;
import com.example.nowfeed.model.WeatherRespond;

/**
 * Created by Millochka on 10/31/16.
 */

public class SecondCardViewHolder extends RecyclerView.ViewHolder {


    TextView mTitle;
    TextView mDescription;
    TextView mCity;
    ImageView mIcon;
    View mView;
    TextView mTemp;
    TextView mPress;
    TextView mHum;
    TextView mWindSpeed;

    public SecondCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));

       mView=itemView;
       //mTitle=(TextView)mView.findViewById(R.id.weather_title);
       mDescription=(TextView)mView.findViewById(R.id.weather_discription);
        mIcon=(ImageView) mView.findViewById(R.id.weather_icon);
        mCity=(TextView) mView.findViewById(R.id.cityText);
        mTemp=(TextView)mView.findViewById(R.id.temp);
        mPress=(TextView)mView.findViewById(R.id.press);
        mHum=(TextView)mView.findViewById(R.id.hum);
        mWindSpeed=(TextView)mView.findViewById(R.id.windSpeed);

    }


    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        return inflater.inflate(R.layout.second_card, parent,false);

    }

    protected  void onBind(WeatherRespond weatherRespond){

        Weather weather = weatherRespond.getWeather().get(0);
        mDescription.setText(weather.getMain()+"("+weather.getDescription()+")");
        WeatherRespond.Sys system= weatherRespond.getSys();
        mCity.setText(weatherRespond.getName()+ ","+system.getCountry());
        Picasso.with(mView.getContext()).load("http://openweathermap.org/img/w/"+weather.getIcon()+".png").resize(170,170).centerCrop().into(mIcon);
        WeatherRespond.WeatherMain weatherMain= weatherRespond.getMain();
        WeatherRespond.Wind wind = weatherRespond.getWind();
        double temperature = Math.round(1.8*(weatherMain.getTemp()-273)+32);
        mTemp.setText(Double.toString(temperature)+" ºF");
        mPress.setText(Double.toString(weatherMain.getPressure())+" hPa");
        mHum.setText(Double.toString(weatherMain.getHumidity())+" %");
        mWindSpeed.setText(wind.getSpeed()+" mps" + " "+ wind.getDeg()+"º");





    }
}
