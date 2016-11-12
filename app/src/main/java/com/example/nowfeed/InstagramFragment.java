package com.example.nowfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nowfeed.R;
import com.squareup.picasso.Picasso;

/**
 * Created by rusili on 11/10/16.
 */

public class InstagramFragment extends Fragment {
    ImageView ivFrag;

    public static String url = null;

    public void setName(String sInput) {
        url = sInput;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.instagram_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ivFrag = (ImageView) view.findViewById(R.id.idInstagram_FragImageView);
        Picasso.with(view.getContext()).load(url).resize(340, 400).centerCrop().into(ivFrag);
        super.onViewCreated(view, savedInstanceState);
    }
}
