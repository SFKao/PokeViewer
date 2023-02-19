
package com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_pokemon.PokemonsMinimal;
import com.sfkao.pokeviewer.utils.Login;

import java.io.Serializable;
import java.util.ArrayList;

public class AmigoApi implements Serializable {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("equipos")
    @Expose
    private ArrayList<EquipoApi> equipos;
    @SerializedName("estadoAmistad")
    @Expose
    private String estadoAmistad;
    @SerializedName("pk1")
    @Expose
    private PokemonsMinimal pk1;
    @SerializedName("pk2")
    @Expose
    private PokemonsMinimal pk2;
    @SerializedName("pk3")
    @Expose
    private PokemonsMinimal pk3;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("favoritos")
    @Expose
    private int favoritos;

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

    public ArrayList<EquipoApi> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<EquipoApi> equipos) {
        this.equipos = equipos;
    }

    public String getEstadoAmistad() {
        return estadoAmistad;
    }

    public void setEstadoAmistad(String estadoAmistad) {
        this.estadoAmistad = estadoAmistad;
    }

    public PokemonsMinimal getPk1() {
        return pk1;
    }

    public void setPk1(PokemonsMinimal pk1) {
        this.pk1 = pk1;
    }

    public PokemonsMinimal getPk2() {
        return pk2;
    }

    public void setPk2(PokemonsMinimal pk2) {
        this.pk2 = pk2;
    }

    public PokemonsMinimal getPk3() {
        return pk3;
    }

    public void setPk3(PokemonsMinimal pk3) {
        this.pk3 = pk3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AmigoApi.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("apikey");
        sb.append('=');
        sb.append(((this.apikey == null)?"<null>":this.apikey));
        sb.append(',');
        sb.append("equipos");
        sb.append('=');
        sb.append(((this.equipos == null)?"<null>":this.equipos));
        sb.append(',');
        sb.append("estadoAmistad");
        sb.append('=');
        sb.append(((this.estadoAmistad == null)?"<null>":this.estadoAmistad));
        sb.append(',');
        sb.append("pk1");
        sb.append('=');
        sb.append(((this.pk1 == null)?"<null>":this.pk1));
        sb.append(',');
        sb.append("pk2");
        sb.append('=');
        sb.append(((this.pk2 == null)?"<null>":this.pk2));
        sb.append(',');
        sb.append("pk3");
        sb.append('=');
        sb.append(((this.pk3 == null)?"<null>":this.pk3));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }

    public Login.User toUser(){
        Login.User u = new Login.User();
        u.setUsername(username);
        u.setMail(email);
        u.setApi_key(apikey);
        u.setInvitado(false);
        u.setPk1(pk1.id);
        u.setPk2(pk2.id);
        u.setPk3(pk3.id);
        u.setPk1Image(pk1.img);
        u.setPk2Image(pk2.img);
        u.setPk3Image(pk3.img);
        return u;
    }
}
