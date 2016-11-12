package com.example.nowfeed;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nowfeed.model.InstagramMediaPOJO;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by Millochka on 10/31/16.
 */

public class InstagramCardViewHolder extends RecyclerView.ViewHolder {

    View mView;
    ImageView ivMedia;
    private FragmentManager supportFragmentManager;
    Activity act;
    static InstagramFragment instafrag = new InstagramFragment();
    String sUrl;
    boolean iFragOpen = false;

    public InstagramCardViewHolder(ViewGroup parent, Activity aInput) {
        super(inflateView(parent));
        mView = itemView;
        this.act = aInput;
    }

    public static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.first_card, parent, false);
    }

    protected void onBind(InstagramMediaPOJO instagram) {
        Random rand = new Random();
        int value = rand.nextInt(instagram.getData().size() - 1);

        ivMedia = (ImageView) mView.findViewById(R.id.idCardImageView);
        sUrl = instagram.getData().get(value).getImages().getlow_resolution().getUrl().toString();
        instafrag.setName(sUrl);
        Log.d("sUrl", sUrl);
        Picasso.with(mView.getContext()).load(sUrl).resize(400, 200).centerCrop().into(ivMedia);
    }

}
