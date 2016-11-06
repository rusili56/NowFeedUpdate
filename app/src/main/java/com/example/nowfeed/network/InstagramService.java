package com.example.nowfeed.network;

import com.example.nowfeed.model.InstagramUserPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rusili on 11/6/16.
 */

public interface InstagramService {

    String client_id = "client_id=1e801153a0a249459e7727d77b475687";
    String client_secret = "client_secret=8f6892d9452c4f6dafea86807d2db731";
    String access_token = "access_token=4093282801.1677ed0.19062e5199704d50a1380d6aad47cd48";

    @GET ("users/self?" + access_token)
    Call<InstagramUserPOJO> getSelf();

    @GET ("users/self/media/recent?"+ access_token)
    Call<InstagramUserPOJO> getRecentMedia();

}
