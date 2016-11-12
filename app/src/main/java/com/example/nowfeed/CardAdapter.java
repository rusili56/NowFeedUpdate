package com.example.nowfeed;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.nowfeed.model.BestSeller;
import com.example.nowfeed.model.Instagram;
import com.example.nowfeed.model.TopStory;
import com.example.nowfeed.model.WeatherRespond;

import java.util.List;

/**
 * Created by Millochka on 10/30/16.
 */
public class CardAdapter extends RecyclerView.Adapter {

    private List<Object> items;
    Activity act;
    static InstagramFragment instafrag = new InstagramFragment();
    static FragmentManager fragmentManager;
    static boolean isFragOpen = false;

    public final int INSTAGRAM = 0, WEATHER = 1, NOTES = 2, TOPSTORIES = 3, BESTSELLERS = 4;

    public CardAdapter(List<Object> items, Activity aInput) {
        this.act = aInput;
        this.items = items;
        fragmentManager = act.getFragmentManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case INSTAGRAM:
                viewHolder = new InstagramCardViewHolder(parent, act);
                break;
            case WEATHER:
                viewHolder = new WeatherCardViewHolder(parent);
                break;
            case TOPSTORIES:
                viewHolder = new TopStoriesViewHolder(parent);
                break;
            case BESTSELLERS:
                viewHolder = new BestSellersViewHolder(parent);
                break;
            default:
                viewHolder = new NotesCardViewHolder(parent);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case INSTAGRAM:
                InstagramCardViewHolder firstCard = (InstagramCardViewHolder) holder;
                firstCard.onBind((Instagram) items.get(position));
                ((InstagramCardViewHolder) holder).ivMedia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fragmentManager.beginTransaction().add(R.id.idFragLayout, instafrag).commit();
                        isFragOpen = true;
                    }
                });
                break;
            case WEATHER:
                WeatherCardViewHolder secondCard = (WeatherCardViewHolder) holder;
                secondCard.onBind((WeatherRespond) items.get(position));
                break;
            case TOPSTORIES:
                TopStoriesViewHolder topviewedCard = (TopStoriesViewHolder) holder;
                topviewedCard.onBind((TopStory) items.get(position));
                break;
            case BESTSELLERS:
                BestSellersViewHolder bestSellersCard = (BestSellersViewHolder) holder;
                bestSellersCard.onBind((BestSeller) items.get(position));
                break;
            default:
                NotesCardViewHolder thirdCard = (NotesCardViewHolder) holder;
                thirdCard.onBind((String) items.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Instagram) {
            return INSTAGRAM;
        } else if (items.get(position) instanceof WeatherRespond) {
            return WEATHER;
        } else if (items.get(position) instanceof BestSeller) {
            return BESTSELLERS;
        } else if (items.get(position) instanceof TopStory) {
            return TOPSTORIES;
        } else if (items.get(position) instanceof String) {
            return NOTES;
        }
        return -1;
    }

    public static InstagramFragment getInstaFrag() {
        return instafrag;
    }

    public static FragmentManager getFragMan() {
        return fragmentManager;
    }

    public static boolean getIsTrue() {
        return isFragOpen;
    }

}
