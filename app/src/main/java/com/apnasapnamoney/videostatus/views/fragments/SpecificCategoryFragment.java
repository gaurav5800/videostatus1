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
import com.apnasapnamoney.videostatus.views.BaseFragment;


public class SpecificCategoryFragment extends BaseFragment {


RecyclerView mRecyclerViewCatagory;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_specific_category;
    }

    @Override
    protected void initViews(View view) {
        mRecyclerViewCatagory=view.findViewById(R.id.cat_recyclerview);

    }

}
