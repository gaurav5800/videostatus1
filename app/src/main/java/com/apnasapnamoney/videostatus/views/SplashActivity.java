package com.apnasapnamoney.videostatus.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.utils.PreferenceKeys;
import com.apnasapnamoney.videostatus.utils.SharedPrefsHelper;


/**
 * Created by JINDAL on 29-04-2017.
 */

public class SplashActivity extends AppCompatActivity {
    private Thread background;
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //wait for 2 secs
        background = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_DISPLAY_LENGTH);

                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (background != null && background.isAlive()) {
            background.interrupt();
            finish();
        }
    }


}
