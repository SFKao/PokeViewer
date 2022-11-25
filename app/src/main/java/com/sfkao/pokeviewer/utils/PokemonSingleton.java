package com.sfkao.pokeviewer.utils;

import androidx.lifecycle.MutableLiveData;

import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;

/**
 * Singleton para almacenar un pokemon para la busqueda de la vista de busqueda.
 * Contiene un mutable live data para que desde los distintos fragmentos se les notifique
 * cuando el pokemon ha sido modificado y que estos se actualicen.
 */
public class PokemonSingleton {

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
