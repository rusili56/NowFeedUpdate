package com.example.nowfeed.network;

import android.app.Activity;
import android.util.Log;

import com.example.nowfeed.model.InstagramUserPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstagramAPIs {
    Activity fActivity;
    String userID;
    InstagramService igService;

    public InstagramAPIs(Activity a){
        this.fActivity = a;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        igService = retrofit.create(InstagramService.class);
    }

    public void getUserID(){
        Call<InstagramUserPOJO> getSelf = igService.getSelf();
        getSelf.enqueue(new Callback<InstagramUserPOJO>() {
            @Override
            public void onResponse(Call<InstagramUserPOJO> call, Response<InstagramUserPOJO> response) {
                userID = response.body().getData().getId();
                Log.d("success", userID);
            }

            @Override
            public void onFailure(Call<InstagramUserPOJO> call, Throwable t) {
            }
        });
    }

    public void getRecentMedia(){
        Call<InstagramUserPOJO> getRecentMedia = igService.getRecentMedia();
        getRecentMedia.enqueue(new Callback<InstagramUserPOJO>() {
            @Override
            public void onResponse(Call<InstagramUserPOJO> call, Response<InstagramUserPOJO> response) {
                //Log.d("success", );
            }

            @Override
            public void onFailure(Call<InstagramUserPOJO> call, Throwable t) {
            }
        });
    }
}
