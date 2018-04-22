package com.example.android.yourbodygoals_apps;


import java.util.ArrayList;

/**
 * Created by Yosafat Dhimas on 22/04/2018.
 */

public class WikipediaMini {
    private String judul;
    private String desc;

    WikipediaMini(String judul, String desc) {
        this.judul = judul;
        this.desc = desc;
    }

    String getJudul() {
        return judul;
    }

    String getDesc() {
        return desc;
    }
}
