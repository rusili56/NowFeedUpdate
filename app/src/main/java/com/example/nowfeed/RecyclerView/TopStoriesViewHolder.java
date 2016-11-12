package com.example.nowfeed.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nowfeed.R;
import com.example.nowfeed.model.TopStoriesPOJO;
import com.squareup.picasso.Picasso;

/**
 * Created by Millochka on 10/31/16.
 */

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    View mView;
    ImageView ivThumbnail;
    TextView tvTitle;

    public TopStoriesViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView = itemView;
    }


    public static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.topviewed_card, parent, false);
    }

    protected void onBind(TopStoriesPOJO tsPOJO) {
        ivThumbnail = (ImageView) mView.findViewById(R.id.idtopviewedthumbnail);
        tvTitle = (TextView) mView.findViewById(R.id.idtopviewedtitle);

        tvTitle.setText(tsPOJO.getResults().get(0).getTitle());
        String url = tsPOJO.getResults().get(0).getMultimedia().get(3).getUrl();
        Log.d("Top Stories URL", url);
        Picasso.with(mView.getContext()).load(url).resize(400, 200).centerCrop().into(ivThumbnail);
    }
}
