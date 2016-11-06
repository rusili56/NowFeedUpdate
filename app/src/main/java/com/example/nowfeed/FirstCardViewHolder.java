package com.example.nowfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nowfeed.model.InstagramUserPOJO;

/**
 * Created by Millochka on 10/31/16.
 */

public class FirstCardViewHolder extends RecyclerView.ViewHolder {

    View mView;
    TextView mTitle;
    public FirstCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));

        mView=itemView;


    }


    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.first_card, parent,false);

    }

    protected  void onBind(InstagramUserPOJO instagram){

    }

}
