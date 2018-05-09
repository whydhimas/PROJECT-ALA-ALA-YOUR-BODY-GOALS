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
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MenuWikipediaMini extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private ArrayList<WikipediaMini> mWikiMini;
    private WikipediaMiniAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wikipedia_mini);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWikiMini = new ArrayList<WikipediaMini>();
        mAdapter = new WikipediaMiniAdapter(this, mWikiMini);
        mRecyclerView.setAdapter(mAdapter);

        initialzeData();
    }

    private void initialzeData() {
        String[] mineralList = getResources().getStringArray(R.array.wikipedia_judul);
        String[] mineralDesc = getResources().getStringArray(R.array.wikipedia_desc);
        TypedArray mineralImageResources = getResources().obtainTypedArray(R.array.wikiImages);

        mWikiMini.clear();

        for (int i =0; i<mineralList.length; i++){
            mWikiMini.add(new WikipediaMini(mineralList[i], mineralDesc[i], mineralImageResources.getResourceId(i,0)));
        }

        mineralImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<WikipediaMini> newList = new ArrayList<>();
        for(WikipediaMini wikipediaMini: mWikiMini) {
            String name = wikipediaMini.getJudul().toLowerCase();
            if (name.contains(newText)){
                newList.add(wikipediaMini);
            }
        }
        mAdapter.setFilter(newList);
        return false;
    }
}
