package com.sfkao.pokeviewer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.sfkao.pokeviewer.apis.PokeviewerConexion;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.security.auth.login.LoginException;

/**
 * Singleton que almacena y trabaja con el usuario logueado de la aplicacion
 */
public class Login {

    private static User usuario;

    public static User getUsuario() {
        return usuario;
    }

    public static void setUsuario(User usuario) {
        Login.usuario = usuario;
    }

    public static boolean tryLogin(String username, String password,Context context) throws LoginException {
        User login = PokeviewerConexion.getInstance().login(username,password,context);
        if(login!=null){
            usuario = login;
            String masterKeyAlias = null;
            try {
                masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            } catch (GeneralSecurityException | IOException e) {
                return true;
            }
            SharedPreferences sharedPreferences = null;
            try {
                sharedPreferences = EncryptedSharedPreferences.create(
                        "secret_shared_prefs_file",
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }

            SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putString("user",usuario.username);
            editor.putString("api",usuario.api_key);
            editor.putString("email",usuario.mail);
            editor.putString("pass",password);
            editor.putInt("pk1",usuario.pk1);
            editor.putInt("pk2",usuario.pk2);
            editor.putInt("pk3",usuario.pk3);
            editor.putString("pk1Image",usuario.pk1Image);
            editor.putString("pk2Image",usuario.pk2Image);
            editor.putString("pk3Image",usuario.pk3Image);
            editor.apply();
            return true;
        }
        return false;
    }

    public static boolean tryRegister(String username, String pass, String email, Context context) throws LoginException {
        User login = PokeviewerConexion.getInstance().register(username,pass,email,context);
        if(login!=null){
            usuario = login;
            String masterKeyAlias = null;
            try {
                masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            } catch (GeneralSecurityException | IOException e) {
                return true;
            }
            SharedPreferences sharedPreferences = null;
            try {
                sharedPreferences = EncryptedSharedPreferences.create(
                        "secret_shared_prefs_file",
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }

            SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putString("user",usuario.username);
            editor.putString("api",usuario.api_key);
            editor.putString("email",usuario.mail);
            editor.putString("pass",pass);
            editor.apply();
            return true;
        }
        return false;
    }

    //Cuando inicio sesion guardo el usuario.
    public static void saveUser(Context context) {
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException | IOException e) {
            return;
        }
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs_file",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("user",usuario.username);
        editor.putString("api",usuario.api_key);
        editor.putString("email",usuario.mail);
        editor.putInt("pk1",usuario.pk1);
        editor.putInt("pk2",usuario.pk2);
        editor.putInt("pk3",usuario.pk3);
        editor.putString("pk1Image",usuario.pk1Image);
        editor.putString("pk2Image",usuario.pk2Image);
        editor.putString("pk3Image",usuario.pk3Image);
        editor.apply();
    }

    public static String getUsername () {
        return usuario.username;
    }

    public static boolean isInvited () {
        return usuario.invitado;
    }

    //Cuando inicio la aplicacion llamo a este metodo para ver si existe un usuario almacenado
    public static User autoLogin (Context context) {

        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException | IOException e) {
            return logout(context);
        }
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs_file",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return logout(context);
        }

        String username = sharedPreferences.getString("user","");
        if(username.equals("")){
            logout(context);
            return usuario;
        }
        String email = sharedPreferences.getString("email","");
        String pass = sharedPreferences.getString("pass","");
        String api = sharedPreferences.getString("api","");
        int pk1 = sharedPreferences.getInt("pk1", 0);
        int pk2 = sharedPreferences.getInt("pk2", 0);
        int pk3 = sharedPreferences.getInt("pk3", 0);
        String pk1Image = sharedPreferences.getString("pk1Image", null);
        String pk2Image = sharedPreferences.getString("pk2Image", null);
        String pk3Image = sharedPreferences.getString("pk3Image", null);

        if(checkApikey(context,api)){
            User usuario = new User(username, email, api);
            usuario.setPk1(pk1);
            usuario.setPk2(pk2);
            usuario.setPk3(pk3);
            usuario.setPk1Image(pk1Image);
            usuario.setPk2Image(pk2Image);
            usuario.setPk3Image(pk3Image);
            setUsuario(usuario);
            usuario.invitado = false;
            return usuario;
        }else{
            try {
                if(tryLogin(username,pass,context)){
                    sharedPreferences.edit().putString("api",usuario.api_key).apply();
                }
            } catch (LoginException ignored) {

            }
        }
        return logout(context);
        /*
        if(usuario!=null)
            return usuario;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            usuario = gson.fromJson(isr, new TypeToken<User>() {
            }.getType());
            fis.close();
        } catch (IOException e) {
            logout(context);
        }
        if(usuario==null)
            return logout(context);
        return usuario;

         */
    }

    public static boolean checkApikey(Context context, String apikey){
        return true;
        //TODO: realizar esto en backend
    }

    //Cambia a invitado
    public static User logout (Context context) {
        usuario = new User();

        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs_file",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.clear();
        editor.apply();
        usuario.invitado = true;
        return usuario;
    }

    //Clase del usuario
    public static class User {
        private String username;
        private String mail;
        private String api_key;
        private boolean invitado;
        private int pk1,pk2,pk3;
        private String pk1Image, pk2Image, pk3Image;

        public User(String username, String mail, String api_key) {
            this.username = username;
            this.mail = mail;
            this.api_key = api_key;
            invitado = false;
        }

        public User(String username) {
            this.username = username;
            invitado = false;
        }

        public User() {
            this.username = "Invitado";
            invitado = true;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getApi_key() {
            return api_key;
        }

        public void setApi_key(String api_key) {
            this.api_key = api_key;
        }

        public boolean isInvitado() {
            return invitado;
        }

        public void setInvitado(boolean invitado) {
            this.invitado = invitado;
        }

        public int getPk1() {
            return pk1;
        }

        public void setPk1(int pk1) {
            this.pk1 = pk1;
        }

        public int getPk2() {
            return pk2;
        }

        public void setPk2(int pk2) {
            this.pk2 = pk2;
        }

        public int getPk3() {
            return pk3;
        }

        public void setPk3(int pk3) {
            this.pk3 = pk3;
        }

        public String getPk1Image() {
            return pk1Image;
        }

        public void setPk1Image(String pk1Image) {
            this.pk1Image = pk1Image;
        }

        public String getPk2Image() {
            return pk2Image;
        }

        public void setPk2Image(String pk2Image) {
            this.pk2Image = pk2Image;
        }

        public String getPk3Image() {
            return pk3Image;
        }

        public void setPk3Image(String pk3Image) {
            this.pk3Image = pk3Image;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", mail='" + mail + '\'' +
                    ", api_key='" + api_key + '\'' +
                    ", invitado=" + invitado +
                    '}';
        }
    }

}

