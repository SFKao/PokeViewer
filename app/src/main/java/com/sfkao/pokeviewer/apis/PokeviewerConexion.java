package com.sfkao.pokeviewer.apis;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_login.LoginResponse;
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

import javax.crypto.Cipher;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeviewerConexion {

    private static final PokeviewerConexion api = new PokeviewerConexion();

    public static PokeviewerConexion getInstance(){
        return api;
    }

    //Obtener pokemon a partir del nombre
    public Login.User login(String nombre, String pass, Context loginActivity) throws Exception {

        //Uso gson para insertar los datos en mi POJO
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PokeviewerConnexionInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        byte[] codigicada = encode(pass,loginActivity);
        PokeviewerConnexionInterface service = retrofit.create(PokeviewerConnexionInterface.class);
        Call<LoginResponse> callSync = service.login(nombre, codigicada);
        //Hago la llamada
        try{
            retrofit2.Response<LoginResponse> response = callSync.execute();
            LoginResponse loginResponse = response.body();
            Log.d("PROBANDO",loginResponse.getResponse());
            Log.d("PROBANDO", String.valueOf(loginResponse.getCode()));
            Log.d("PROBANDO",loginResponse.getUsuario().toString());

            if(loginResponse.getUsuario()!=null){
                return (loginResponse.getUsuario().toUser());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    /*
    private String encode(String toEncode,Context loginActivity) throws Exception {
        PublicKey publicKey = loadPublicKey(loginActivity);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        String encode = new String(Base64.getEncoder().encode(bytes));
        Log.d("PROBANDO",encode);
        return (encode);
    }

     */

    private byte[] encode(String toEncode,Context loginActivity) throws Exception {
        PublicKey publicKey = loadPublicKey(loginActivity);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encode(bytes);
    }


}
