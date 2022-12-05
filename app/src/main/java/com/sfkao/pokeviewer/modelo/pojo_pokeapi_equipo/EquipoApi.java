package com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.Equipo;

public class EquipoApi {

    @Expose
    @SerializedName("pokemon6")
    public int pokemon6;
    @Expose
    @SerializedName("pokemon5")
    public int pokemon5;
    @Expose
    @SerializedName("pokemon4")
    public int pokemon4;
    @Expose
    @SerializedName("pokemon3")
    public int pokemon3;
    @Expose
    @SerializedName("pokemon2")
    public int pokemon2;
    @Expose
    @SerializedName("pokemon1")
    public int pokemon1;
    @Expose
    @SerializedName("fecha")
    public String fecha;
    @Expose
    @SerializedName("usuario")
    public Usuario usuario;
    @Expose
    @SerializedName("nombre")
    public String nombre;
    @Expose
    @SerializedName("id")
    public String id;

    public static class Usuario {
        @Expose
        @SerializedName("username")
        public String username;
    }

    public Equipo load(){
        Equipo e = new Equipo();

        Thread[] t = new Thread[6];
        int[] pokemons = new int[6];
        pokemons[0] = pokemon1;
        pokemons[1] = pokemon2;
        pokemons[2] = pokemon3;
        pokemons[3] = pokemon4;
        pokemons[4] = pokemon5;
        pokemons[5] = pokemon6;
        e.setNombre(nombre);
        e.setAutor(usuario.username);
        e.setIdentificador(id);

        for(int i = 0; i < 6; i++){
            int finalI = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    e.setPokemon(ApiConexion.getInstance().getPokemon(pokemons[finalI]), finalI);
                }
            });
            t[i].start();
        }

        try{
            for (Thread tr:t) {
                tr.join();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon1),0);
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon2),1);
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon3),2);
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon4),3);
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon5),4);
        e.setPokemon(ApiConexion.getInstance().getPokemon(pokemon6),5);

         */
        return e;
    }
}
