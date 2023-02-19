package com.sfkao.pokeviewer.apis;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_login.LoginResponse;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;
import com.sfkao.pokeviewer.realm.EquipoRealm;
import com.sfkao.pokeviewer.realm.EquipoRealmOperaciones;
import com.sfkao.pokeviewer.utils.Login;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeviewerConexion {

    private static final PokeviewerConexion api = new PokeviewerConexion();

    public static PokeviewerConexion getInstance(){
        return api;
    }
    
    private static Retrofit retrofit;
    private static Retrofit getRetrofit(){
        if(retrofit==null) {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().baseUrl(PokeviewerConnexionInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }

    //Obtener login
    public Login.User login(String nombre, String pass, Context loginActivity) throws LoginException, SecurityException {
        
        byte[] codigicada;
        try {
            codigicada = encode(pass,loginActivity);
        } catch (Exception e) {
            throw new SecurityException(loginActivity.getString(R.string.no_se_pudo_encriptar));
        }
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<LoginResponse> callSync = service.login(nombre, codigicada);
        //Hago la llamada
        try{
            retrofit2.Response<LoginResponse> response = callSync.execute();
            LoginResponse loginResponse = response.body();
            if(loginResponse.getUsuario()!=null){
                return (loginResponse.getUsuario().toUser());
            }
            throw new LoginException(loginResponse.getResponse());
        } catch (IOException e) {
            throw new SecurityException(loginActivity.getString(R.string.no_se_pudo_ejecutar_la_llamada));
        }
    }

    public Login.User register(String nombre, String pass, String email, Context registerActivity) throws LoginException, SecurityException {
        byte[] codigicada;
        try {
            codigicada = encode(pass,registerActivity);
        } catch (Exception e) {
            throw new SecurityException(registerActivity.getString(R.string.no_se_pudo_encriptar));
        }
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<LoginResponse> callSync = service.register(nombre, codigicada,email);
        try{
            retrofit2.Response<LoginResponse> responde = callSync.execute();
            LoginResponse loginResponse = responde.body();
            if(loginResponse.getUsuario()!=null)
                return loginResponse.getUsuario().toUser();
            throw new LoginException(loginResponse.getResponse());
        }catch(IOException e){
            throw new SecurityException(registerActivity.getString(R.string.no_se_pudo_encriptar));
        }
    }

    public List<EquipoApi> getEquipos(int cantidad, int posInicial){
        
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<List<EquipoApi>> call;
        if(Login.isInvited())
            call = service.getEquipos(cantidad,posInicial);
        else
            call = service.getEquipos(cantidad,posInicial,Login.getUsuario().getApi_key());
        try{
            retrofit2.Response<List<EquipoApi>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EquipoForAdapterInterface subirEquipo(EquipoForAdapterInterface e){
        
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<EquipoApi> call = service.saveEquipo(
                e.getName(),
                Login.getUsuario().getApi_key(),
                e.getPokId(0),
                e.getPokId(1),
                e.getPokId(2),
                e.getPokId(3),
                e.getPokId(4),
                e.getPokId(5)
        );
        try{
            retrofit2.Response<EquipoApi> response = call.execute();
            EquipoApi equipoApi = response.body();
            EquipoRealmOperaciones.actualizaSubeEquipo((EquipoRealm) e,equipoApi.getApiId(),equipoApi.getUser());
            return e;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public EquipoApi getEquipo(String id){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<EquipoApi> call;
        if(Login.isInvited())
            call = service.getEquipoByID(id);
        else
            call = service.getEquipoByID(id,Login.getUsuario().getApi_key());
        try{
            retrofit2.Response<EquipoApi> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean borrarEquipo(String id){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.borrarEquipoByID(id);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean darLike(String id, String apikey){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.darLike(id,apikey);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean quitarLike(String id, String apikey){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.quitarLike(id,apikey);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean darFavorito(String id, String apikey){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.darFavorito(id,apikey);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean quitarFavorito(String id, String apikey){

        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.quitarFavorito(id,apikey);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setPokemonFavorito(String apikey, int pos, int id){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.setPokemonFavorito(apikey,pos,id);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AmigoApi> getAmigos(String apikey){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<List<AmigoApi>> call = service.getAmigos(apikey);
        try{
            retrofit2.Response<List<AmigoApi>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AmigoApi> getSolicitudesDeAmistad(String apikey){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<List<AmigoApi>> call = service.getSolicitudes(apikey);
        try{
            retrofit2.Response<List<AmigoApi>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AmigoApi buscarUsuario(String apikey,String username){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<AmigoApi> call = service.buscarUsuario(apikey,username);
        try{
            retrofit2.Response<AmigoApi> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean enviarPeticion(String apikey, String username){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.enviarSolicitud(apikey,username);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<EquipoApi> verEquiposDeAmigo(String apikey, String usernameamigo){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<List<EquipoApi>> call = service.verEquiposDeAmigo(apikey,usernameamigo);
        try{
            retrofit2.Response<List<EquipoApi>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean borrarAmigo(String apikey, String username){
        PokeviewerConnexionInterface service = getRetrofit().create(PokeviewerConnexionInterface.class);
        Call<Boolean> call = service.borrarAmigo(apikey,username);
        try{
            retrofit2.Response<Boolean> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private PublicKey loadPublicKey(Context loginActivity) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //byte[] keyBytes = Files.readAllBytes(Paths.get("/public_key.der"));
        InputStream i = loginActivity.getAssets().open("public_key.der");
        StringBuilder b = new StringBuilder();
        byte[] keyBytes = new byte[i.available()];
        i.read(keyBytes);
        for(byte bit : keyBytes){
            b.append(bit);
        }
        Log.d("PROBANDO",b.toString());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private byte[] encode(String toEncode,Context loginActivity) throws Exception {
        PublicKey publicKey = loadPublicKey(loginActivity);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encode(bytes);
    }


}
