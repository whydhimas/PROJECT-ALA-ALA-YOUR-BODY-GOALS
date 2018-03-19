package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mMenuCalculator;
    ImageView mMenuMakananMinuman;
    ImageView mMenuJenisOlahraga;
    ImageView mMenuWikipediaMini;
    ImageView mMenuRiwayat;
    ImageView mMenuTentangApp;
    ImageView mMenuHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenuCalculator = (ImageView) findViewById(R.id.img_calculator);
        mMenuMakananMinuman = (ImageView) findViewById(R.id.img_makanan_n_minuman);
        mMenuJenisOlahraga = (ImageView) findViewById(R.id.img_jenis_olahraga);
        mMenuWikipediaMini = (ImageView) findViewById(R.id.img_wikipedia_mini);
        mMenuRiwayat = (ImageView) findViewById(R.id.img_riwayat);
        mMenuTentangApp = (ImageView) findViewById(R.id.img_about);
        mMenuHelp = (ImageView) findViewById(R.id.iv_help);

        mMenuCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuCalculator.class);
                startActivity(intent);
            }
        });

        mMenuHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpPage.class);
                startActivity(intent);
            }
        });

    }
}
