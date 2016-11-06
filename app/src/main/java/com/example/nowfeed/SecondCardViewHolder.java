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

    public SecondCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));

       mView=itemView;
       //mTitle=(TextView)mView.findViewById(R.id.weather_title);
       mDescription=(TextView)mView.findViewById(R.id.weather_discription);
        mIcon=(ImageView) mView.findViewById(R.id.weather_icon);
        mCity=(TextView) mView.findViewById(R.id.cityText);
        mTemp=(TextView)mView.findViewById(R.id.temp);

    }


    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        return inflater.inflate(R.layout.second_card, parent,false);

    }

    protected  void onBind(WeatherRespond weatherRespond){

        Weather weather = weatherRespond.getWeather().get(0);
        //mTitle.setText(weather.getMain());
        mDescription.setText(weather.getMain()+"("+weather.getDescription()+")");
        mCity.setText(weatherRespond.getName()+",United States of America");
        Picasso.with(mView.getContext()).load("http://openweathermap.org/img/w/"+weather.getIcon()+".png").resize(200,200).centerCrop().into(mIcon);
        //mIcon.setImageDrawable(mWeatherIcon);
        WeatherRespond.WeatherMain weatherMain= weatherRespond.getMain();
        mTemp.setText(Double.toString(weatherMain.getTemp()));




    }
}
