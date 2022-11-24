package com.sfkao.pokeviewer.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sfkao.pokeviewer.modelo.Equipo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EquipoSingleton {

    private static ArrayList<Equipo> equipos;
    private static final String FILENAME = "equipos.json";

    public static ArrayList<Equipo> getEquipos(){
        return equipos;
    }

    public static void guardarEquipos(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(equipos, osw);
            osw.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Equipo> cargarEquipos(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            equipos = gson.fromJson(isr, new TypeToken<ArrayList<Equipo>>() {}.getType());
            fis.close();
        } catch (IOException e) {
            equipos = new ArrayList<Equipo>();
        }
        return equipos;
    }


}
