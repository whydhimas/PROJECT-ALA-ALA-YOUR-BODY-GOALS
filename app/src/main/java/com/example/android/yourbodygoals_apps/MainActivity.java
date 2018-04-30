package com.example.android.yourbodygoals_apps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {
    ImageView mMenuCalculator;
    ImageView mMenuMakananMinuman;
    ImageView mMenuJenisOlahraga;
    ImageView mMenuWikipediaMini;
    ImageView mMenuRiwayat;
    ImageView mMenuTentangApp;
    ImageView mMenuHelp;
    TextView mLogout;

    GridLayout mainGrid;

    //Context mContex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        //mMenuCalculator = (ImageView) findViewById(R.id.img_calculator);
        //mMenuMakananMinuman = (ImageView) findViewById(R.id.img_makanan_n_minuman);
        //mMenuJenisOlahraga = (ImageView) findViewById(R.id.img_jenis_olahraga);
        //mMenuWikipediaMini = (ImageView) findViewById(R.id.img_wikipedia_mini);
        //mMenuRiwayat = (ImageView) findViewById(R.id.img_riwayat);
        //mMenuTentangApp = (ImageView) findViewById(R.id.img_about);
        //mMenuHelp = (ImageView) findViewById(R.id.iv_help);
        //mLogout = (TextView) findViewById(R.id.tv_logout);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);

    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++){
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int theFinal = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //cardView.setCardBackgroundColor(mContex.getResources().getColor(R.color.buttonPressed));
                    if (theFinal == 0){
                        Intent intent = new Intent(MainActivity.this, MenuCalculator.class);
                        startActivity(intent);
                    }
                    else if (theFinal == 1){
                        Intent intent = new Intent(MainActivity.this, MenuMakananMinuman.class);
                        startActivity(intent);
                    }
                    else if (theFinal == 2){
                        Intent intent = new Intent(MainActivity.this, MenuJenisOlahraga.class);
                        startActivity(intent);
                        //buildDialog(0, "Menu under construction \nYou are running ver.01 BETA");
                    }
                    else if (theFinal == 3){
                        Intent intent = new Intent(MainActivity.this, MenuWikipediaMini.class);
                        startActivity(intent);
                    }
                    else if (theFinal == 4){
                        //Intent intent = new Intent(MainActivity.this, MenuRiwayatPenggunaan.class);
                        //startActivity(intent);
                        buildDialog(0, "Menu under construction \nYou are running ver.01 BETA");
                    }
                    else if (theFinal == 5){
                        Intent intent = new Intent(MainActivity.this, MenuAbout.class);
                        startActivity(intent);
                    }
                    else if (theFinal == 6){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Anda yakin untuk logout?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent myIntent = new Intent(MainActivity.this, SignUp.class);
                                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
                                startActivity(myIntent);
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
                    else if (theFinal == 7){
                        Intent intent = new Intent(MainActivity.this, HelpPage.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }
}
