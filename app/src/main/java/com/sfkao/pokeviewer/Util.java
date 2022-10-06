package com.sfkao.pokeviewer;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.HashMap;

public class Util extends Application {


    public static HashMap<String, Drawable> diccionarioNombreAID;

    public static Drawable getType(String type){
        return diccionarioNombreAID.get(type);
    }




}
