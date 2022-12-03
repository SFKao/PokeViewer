package com.sfkao.pokeviewer.apis;

import com.sfkao.pokeviewer.modelo.pojo_pokeapi_login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeviewerConnexionInterface {

    //Endpoint
    String BASE_URL = "http://10.0.2.2:8080/";

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


}
