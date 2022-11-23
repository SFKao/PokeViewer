package com.sfkao.pokeviewer.utils;

import android.graphics.drawable.Drawable;

import com.sfkao.pokeviewer.modelo.pojo_tipos.DoubleDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.HalfDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.NoDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util  {


    public static HashMap<String, Drawable> diccionarioNombreAID;

    public static Drawable getType(String type){
        return diccionarioNombreAID.get(type);
    }

    public static Map<String,ArrayList<String>> calcularRelacionDeTipos(Tipo tipo1, Tipo tipo2){
        ArrayList<String> debilidades = new ArrayList<>();
        ArrayList<String> debilidadesX4 = new ArrayList<>();
        ArrayList<String> inmunidades = new ArrayList<>();
        ArrayList<String> resistencias = new ArrayList<>();
        ArrayList<String> resistenciasX4 = new ArrayList<>();


        for(DoubleDamageFrom d : tipo1.getDamageRelations().getDoubleDamageFrom()){
            debilidades.add(d.getName());
        }

        for(HalfDamageFrom d : tipo1.getDamageRelations().getHalfDamageFrom()){
            resistencias.add(d.getName());
        }

        for(NoDamageFrom d : tipo1.getDamageRelations().getNoDamageFrom()){
            inmunidades.add(d.getName());
        }



        if(tipo2 != null) {
            for(NoDamageFrom d : tipo2.getDamageRelations().getNoDamageFrom()){
                if(!inmunidades.contains(d.getName())) {
                    inmunidades.add(d.getName());
                    debilidades.remove(d.getName());
                    resistencias.remove(d.getName());
                }
            }

            for(DoubleDamageFrom d : tipo2.getDamageRelations().getDoubleDamageFrom()){
                String nombre = d.getName();
                if(debilidades.contains(nombre)) {
                    debilidades.remove(nombre);
                    debilidadesX4.add(nombre);
                }else if(resistencias.contains(nombre)){
                    resistencias.remove(nombre);
                }else if(!inmunidades.contains(nombre)){
                    debilidades.add(nombre);
                }
            }

            for(HalfDamageFrom d : tipo2.getDamageRelations().getHalfDamageFrom()){
                String nombre = d.getName();
                if(resistencias.contains(nombre)) {
                    resistencias.remove(nombre);
                    resistenciasX4.add(nombre);
                }else if(debilidades.contains(nombre)){
                    debilidades.remove(nombre);
                }else if(!inmunidades.contains(nombre)){
                    resistencias.add(nombre);
                }
            }

        }

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("debilidades",debilidades);
        map.put("debilidadesX4",debilidadesX4);
        map.put("resistencias",resistencias);
        map.put("resistenciasX4",resistenciasX4);
        map.put("inmunidades",inmunidades);
        return map;
    }


}
