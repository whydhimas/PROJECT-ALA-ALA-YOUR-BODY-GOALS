package com.example.android.yourbodygoals_apps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView mMenuCalculator;
    ImageView mMenuMakananMinuman;
    ImageView mMenuJenisOlahraga;
    ImageView mMenuWikipediaMini;
    ImageView mMenuRiwayat;
    ImageView mMenuTentangApp;
    ImageView mMenuHelp;
    TextView mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mMenuCalculator = (ImageView) findViewById(R.id.img_calculator);
        mMenuMakananMinuman = (ImageView) findViewById(R.id.img_makanan_n_minuman);
        mMenuJenisOlahraga = (ImageView) findViewById(R.id.img_jenis_olahraga);
        mMenuWikipediaMini = (ImageView) findViewById(R.id.img_wikipedia_mini);
        mMenuRiwayat = (ImageView) findViewById(R.id.img_riwayat);
        mMenuTentangApp = (ImageView) findViewById(R.id.img_about);
        mMenuHelp = (ImageView) findViewById(R.id.iv_help);
        mLogout = (TextView) findViewById(R.id.tv_logout);

        mMenuCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuCalculator.class);
                startActivity(intent);
            }
        });

        mMenuMakananMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuMakananMinuman.class);
                startActivity(intent);
            }
        });

        mMenuJenisOlahraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuJenisOlahraga.class);
                startActivity(intent);
            }
        });

        mMenuWikipediaMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuWikipediaMini.class);
                startActivity(intent);
            }
        });

        mMenuRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuRiwayatPenggunaan.class);
                startActivity(intent);
            }
        });

        mMenuTentangApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuAbout.class);
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

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Anda yakin untuk logout?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent();
                        intent.putExtra("finish", true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
