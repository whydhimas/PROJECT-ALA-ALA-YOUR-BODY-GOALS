package com.example.android.yourbodygoals_apps;

/**
 * Created by Yosafat Dhimas on 27/04/2018.
 */

public class JenisOlahraga {
    private String judulJO;
    private String descJO;
    private final int imgJO;

    JenisOlahraga(String judul, String desc, int imageResource) {
        this.judulJO = judul;
        this.descJO = desc;
        this.imgJO = imageResource;
    }

    String getJudulJO() {
        return judulJO;
    }

    String getDescJO() {
        return descJO;
    }

    public int getImgJO(){
        return imgJO;
    }
}

