package com.apnasapnamoney.videostatus.views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.interfaces.Core;
import com.apnasapnamoney.videostatus.interfaces.Disposable;
import com.apnasapnamoney.videostatus.utils.ProgressUtility;

import java.util.List;

/**
 * To initialize view,controls and replace Fragments
 * Created by jindaldipanshu on 7/2/2016.
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment implements Core, Disposable {
    public String TAG = "BaseFragment";
    private BaseActivity activity;
    public Typeface regular_fonts,light_fonts,semibold_fonts;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity)
            activity = (BaseActivity) context;
    }

    protected void initFonts() {
        regular_fonts = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Muli.ttf");
        light_fonts = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Muli-Light.ttf");
        semibold_fonts = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Muli-SemiBold.ttf");
    }



    protected View findViewById(int id) {
        return getView().findViewById(id);
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        getDataFromBundle(getArguments());
        initViews(view);
        return view;
    }

    protected void getDataFromBundle(Bundle bundle) {
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    /*protected void setAdapter(RecyclerView recyclerView, ArrayList<?> mList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }*/

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(activity, ""+getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(activity, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        try {
            if (!getBaseActivity().isFinishing())
                ProgressUtility.showProgress(getBaseActivity(), getString(R.string.please_wait_meassge));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideDialog() {
        ProgressUtility.dismissProgress();
    }


    protected void setAdapter(RecyclerView recyclerView, List<?> mList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    protected void setGridAdapter(RecyclerView recyclerView, List<?> mList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
    }


}
