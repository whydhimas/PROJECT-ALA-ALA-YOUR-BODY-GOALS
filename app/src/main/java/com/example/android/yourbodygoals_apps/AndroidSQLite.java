package com.example.android.yourbodygoals_apps;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Yosafat Dhimas on 04/05/2018.
 */

public class AndroidSQLite extends Activity {
    private SQLiteAdapter mySQLiteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_developer);
        ListView listContent = (ListView)findViewById(R.id.contentlist);

      /*
       *  membuka SQLite database
       *  dan mengisinya dengan konten dummy
       *  setelah itu di close
       */
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        mySQLiteAdapter.deleteAll();

        mySQLiteAdapter.insert("EGA FIRMANSYAH H. \n1202150031");
        mySQLiteAdapter.insert("FARHAN RIZKY F. \n1202152159");
        mySQLiteAdapter.insert("RAHMANIA ARINA A. S. \n1202150271");
        mySQLiteAdapter.insert("VENESSA V. \n1202150247");
        mySQLiteAdapter.insert("YOSAFAT DHIMAS K. A. \n1202154119");

        mySQLiteAdapter.close();

      /*
       *  membuka SQLite database yang sama
       *  dan membaca semua konten data
       *  SIK DURUNG DIDANDANI
       */
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToRead();

        Cursor cursor = mySQLiteAdapter.queueAll();
        startManagingCursor(cursor);

        String[] from = new String[]{SQLiteAdapter.KEY_CONTENT};
        int[] to = new int[]{R.id.text};

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);

        listContent.setAdapter(cursorAdapter);

        mySQLiteAdapter.close();
    }
}
