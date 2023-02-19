package com.sfkao.pokeviewer.apis;

import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_login.LoginResponse;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeviewerConnexionInterface {

    //Endpoint
    String BASE_URL = "http://sfkao.bounceme.net:25577/";

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
    @GET("get_equipo")
    Call<EquipoApi> getEquipoByID(
            @Query("id")String id,
            @Query("apikey")String apikey
    );

    @GET("get_equipos")
    Call<List<EquipoApi>> getEquipos(
            @Query("cantidad")int cantidad,
            @Query("posInicial")int posInicial
    );

    @GET("get_equipos")
    Call<List<EquipoApi>> getEquipos(
            @Query("cantidad")int cantidad,
            @Query("posInicial")int posInicial,
            @Query("apikey")String apikey
    );

    @GET("borrar_equipo")
    Call<Boolean> borrarEquipoByID(
            @Query("id")String id
    );

    @GET("dar_like")
    Call<Boolean> darLike(
            @Query("id")String id,
            @Query("apikey")String apikey
    );

    @GET("quitar_like")
    Call<Boolean> quitarLike(
            @Query("id")String id,
            @Query("apikey")String apikey
    );

    @GET("dar_favorito")
    Call<Boolean> darFavorito(
            @Query("id")String id,
            @Query("apikey")String apikey
    );

    @GET("quitar_favorito")
    Call<Boolean> quitarFavorito(
            @Query("id")String id,
            @Query("apikey")String apikey
    );

    @GET("set_favorito")
    Call<Boolean> setPokemonFavorito(
            @Query("apikey") String apikey,
            @Query("pos") int pos,
            @Query("id") int id
    );

    @GET("get_amigos")
    Call<List<AmigoApi>> getAmigos(
            @Query("apikey")String apikey
    );

    @GET("get_solicitudes_de_amistad")
    Call<List<AmigoApi>> getSolicitudes(
            @Query("apikey")String apikey
    );

    @GET("buscar_usuario")
    Call<AmigoApi> buscarUsuario(
            @Query("apikey")String apikey,
            @Query("username")String username
    );

    @GET("enviar_solicitud_de_amistad")
    Call<Boolean> enviarSolicitud(
            @Query("apikey")String apikey,
            @Query("username")String username
    );

    @GET("get_equipo_de_amigo")
    Call<List<EquipoApi>> verEquiposDeAmigo(
            @Query("apikey")String apikey,
            @Query("usernameamigo")String usernameamigo
    );

    @GET("borrar_amigo")
    Call<Boolean> borrarAmigo(
            @Query("apikey") String apikey,
            @Query("username")String username
    );



}
