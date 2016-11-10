package com.example.nowfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import com.example.nowfeed.model.BestSellersPOJO;
import com.example.nowfeed.model.InstagramMediaPOJO;
import com.example.nowfeed.model.InstagramUserPOJO;
import com.example.nowfeed.model.TopStoriesPOJO;
import com.example.nowfeed.model.WeatherRespond;

/**
 * Created by Millochka on 10/30/16.
 */
public class CardAdapter extends RecyclerView.Adapter {

    private List<Object> items;

    private final int  INSTAGRAM = 0,WEATHER = 1,NOTES=2, TOPSTORIES = 3, BESTSELLERS = 4;

    public CardAdapter(List<Object> items) {
        this.items=items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case INSTAGRAM:
                viewHolder = new InstagramCardViewHolder(parent);
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
                firstCard.onBind((InstagramMediaPOJO) items.get(position));
                break;
            case WEATHER:
                WeatherCardViewHolder secondCard = (WeatherCardViewHolder) holder;
                secondCard.onBind((WeatherRespond) items.get(position));
                break;
            case TOPSTORIES:
                TopStoriesViewHolder topviewedCard = (TopStoriesViewHolder) holder;
                topviewedCard.onBind((TopStoriesPOJO) items.get(position));
                break;
            case BESTSELLERS:
                BestSellersViewHolder bestSellersCard = (BestSellersViewHolder) holder;
                bestSellersCard.onBind((BestSellersPOJO) items.get(position));
                break;
            default:
                NotesCardViewHolder thirdCard = (NotesCardViewHolder) holder;
                thirdCard.onBind((String)items.get(position));
                break;
        }
    }

        @Override
        public int getItemCount () {
            return items.size();
        }


        @Override
        public int getItemViewType ( int position){
            if (items.get(position) instanceof InstagramMediaPOJO) {
                return  INSTAGRAM;
            } else if (items.get(position) instanceof WeatherRespond) {
                return WEATHER;
            } else if (items.get(position) instanceof BestSellersPOJO) {
                return BESTSELLERS;
            } else if (items.get(position) instanceof TopStoriesPOJO) {
                return TOPSTORIES;
            }else if (items.get(position) instanceof String) {
                return NOTES;
            }
            return -1;
        }

}
