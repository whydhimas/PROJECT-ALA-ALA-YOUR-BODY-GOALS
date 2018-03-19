package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 5000; //3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,ForTheFirstTime.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
