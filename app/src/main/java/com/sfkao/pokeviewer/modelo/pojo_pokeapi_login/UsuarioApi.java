package com.sfkao.pokeviewer.modelo.pojo_pokeapi_login;

import com.sfkao.pokeviewer.modelo.Equipo;
import com.sfkao.pokeviewer.utils.Login;

import java.util.ArrayList;
import java.util.Objects;

public class UsuarioApi {

    private String username;
    private String email;
    private String apikey;

    private ArrayList<Equipo> equipos;

    public UsuarioApi(String username, String email, String apikey) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioApi usuario = (UsuarioApi) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", apikey='" + apikey + '\'' +
                '}';
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Login.User toUser() {
        return new Login.User(username,email,apikey);
    }
}
