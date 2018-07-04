package com.apnasapnamoney.videostatus.views.videos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.adapter.SpecificCategoryAdapter;
import com.apnasapnamoney.videostatus.adapter.SpecificStatusAdapter;

public class UnderStatus extends AppCompatActivity {
RecyclerView status_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_status);
        status_recyclerview=findViewById(R.id.status_recyclerview);
        status_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(UnderStatus.this,1);
        status_recyclerview.setLayoutManager(layoutManager);

        // ArrayList<AndroidVersion> androidVersions = prepareData();
        //DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        SpecificStatusAdapter videosAdapter=new SpecificStatusAdapter(UnderStatus.this);
        status_recyclerview.setAdapter(videosAdapter);
    }
}
