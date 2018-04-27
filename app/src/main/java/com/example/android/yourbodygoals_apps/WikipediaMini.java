package com.example.android.yourbodygoals_apps;


import java.util.ArrayList;

/**
 * Created by Yosafat Dhimas on 22/04/2018.
 */

public class WikipediaMini {
    private String judul;
    private String desc;
    private final int img;

    WikipediaMini(String judul, String desc, int imageResource) {
        this.judul = judul;
        this.desc = desc;
        this.img = imageResource;
    }

    String getJudul() {
        return judul;
    }

    String getDesc() {
        return desc;
    }

    public int getImg(){
        return img;
    }
}
