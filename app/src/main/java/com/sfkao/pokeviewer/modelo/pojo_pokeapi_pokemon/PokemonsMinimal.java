package com.sfkao.pokeviewer.modelo.pojo_pokeapi_pokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonsMinimal {
    @Expose
    @SerializedName("imgS")
    public String imgs;
    @Expose
    @SerializedName("img")
    public String img;
    @Expose
    @SerializedName("name")
    public String name;
    @Expose
    @SerializedName("id")
    public int id;

}
