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
import android.widget.RelativeLayout;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.SpecificCategoryAdapter;
import com.apnasapnamoney.videostatus.adapter.VideosAdapter;


public class SpecificCategoryFragment extends Fragment {

    public SpecificCategoryFragment() {
        // Required empty public constructor
    }
RecyclerView cat_recyclerview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_specific_category, container, false);
        cat_recyclerview=view.findViewById(R.id.cat_recyclerview);
        cat_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        cat_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        SpecificCategoryAdapter videosAdapter=new SpecificCategoryAdapter(getActivity());
        cat_recyclerview.setAdapter(videosAdapter);
        return view;
    }

}
