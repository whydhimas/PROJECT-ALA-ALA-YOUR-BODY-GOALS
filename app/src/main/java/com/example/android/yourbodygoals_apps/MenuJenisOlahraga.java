package com.example.android.yourbodygoals_apps;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Locale;

public class MenuJenisOlahraga extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<JenisOlahraga> mJenisOlahraga;
    private JenisOlahragaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jenis_olahraga);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mJenisOlahraga = new ArrayList<JenisOlahraga>();

        mAdapter = new JenisOlahragaAdapter(this, mJenisOlahraga);
        mRecyclerView.setAdapter(mAdapter);

        initialzeData();

    }

    private void initialzeData() {
        String[] mineralList = getResources().getStringArray(R.array.jenis_olahraga_judul);
        String[] mineralDesc = getResources().getStringArray(R.array.jenis_olahraga_desc);
        TypedArray mineralImageResources = getResources().obtainTypedArray(R.array.jenis_olahraga_images);

        mJenisOlahraga.clear();

        for (int i =0; i<mineralList.length; i++){
            mJenisOlahraga.add(new JenisOlahraga(mineralList[i], mineralDesc[i], mineralImageResources.getResourceId(i,0)));
        }

        mineralImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }
}
