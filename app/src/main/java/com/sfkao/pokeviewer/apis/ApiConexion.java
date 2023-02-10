package com.sfkao.pokeviewer.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton para la conexion a la API
 */
public class ApiConexion {

    private static final ApiConexion api = new ApiConexion();

    public static ApiConexion getInstance(){
        return api;
    }

    private static Retrofit retrofit;
    private static Retrofit getRetrofit(){
        if(retrofit==null) {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().baseUrl(ApiUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
    
    //Obtener pokemon a partir del nombre
    public Pokemon getPokemon(String nombre){

        ApiUtils service = getRetrofit().create(ApiUtils.class);

        Call<Pokemon> callSync = service.getPokemon(nombre.toLowerCase(Locale.ROOT));
        //Hago la llamada
        try{
            retrofit2.Response<Pokemon> response = callSync.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pokemon getPokemon(int num){
        if(num == 0)
            return null;

        ApiUtils service = getRetrofit().create(ApiUtils.class);

        Call<Pokemon> callSync = service.getPokemon(String.valueOf(num));
        //Hago la llamada
        try{
            retrofit2.Response<Pokemon> response = callSync.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Obtener el tipo a partir de su nombre
    public Tipo getTipo(String nombre){
        
        ApiUtils service = getRetrofit().create(ApiUtils.class);

        Call<Tipo> callSync = service.getTipo(nombre);
        //Hago la llamada
        try{
            retrofit2.Response<Tipo> response = callSync.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
