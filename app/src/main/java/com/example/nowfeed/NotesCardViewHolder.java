package com.example.nowfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Millochka on 10/31/16.
 */

public class NotesCardViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView mNote;
    public NotesCardViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView=itemView;
        mNote=(TextView)mView.findViewById(R.id.notes);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.third_card, parent,false);
    }

    protected  void onBind(String input){
        mNote.setText(input);
    }
}
