package com.example.nowfeed;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.nowfeed.model.BestSeller;
import com.example.nowfeed.model.ForecastFiveDays;
import com.example.nowfeed.model.Instagram;
import com.example.nowfeed.model.TopStory;
import com.example.nowfeed.network.InstagramService;
import com.example.nowfeed.network.NYTimesService;
import com.example.nowfeed.network.WeatherApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    InstagramFragment instafrag = new InstagramFragment();
    VideoFragment vidfrag = new VideoFragment();

    List<Object> mCardsData = new ArrayList<>();
    private static final String TAG = "MainActivity";
    SharedPreferences sharedPrefs;
    Set<String> getAddedNotes, getSavedNotes;
    private GestureDetectorCompat mDetector;

    private static final String API_KEY = "62f136aaf813f7d74fabcdfdb0fcb3ba";
    private static final String LOCATION = "NEWYORK,USA";
    public Retrofit mRetrofit;
    WeatherApi mWeatherApi;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this, this);

        if (isNetworkOnline() == false) {
            Toast toast = Toast.makeText(this, "Please check your network. App will only have partial functionality", Toast.LENGTH_LONG);
            toast.show();
        }

//        //Hyun
        mCardsData.add("My notes");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            sharedPrefs = getSharedPreferences("Stuff", MODE_PRIVATE);
            getAddedNotes = sharedPrefs.getStringSet("mAddedNotes", NotesCardViewHolder.getAddHash());
            getSavedNotes = sharedPrefs.getStringSet("mSavedNotes", NotesCardViewHolder.getSavedHash());
            NotesCardViewHolder.setSavedNotes(getSavedNotes);
            NotesCardViewHolder.setAddedNotes(getAddedNotes);
        }
        WeatherAPI();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TopStoriesAPI();
                BestSellersAPI();
                InstagramAPI();
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initializeRecView();
            }
        }, 4000);
    }

    public void InstagramAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstagramService igService = retrofit.create(InstagramService.class);
        Call<Instagram> getRecentMedia = igService.getRecentMedia();
        getRecentMedia.enqueue(new Callback<Instagram>() {
            @Override
            public void onResponse(Call<Instagram> call, Response<Instagram> response) {
                if (response.isSuccessful()) {
                    Instagram igMedia = response.body();
                    mCardsData.add(igMedia);
                }
            }

            @Override
            public void onFailure(Call<Instagram> call, Throwable t) {

            }
        });
    }

    public void BestSellersAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NYTimesService igService = retrofit.create(NYTimesService.class);
        Call<BestSeller> getRecentMedia = igService.getBestSellers();
        getRecentMedia.enqueue(new Callback<BestSeller>() {
            @Override
            public void onResponse(Call<BestSeller> call, Response<BestSeller> response) {
                if (response.isSuccessful()) {
                    BestSeller NYTBestSellers = response.body();
                    mCardsData.add(NYTBestSellers);
                }
            }

            @Override
            public void onFailure(Call<BestSeller> call, Throwable t) {
            }
        });
    }

    public void TopStoriesAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NYTimesService igService = retrofit.create(NYTimesService.class);
        Call<TopStory> getRecentMedia = igService.getTopStories();
        getRecentMedia.enqueue(new Callback<TopStory>() {
            @Override
            public void onResponse(Call<TopStory> call, Response<TopStory> response) {
                if (response.isSuccessful()) {
                    TopStory NYTTopStories = response.body();
                    mCardsData.add(NYTTopStories);
                }
            }

            @Override
            public void onFailure(Call<TopStory> call, Throwable t) {
            }
        });
    }

    public void WeatherAPI() {
        mRetrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        mWeatherApi = mRetrofit.create(WeatherApi.class);
        Call<ForecastFiveDays> call = mWeatherApi.fetchFiveDays(LOCATION, API_KEY);
        call.enqueue(new Callback<ForecastFiveDays>() {
            @Override
            public void onResponse(Call<ForecastFiveDays> call, Response<ForecastFiveDays> response) {
                if (response.isSuccessful()) {
                    ForecastFiveDays weatherRespond = response.body();
                    mCardsData.add(weatherRespond);
                }
            }

            @Override
            public void onFailure(Call<ForecastFiveDays> call, Throwable t) {
            }
        });
    }

    public void initializeRecView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(mCardsData, getFragmentManager()));
    }

    public void onClickRemoveFrag(View view) {
        FragmentManager fm = CardAdapter.getFragMan();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(CardAdapter.getInstaFrag()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPrefs = getSharedPreferences("Stuff", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putStringSet("mAddNotes", NotesCardViewHolder.getAddHash());
        editor.putStringSet("mSavedNotes", NotesCardViewHolder.getSavedHash());
        editor.apply();
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ppap);
        mediaPlayer.start();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        this.recreate();
        return false;
    }
}
