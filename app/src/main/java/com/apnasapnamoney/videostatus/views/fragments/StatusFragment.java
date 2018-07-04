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
import com.apnasapnamoney.videostatus.views.BaseFragment;

import java.util.List;

public class StatusFragment extends BaseFragment {


RecyclerView status_recyclerview;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_status;
    }

    @Override
    protected void initViews(View view) {
        status_recyclerview=view.findViewById(R.id.status_recyclerview);

    }

    @Override
    protected void setGridAdapter(RecyclerView recyclerView, List<?> mList) {
        super.setGridAdapter(recyclerView, mList);

    }
}
