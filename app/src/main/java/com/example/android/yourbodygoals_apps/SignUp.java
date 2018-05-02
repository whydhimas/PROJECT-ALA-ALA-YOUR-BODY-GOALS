package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText mFullName, mAge, mWeight, mHeight;
    Button mEnterApp;
    String mStringData1, mStringData2, mStringData3, mStringData4;

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

        mEnterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStringData1 = mFullName.getText().toString();
                mStringData2 = mAge.getText().toString();
                mStringData3 = mWeight.getText().toString();
                mStringData4 = mHeight.getText().toString();

                if (mFullName.getText().toString().length() == 0){
                    mFullName.setError("Full Name required");
                }
                else if (mAge.getText().toString().length() == 0){
                    mAge.setError("Age required");
                }
                else if (mWeight.getText().toString().length() == 0) {
                    mWeight.setError("Weight required");
                }
                else if (mHeight.getText().toString().length() == 0) {
                    mHeight.setError("Height required");
                }
                else {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Login");
                ref.child("Fullname").setValue(mStringData1);
                ref.child("Age").setValue(mStringData2);
                ref.child("Weight").setValue(mStringData3);
                ref.child("Height").setValue(mStringData4);

                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(SignUp.this, "Data Anda telah tersimpan, "+mStringData1, Toast.LENGTH_LONG).show();
                finish();
                }
            }
        });
    }
}
