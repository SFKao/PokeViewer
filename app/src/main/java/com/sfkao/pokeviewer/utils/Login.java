package com.sfkao.pokeviewer.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Singleton que almacena y trabaja con el usuario logueado de la aplicacion
 */
public class Login {

    private static final String FILENAME = "user.json";


    private static User usuario;

    public static User getUsuario() {
        return usuario;
    }

    public static void setUsuario(User usuario) {
        Login.usuario = usuario;
    }

    public static boolean tryLogin(String username, String password,Context context) {
        //Aqui tendriamos que checkear la bbdd
        usuario = new User(username);
        saveUser(context);
        return true;
    }

    //Cuando inicio sesion guardo el usuario.
    private static void saveUser(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(usuario, osw);
            osw.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUsername () {
        return usuario.username;
    }

    public static boolean isInvited () {
        return usuario.invitado;
    }

    //Cuando inicio la aplicacion llamo a este metodo para ver si existe un usuario almacenado
    public static User autoLogin (Context context) {
        if(usuario!=null)
            return usuario;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            usuario = gson.fromJson(isr, new TypeToken<User>() {
            }.getType());
            fis.close();
            usuario.invitado = false;
        } catch (IOException e) {
            logout(context);
        }
        return usuario;
    }

    //Cambia a invitado
    public static User logout (Context context) {
        usuario = new User("Invitado", "", "");
        usuario.invitado = true;
        saveUser(context);
        return usuario;
    }

    //Clase del usuario
    public static class User {
        private String username;
        private String mail;
        private String api_key;
        private boolean invitado;

        public User(String username, String mail, String api_key) {
            this.username = username;
            this.mail = mail;
            this.api_key = api_key;
            invitado = true;
        }

        public User(String username) {
            this.username = username;
            invitado = false;
        }

        public User() {
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

