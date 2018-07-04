package com.apnasapnamoney.videostatus.views.videos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.SpecificDpAdapter;
import com.apnasapnamoney.videostatus.adapter.SpecificStatusAdapter;

public class UnderDP extends AppCompatActivity {
RecyclerView under_dp_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_dp);
        under_dp_recyclerview=findViewById(R.id.under_dp_recyclerview);
        under_dp_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(UnderDP.this,2);
        under_dp_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        SpecificDpAdapter videosAdapter=new SpecificDpAdapter(UnderDP.this);
        under_dp_recyclerview.setAdapter(videosAdapter);
    }
}
