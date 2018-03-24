package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class SignUp extends AppCompatActivity {
    EditText mFullName;
    EditText mAge;
    EditText mWeight;
    EditText mHeight;

    Button mEnterApp;

    String mStringData1;
    String mStringData2;
    String mStringData3;
    String mStringData4;

    Firebase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mFullName = (EditText) findViewById(R.id.et_fullname);
        mAge = (EditText) findViewById(R.id.et_age);
        mWeight = (EditText) findViewById(R.id.et_weight);
        mHeight = (EditText) findViewById(R.id.et_height);

        mEnterApp = (Button) findViewById(R.id.btn_entertheapp);

        mStringData1 = mFullName.getText().toString();
        mStringData2 = mAge.getText().toString();
        mStringData3 = mWeight.getText().toString();
        mStringData4 = mHeight.getText().toString();

        Firebase.setAndroidContext(this);

        mFirebase = new Firebase("https://yourbodygoals-apps.firebaseio.com");

        mEnterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStringData1 = mFullName.getText().toString();
                mStringData2 = mAge.getText().toString();
                mStringData3 = mWeight.getText().toString();
                mStringData4 = mHeight.getText().toString();

                Firebase mNewChild1 = mFirebase.child("FullName");
                mNewChild1.setValue(mStringData1);
                Firebase mNewChild2 = mFirebase.child("Age");
                mNewChild2.setValue(mStringData2);
                Firebase mNewChild3 = mFirebase.child("Weight");
                mNewChild3.setValue(mStringData3);
                Firebase mNewChild4 = mFirebase.child("Height");
                mNewChild4.setValue(mStringData4);

                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(SignUp.this, "Data Anda telah tersimpan, "+mStringData1, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
