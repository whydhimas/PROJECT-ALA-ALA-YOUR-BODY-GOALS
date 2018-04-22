package com.example.android.yourbodygoals_apps;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Yosafat Dhimas on 22/04/2018.
 */

public class WikipediaMiniDetail extends AppCompatActivity {
    TextView wikiTitle, wikiDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        wikiTitle = (TextView)findViewById(R.id.titleDetail);
        wikiDesc = (TextView)findViewById(R.id.title_descriptionDetail);

        wikiTitle.setText(getIntent().getStringExtra("title"));
        wikiDesc.setText(getIntent().getStringExtra("desc"));
    }
}
