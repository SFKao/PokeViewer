package com.sfkao.pokeviewer.apis;

import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiUtils {

    //Endpoint
    String BASE_URL = "https://pokeapi.co/api/v2/";

    //Obtener pokemon a partir del nombre (o numero)
    @GET("pokemon/{nombre}")
    Call<Pokemon> getPokemon(@Path("nombre") String nombre);

    //Obtener tipo a partir de su nombre (o id)
    @GET("type/{nombre}")
    Call<Tipo> getTipo(@Path("nombre") String nombre);


}
