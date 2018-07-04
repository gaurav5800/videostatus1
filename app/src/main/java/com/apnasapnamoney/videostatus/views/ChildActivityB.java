package com.apnasapnamoney.videostatus.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.apnasapnamoney.videostatus.R;

public class ChildActivityB extends AppCompatActivity {

    private static final String TAG = ChildActivityB.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_b);
        Log.e(TAG, "onCreate");
        FrameLayout frameLayout = findViewById(R.id.frame_layput_container);
        replaceFragment(new FragmentC(), null);
    }

    /**
     * This method is used to replace fragment .
     *
     * @param newFragment :replace an existing fragment with new fragment.
     * @param args        :pass bundle data fron one fragment to another fragment
     */
    public void replaceFragment(Fragment newFragment, Bundle args) {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        Log.e(TAG, "count--" + count + "");
        //manager.popBackStack();
        newFragment.setArguments(args);
        FragmentTransaction transaction = manager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.frame_layput_container, newFragment);
       // transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}
