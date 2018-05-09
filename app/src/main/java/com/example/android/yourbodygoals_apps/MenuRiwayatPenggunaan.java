package com.example.android.yourbodygoals_apps;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.core.view.View;

import java.util.ArrayList;

public class MenuRiwayatPenggunaan extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_riwayat_penggunaan);

        listView = (ListView) findViewById(R.id.list);
        String[] theHistory = {"24.618105 \nBerat badan Anda normal",
                                "24.221453 \nBerat badan Anda normal",
                                "18.732784 \nBerat badan Anda normal",
                                "31.955925 \nAnda obesitas kelas I",
                                "30.222221 \nAnda obesitas kelas I"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theHistory);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
        TextView tv = (TextView) view;
        tv.setTextColor(Color.WHITE);
        Toast.makeText(this, ""+tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
