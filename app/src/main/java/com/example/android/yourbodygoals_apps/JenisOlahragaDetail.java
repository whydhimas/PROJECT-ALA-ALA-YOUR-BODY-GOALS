package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Yosafat Dhimas on 27/04/2018.
 */

public class JenisOlahragaDetail extends AppCompatActivity{
    TextView joTitle, joDesc;
    ImageView joPict;
    Button joOpenLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        joTitle = (TextView) findViewById(R.id.titleDetail2);
        joDesc = (TextView) findViewById(R.id.title_descriptionDetail2);
        joPict = (ImageView) findViewById(R.id.imgDetail2);
        joOpenLoc = (Button) findViewById(R.id.btn_openloc);

        joOpenLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the string indicating a location. Input is not validated; it is
                // passed to the location handler intact.
                String loc = joTitle.getText().toString();

                // Parse the location and create the intent.
                Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

                // Find an activity to handle the intent, and start that activity.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
            }
        });

        joTitle.setText(getIntent().getStringExtra("title2"));
        joDesc.setText(getIntent().getStringExtra("desc2"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource2",0)).into(joPict);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.ketentuanMaps) {
            buildDialog(0, "Lokasi olah raga akan ditampilkan sesuai dengan GPS anda");
        }
        return super.onOptionsItemSelected(item);
    }

    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ketentuan");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }
}
