package com.sfkao.pokeviewer.utils;

import android.content.Context;

import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.realm.EquipoRealmOperaciones;

import java.util.ArrayList;

/**
 * Singleton que almacena un pokemon para el fragmento de mis equipos
 */
public class EquipoSingleton {





    private static ArrayList<EquipoForAdapterInterface> equipos;
    private static final String FILENAME = "equipos.json";

    public static ArrayList<EquipoForAdapterInterface> getEquipos(){
        return equipos = EquipoRealmOperaciones.getEquiposForAdapter();
    }

    //Almacena los equipos en json en el dispositivo
    public static void guardarEquipos(Context context){

        /*
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

         */
    }

    //Carga los equipos de el archivo json si este existe
    public static ArrayList<EquipoForAdapterInterface> cargarEquipos(Context context) {

        equipos = EquipoRealmOperaciones.getEquiposForAdapter();
        return equipos;

        /*
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

         */
    }

}
