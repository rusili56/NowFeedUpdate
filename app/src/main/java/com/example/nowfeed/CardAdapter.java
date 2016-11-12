package com.example.nowfeed;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.City;
import model.Forecast;
import model.ForecastFiveDays;
import model.Instagram;
import model.Weather;

import static android.content.ContentValues.TAG;


/**
 * Created by Millochka on 10/30/16.
 */
public class CardAdapter extends RecyclerView.Adapter implements ViewGroup.OnClickListener {

    private List<Object> items;

    private final int  INSTAGRAM = 0,WEATHER = 1,NOTES=2 ;

    FragmentManager fragmentManager;
    private final String WDESCRIPTION = "WDESCRIPTION";
    private final String  WCITY = "WCITY";
    private final String WICON = "WICON";
    private final String WPRESSURE= "WPRESSURE";
    private final String WHUMIDITY = "WHUMIDITY";
    private final String WTEMP="WTEMP";


    public CardAdapter(List<Object> items, FragmentManager fragmentManager) {
        this.fragmentManager=fragmentManager;
        this.items=items;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case INSTAGRAM:
                viewHolder = new FirstCardViewHolder(parent);
                break;
            case WEATHER:
                viewHolder = new SecondCardViewHolder(parent);
                break;
            default:
                viewHolder = new ThirdCardViewHolder(parent);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)) {
            case INSTAGRAM:
                FirstCardViewHolder firstCard= (FirstCardViewHolder) holder;

                firstCard.onBind((Instagram) items.get(position));
                break;
            case WEATHER:

                SecondCardViewHolder secondCard = (SecondCardViewHolder) holder;
                secondCard.onBind((ForecastFiveDays) items.get(position));

                initOnClick(secondCard);
                onClick(secondCard.mView);

                break;
            default:
                ThirdCardViewHolder thirdCard = (ThirdCardViewHolder) holder;
                thirdCard.onBind((String)items.get(position));

                break;

        }


    }

        @Override
        public int getItemCount () {
            return items.size();
        }


        @Override
        public int getItemViewType ( int position){
            if (items.get(position) instanceof Instagram) {
                return  INSTAGRAM;
            } else if (items.get(position) instanceof ForecastFiveDays) {
                return WEATHER;
            }else if (items.get(position) instanceof String) {
                return NOTES;
            }
            return -1;
        }

    @Override
    public void onClick(View view) {

        WeatherFragment detailedWeather= new WeatherFragment();
        MainActivity.setFragment(true);
        int itemId=view.getId();
        switch (itemId){
            case R.id.day1:

                detailedWeather.setArguments(retrieveWeatherDetails(0, (ForecastFiveDays) items.get(1)));
                fragmentManager.beginTransaction().add(R.id.weather_CV,detailedWeather,TAG).commit();

                break;
            case R.id.day2:
                detailedWeather.setArguments(retrieveWeatherDetails(1, (ForecastFiveDays) items.get(1)));
                fragmentManager.beginTransaction().add(R.id.weather_CV,detailedWeather,TAG).commit();

                break;
            case R.id.day3:

                detailedWeather.setArguments(retrieveWeatherDetails(2, (ForecastFiveDays) items.get(1)));
                fragmentManager.beginTransaction().add(R.id.weather_CV,detailedWeather,TAG).commit();

                break;
            case R.id.day4:
                detailedWeather.setArguments(retrieveWeatherDetails(3, (ForecastFiveDays) items.get(1)));
                fragmentManager.beginTransaction().add(R.id.weather_CV,detailedWeather,TAG).commit();

                break;
            case R.id.day5:
                detailedWeather.setArguments(retrieveWeatherDetails(4, (ForecastFiveDays) items.get(1)));
                fragmentManager.beginTransaction().add(R.id.weather_CV,detailedWeather,TAG).commit();

                break;
        }


    }



 public Bundle retrieveWeatherDetails(int index, ForecastFiveDays weatherRespond){

     City city = weatherRespond.getCity();
     List<Forecast> forecast=weatherRespond.getList();
     Bundle bundle = new Bundle();
     Weather weather = forecast.get(index).getWeather().get(0);
     bundle.putString(WDESCRIPTION,weather.getMain()+"("+weather.getDescription()+")");
     bundle.putString(WCITY,city.getName()+ ","+city.getCountry());
     bundle.putString(WICON,"http://openweathermap.org/img/w/"+weather.getIcon()+".png");
     double temperature = Math.round(1.8*(forecast.get(index).getTemp().getMax() - 273)+32);
     bundle.putString(WTEMP,Double.toString(temperature)+" ºF");
     bundle.putString(WPRESSURE,Double.toString(forecast.get(index).getPressure())+" hPa");
     bundle.putString(WHUMIDITY,Double.toString(forecast.get(index).getHumidity())+" %");


     return bundle;

 }

    public void initOnClick(SecondCardViewHolder holder){
        holder.getmDay1().setOnClickListener(this);
        holder.getmDay2().setOnClickListener(this);
        holder.getmDay3().setOnClickListener(this);
        holder.getmDay4().setOnClickListener(this);
        holder.getmDay5().setOnClickListener(this);

    }

}
