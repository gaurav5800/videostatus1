package com.apnasapnamoney.videostatus.views.videos;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.views.fragments.SpecificCategoryFragment;

public class UnderCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_category);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container,new SpecificCategoryFragment());
        fragmentTransaction.commit();
    }
}
