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
import com.apnasapnamoney.videostatus.adapter.StatusAdapter;
import com.apnasapnamoney.videostatus.adapter.VideosAdapter;

public class StatusFragment extends Fragment {


    public StatusFragment() {
        // Required empty public constructor
    }
RecyclerView status_recyclerview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_status, container, false);

        status_recyclerview=view.findViewById(R.id.status_recyclerview);
        status_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        status_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        StatusAdapter videosAdapter=new StatusAdapter(getActivity());
        status_recyclerview.setAdapter(videosAdapter);
        return view;
    }


}
