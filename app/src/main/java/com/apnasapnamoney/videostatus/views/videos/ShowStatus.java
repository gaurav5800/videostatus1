package com.apnasapnamoney.videostatus.views.videos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.DpAdapter;
import com.apnasapnamoney.videostatus.adapter.SpecificDpAdapter;
import com.apnasapnamoney.videostatus.adapter.SpecificStatusAdapter;

public class ShowStatus extends AppCompatActivity {
RecyclerView extra_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_status);
        extra_recyclerview=findViewById(R.id.extra_recyclerview);
        extra_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ShowStatus.this,2);
        extra_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        SpecificDpAdapter videosAdapter=new SpecificDpAdapter(ShowStatus.this);
        extra_recyclerview.setAdapter(videosAdapter);
    }
}
