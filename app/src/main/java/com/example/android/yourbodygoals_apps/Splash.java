package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 4000; //4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Login").exists()!=true){
                    startActivity(new Intent(Splash.this, ForTheFirstTime.class));
                    finish();
                }else {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Splash.this, "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });

//        new Handler().postDelayed(new Runnable() { ORA DIKANGGO
//            @Override
//            public void run() {
//                startActivity(new Intent(Splash.this,ForTheFirstTime.class));
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
    }
}
