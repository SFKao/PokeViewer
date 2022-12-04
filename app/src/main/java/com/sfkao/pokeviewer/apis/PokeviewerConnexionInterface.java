package com.sfkao.pokeviewer.apis;

import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_login.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeviewerConnexionInterface {

    //Endpoint
    String BASE_URL = "http://sfkao.bounceme.net:25577/";

    //Obtener pokemon a partir del nombre (o numero)
    //?usuario={username}&pass={pass}
    @GET("login")
    Call<LoginResponse> login(@Query("usuario") String username,
                              @Query("pass") String pass);

    @GET("login")
    Call<LoginResponse> login(@Query("usuario") String username,
                              @Query("pass") byte[] pass);

    @GET("register")
    Call<LoginResponse> register(@Query("usuario")String username,
                                 @Query("pass")byte[] pass,
                                 @Query("email")String email);

    @GET("save_equipo")
    Call<EquipoApi> saveEquipo(
            @Query("nombre")String nombre,
            @Query("apikey")String apikey,
            @Query("pk1")int pk1,
            @Query("pk2")int pk2,
            @Query("pk3")int pk3,
            @Query("pk4")int pk4,
            @Query("pk5")int pk5,
            @Query("pk6")int pk6
    );

    @GET("get_equipo")
    Call<EquipoApi> getEquipoByID(
            @Query("id")String id
    );

    @GET("get_equipos")
    Call<List<EquipoApi>> getEquipos(
            @Query("cantidad")int cantidad,
            @Query("posInicial")int posInicial
    );


}
