package com.sfkao.pokeviewer.realm;

import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class EquipoRealm extends RealmObject implements EquipoForAdapterInterface {

    @PrimaryKey
    String id = ObjectId.get().toString();

    @Required
    String nombre = "";

    String autor = null;

    @Required
    PokemonRealm pk1 = new PokemonRealm();

    PokemonRealm pk2,pk3,pk4,pk5,pk6;


    public EquipoRealm() {
    }

    public EquipoRealm(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
    }

    public EquipoRealm(String nombre, String autor, PokemonRealm pk1, PokemonRealm pk2, PokemonRealm pk3, PokemonRealm pk4, PokemonRealm pk5, PokemonRealm pk6) {
        this.nombre = nombre;
        this.autor = autor;
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
        this.pk4 = pk4;
        this.pk5 = pk5;
        this.pk6 = pk6;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PokemonRealm getPk1() {
        return pk1;
    }

    public void setPk1(PokemonRealm pk1) {
        this.pk1 = pk1;
    }

    public PokemonRealm getPk2() {
        return pk2;
    }

    public void setPk2(PokemonRealm pk2) {
        this.pk2 = pk2;
    }

    public PokemonRealm getPk3() {
        return pk3;
    }

    public void setPk3(PokemonRealm pk3) {
        this.pk3 = pk3;
    }

    public PokemonRealm getPk4() {
        return pk4;
    }

    public void setPk4(PokemonRealm pk4) {
        this.pk4 = pk4;
    }

    public PokemonRealm getPk5() {
        return pk5;
    }

    public void setPk5(PokemonRealm pk5) {
        this.pk5 = pk5;
    }

    public PokemonRealm getPk6() {
        return pk6;
    }

    public void setPk6(PokemonRealm pk6) {
        this.pk6 = pk6;
    }

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public String getUser() {
        return "Local";
    }

    @Override
    public boolean isPokemon(int pok) {
        switch (pok){
            case 0:
                return pk1!=null;
            case 1:
                return pk2!=null;
            case 2:
                return pk3!=null;
            case 3:
                return pk4!=null;
            case 4:
                return pk5!=null;
            case 5:
                return pk6!=null;

        }
        return false;
    }

    @Override
    public String getPokImg(int pok) {
        return null;
    }

    @Override
    public String getPokSImg(int pok) {
        return null;
    }

    @Override
    public String getPokName(int pok) {
        return null;
    }

    @Override
    public int getPokId(int pok) {
        return 0;
    }

    @Override
    public int getLikes() {
        return 0;
    }

    @Override
    public int getFavs() {
        return 0;
    }
}
