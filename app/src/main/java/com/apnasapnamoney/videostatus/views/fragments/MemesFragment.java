package com.apnasapnamoney.videostatus.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.views.BaseFragment;

public class MemesFragment extends BaseFragment {



    RecyclerView mRecyclerViewMeme;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_memes;
    }

    @Override
    protected void initViews(View view) {
        mRecyclerViewMeme = view.findViewById(R.id.memes_recyclerview);

    }

}
