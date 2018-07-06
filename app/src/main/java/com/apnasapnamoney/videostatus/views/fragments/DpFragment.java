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
import com.apnasapnamoney.videostatus.adapter.DpAdapter;
import com.apnasapnamoney.videostatus.adapter.StatusAdapter;
import com.apnasapnamoney.videostatus.views.BaseFragment;

public class DpFragment extends BaseFragment {


    RecyclerView mRecyclerViewDp;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dp;
    }

    @Override
    protected void initViews(View view) {
        mRecyclerViewDp = view.findViewById(R.id.dp_recyclerview);

    }

}
