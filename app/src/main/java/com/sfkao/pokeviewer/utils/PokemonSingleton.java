package com.sfkao.pokeviewer.utils;

import androidx.lifecycle.MutableLiveData;

import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;

public class PokemonSingleton {

    private static Pokemon pokemon;

    private static final MutableLiveData<Pokemon> pokemon2LiveData = new MutableLiveData<>();

    public static Pokemon getPokemon() {
        return pokemon2LiveData.getValue();
    }

    public static void setPokemon(Pokemon pokemon) {
        pokemon2LiveData.setValue(pokemon);
    }

    public static MutableLiveData<Pokemon> getPokemon2LiveData(){
        return pokemon2LiveData;
    }
}
