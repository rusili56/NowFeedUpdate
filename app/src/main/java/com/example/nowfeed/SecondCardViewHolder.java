package com.example.nowfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import model.Weather;

/**
 * Created by Millochka on 10/31/16.
 */

public class SecondCardViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    TextView mDescription;
    ImageView mIcon;
    View mView;
    public SecondCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));

       mView=itemView;
       mTitle=(TextView)mView.findViewById(R.id.weather_title);
       mDescription=(TextView)mView.findViewById(R.id.weather_discription);

    }


    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        return inflater.inflate(R.layout.second_card, parent,false);

    }

    protected  void onBind(Weather weather){
        mTitle.setText(weather.getMain());
        mDescription.setText(weather.getDescription());


    }
}
