package com.example.nowfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

/**
 * Created by rusili on 11/13/16.
 */

public class VideoFragment extends Fragment{
    VideoView vvFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.instagram_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        vvFrag = (VideoView) view.findViewById(R.id.idVideoView);
        vvFrag.setVideoPath("/Users/rusili/Desktop/accesscode/NowFeed/app/src/main/res/raw/videoplayback.3gp");
        super.onViewCreated(view, savedInstanceState);
    }
}
