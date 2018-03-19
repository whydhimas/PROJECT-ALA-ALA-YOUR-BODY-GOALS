package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForTheFirstTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_the_first_time);
    }

    public void onEnterTheIdentity(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
