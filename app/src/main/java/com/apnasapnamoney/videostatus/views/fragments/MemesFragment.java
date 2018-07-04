package com.apnasapnamoney.videostatus.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.SpecificStatusAdapter;

public class MemesFragment extends Fragment {


    public MemesFragment() {
        // Required empty public constructor
    }

    RecyclerView memes_recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memes, container, false);
        memes_recyclerview = view.findViewById(R.id.memes_recyclerview);
        memes_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        memes_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        SpecificStatusAdapter videosAdapter = new SpecificStatusAdapter(getActivity());
        memes_recyclerview.setAdapter(videosAdapter);
        return view;
    }

}
