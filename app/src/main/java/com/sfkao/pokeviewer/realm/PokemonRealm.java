package com.sfkao.pokeviewer.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PokemonRealm extends RealmObject {

    @PrimaryKey
    private int id = 0;

    private String nombre = "";

    private String img = "";

    private String imgS = "";

    public PokemonRealm() {
    }

    public PokemonRealm(int id, String nombre, String img, String imgS) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.imgS = imgS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgS() {
        return imgS;
    }

    public void setImgS(String imgS) {
        this.imgS = imgS;
    }
}
