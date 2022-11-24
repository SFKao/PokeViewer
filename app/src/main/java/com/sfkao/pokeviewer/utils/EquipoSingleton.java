package com.sfkao.pokeviewer.utils;

import com.sfkao.pokeviewer.modelo.Equipo;

import java.util.ArrayList;

public class EquipoSingleton {

    private static final ArrayList<Equipo> equipos = new ArrayList<>();

    public static ArrayList<Equipo> getEquipos(){
        return equipos;
    }


}
