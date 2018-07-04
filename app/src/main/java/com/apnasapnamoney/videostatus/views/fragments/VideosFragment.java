package com.apnasapnamoney.videostatus.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.VideosAdapter;
import com.apnasapnamoney.videostatus.api.CategoryApi;
import com.apnasapnamoney.videostatus.model.CategoriesResponse;
import com.apnasapnamoney.videostatus.utils.Utilities;
import com.apnasapnamoney.videostatus.views.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class VideosFragment extends BaseFragment implements CategoryApi.CategoriesInterface {

    RecyclerView mVideosRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_videos;
    }

    @Override
    protected void initViews(View view) {
        mVideosRecyclerView = view.findViewById(R.id.recycler_view_videos);

        if (!Utilities.checkInternet(getBaseActivity())) {
            showToast(R.string.no_internet_connection);
        } else {
            CategoryApi dpCategoryApi = new CategoryApi(getBaseActivity(), this);
            dpCategoryApi.fetchCategoriesResponse("video_categories");
        }

    }


    @Override
    public void onSuccess(ArrayList<CategoriesResponse.Categories> categoriesArrayList) {
        if (categoriesArrayList != null && categoriesArrayList.size() > 0) {
            setGridAdapter(mVideosRecyclerView, categoriesArrayList);
        }else {
            showToast("No data found");
        }
    }


    @Override
    protected void setGridAdapter(RecyclerView recyclerView, List<?> mList) {
        super.setGridAdapter(recyclerView, mList);

        VideosAdapter videosAdapter = new VideosAdapter(getBaseActivity(), (ArrayList<CategoriesResponse.Categories>) mList);
        mVideosRecyclerView.setAdapter(videosAdapter);

    }
}
