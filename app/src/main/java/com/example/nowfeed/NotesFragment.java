package com.example.nowfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Hyun on 11/18/16.
 */

public class NotesFragment extends Fragment {

    protected RecyclerView mListRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listnotes_frag, container, false);

        mListRecyclerView = (RecyclerView) view.findViewById(R.id.listnotes_RV);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mListRecyclerView.setAdapter(new ListNotesAdapter());

        return view;
    }

}
