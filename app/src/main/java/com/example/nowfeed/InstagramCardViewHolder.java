package com.example.nowfeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nowfeed.model.InstagramMediaPOJO;
import com.example.nowfeed.model.InstagramUserPOJO;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by Millochka on 10/31/16.
 */

public class InstagramCardViewHolder extends RecyclerView.ViewHolder {

    View mView;
    ImageView ivMedia;

    public InstagramCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView=itemView;
    }


    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.first_card, parent,false);

    }

    protected  void onBind(InstagramMediaPOJO instagram){
        Random rand = new Random();
        int value = rand.nextInt(instagram.getData().size()-1);

        ivMedia = (ImageView) mView.findViewById(R.id.idCardImageView);
        String sUrl = instagram.getData().get(value).getImages().getlow_resolution().getUrl().toString();
        Log.d("sUrl", sUrl);
        Picasso.with(mView.getContext()).load(sUrl).resize(400, 200).centerCrop().into(ivMedia);
    }
}
