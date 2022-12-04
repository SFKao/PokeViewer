package com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
