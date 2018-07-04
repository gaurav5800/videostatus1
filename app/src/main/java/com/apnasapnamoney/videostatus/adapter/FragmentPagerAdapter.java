package com.apnasapnamoney.videostatus.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.views.fragments.MemesFragment;
import com.apnasapnamoney.videostatus.views.fragments.StatusFragment;
import com.apnasapnamoney.videostatus.views.fragments.VideosFragment;
import com.apnasapnamoney.videostatus.views.fragments.DpFragment;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context mContext;

    public FragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new VideosFragment();
        } else if (position == 1) {
            return new StatusFragment();
        } else if (position == 2) {
            return new DpFragment();
        } else {
            return new MemesFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Videos";
            case 1:
                return "Status";
            case 2:
                return "DP";
            case 3:
                return "Memes";
            default:
                return null;
        }
    }

}


