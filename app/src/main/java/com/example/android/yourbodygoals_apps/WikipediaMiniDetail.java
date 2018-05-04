package com.example.android.yourbodygoals_apps;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by Yosafat Dhimas on 22/04/2018.
 */

public class WikipediaMiniDetail extends AppCompatActivity {
    TextView wikiTitle, wikiDesc;
    ImageView wikiPict;
    CoordinatorLayout coordinatorLayout;

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
        wikiPict = (ImageView) findViewById(R.id.imgDetail);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        wikiTitle.setText(getIntent().getStringExtra("title"));
        wikiDesc.setText(getIntent().getStringExtra("desc"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0)).into(wikiPict);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu5, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.copy) {
            ((ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE)).setText(wikiDesc.getText().toString());
            //Toast.makeText(getApplicationContext(), "Article copied to Clipboard", Toast.LENGTH_LONG).show();
            Snackbar.make(coordinatorLayout, "Wiki article copied to clipboard", Snackbar.LENGTH_LONG).show();
        }

        if(item.getItemId()==R.id.sizeHigh) {
            wikiDesc.setTextSize(21);
            Snackbar.make(coordinatorLayout, "Text size setting is high", Snackbar.LENGTH_LONG).show();
        }

        if(item.getItemId()==R.id.sizeNormal) {
            wikiDesc.setTextSize(16);
            Snackbar.make(coordinatorLayout, "Text size setting is normal", Snackbar.LENGTH_LONG).show();
        }

        if(item.getItemId()==R.id.sizeSmall) {
            wikiDesc.setTextSize(11);
            Snackbar.make(coordinatorLayout, "Text size setting is small", Snackbar.LENGTH_LONG).show();
        }

        if(item.getItemId()==R.id.share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = wikiDesc.getText().toString();
            String shareSub = wikiTitle.getText().toString();
            intent.putExtra(Intent.EXTRA_TEXT,shareBody);
            intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            startActivity(Intent.createChooser(intent, "Share the article"));
        }

        if(item.getItemId()==R.id.menuitem_home) {
            Intent intent = new Intent(WikipediaMiniDetail.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
