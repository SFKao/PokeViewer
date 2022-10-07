package com.sfkao.pokeviewer;

import com.android.volley.toolbox.DiskBasedCache;
import com.sfkao.pokeviewer.activities.MainActivity;

import java.io.File;

public class Pasarela {

    public MainActivity mainActivity;

    public File getCacheDir() {
        return mainActivity.getCacheDir();
    }
}
