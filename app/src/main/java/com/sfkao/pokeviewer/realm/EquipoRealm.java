package com.sfkao.pokeviewer.realm;

import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class EquipoRealm extends RealmObject implements EquipoForAdapterInterface {

    @PrimaryKey
    String localId = ObjectId.get().toString();

    String apiId;

    @Required
    String nombreEquipo = "";

    String autor = null;

    PokemonRealm pk1 = new PokemonRealm();

    PokemonRealm pk2 = null,pk3 = null,pk4 = null,pk5 = null,pk6 = null;


    public EquipoRealm() {
    }

    public EquipoRealm(String nombreEquipo, String autor) {
        this.nombreEquipo = nombreEquipo;
        this.autor = autor;
    }

    public EquipoRealm(String nombreEquipo, String autor, PokemonRealm pk1, PokemonRealm pk2, PokemonRealm pk3, PokemonRealm pk4, PokemonRealm pk5, PokemonRealm pk6) {
        this.nombreEquipo = nombreEquipo;
        this.autor = autor;
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
        this.pk4 = pk4;
        this.pk5 = pk5;
        this.pk6 = pk6;
    }

    public EquipoRealm(String apiId, String nombreEquipo, String autor, PokemonRealm pk1, PokemonRealm pk2, PokemonRealm pk3, PokemonRealm pk4, PokemonRealm pk5, PokemonRealm pk6) {
        this.apiId = apiId;
        this.nombreEquipo = nombreEquipo;
        this.autor = autor;
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
        this.pk4 = pk4;
        this.pk5 = pk5;
        this.pk6 = pk6;
    }

    public EquipoRealm(EquipoRealm e){
        this.localId = e.localId;
        this.nombreEquipo = e.getName();
        this.apiId = e.getApiId();
        this.autor = e.getUser();
        this.pk1 = e.pk1;
        this.pk2 = e.pk2;
        this.pk3 = e.pk3;
        this.pk4 = e.pk4;
        this.pk5 = e.pk5;
        this.pk6 = e.pk6;
    }

    public EquipoRealm(EquipoForAdapterInterface e){
        this.nombreEquipo = e.getName();
        this.apiId = e.getApiId();
        this.autor = e.getUser();
        this.pk1 = new PokemonRealm(
                e.getPokId(0),
                e.getPokName(0),
                e.getPokImg(0),
                e.getPokSImg(0)
        );
        if(e.isPokemon(1))
            this.pk2 = new PokemonRealm(
                    e.getPokId(1),
                    e.getPokName(1),
                    e.getPokImg(1),
                    e.getPokSImg(1)
            );
        if(e.isPokemon(2))
            this.pk3 = new PokemonRealm(
                    e.getPokId(2),
                    e.getPokName(2),
                    e.getPokImg(2),
                    e.getPokSImg(2)
            );
        if(e.isPokemon(3))
            this.pk4 = new PokemonRealm(
                    e.getPokId(3),
                    e.getPokName(3),
                    e.getPokImg(3),
                    e.getPokSImg(3)
            );
        if(e.isPokemon(4))
            this.pk5 = new PokemonRealm(
                    e.getPokId(4),
                    e.getPokName(4),
                    e.getPokImg(4),
                    e.getPokSImg(4)
            );
        if(e.isPokemon(5))
            this.pk6 = new PokemonRealm(
                    e.getPokId(5),
                    e.getPokName(5),
                    e.getPokImg(5),
                    e.getPokSImg(5)
            );
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getApiId() {
        return apiId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
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

    public PokemonRealm getPokemon(int pos){
        switch (pos){
            case 0:
                return pk1;
            case 1:
                return pk2;
            case 2:
                return pk3;
            case 3:
                return pk4;
            case 4:
                return pk5;
            case 5:
                return pk6;
            default:
                return null;
        }
    }

    @Override
    public String getName() {
        return nombreEquipo;
    }

    @Override
    public String getUser() {
        return autor;
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
        switch (pok){
            case 0:
                return pk1.getImg();
            case 1:
                return pk2.getImg();
            case 2:
                return pk3.getImg();
            case 3:
                return pk4.getImg();
            case 4:
                return pk5.getImg();
            case 5:
                return pk6.getImg();

        }
        return null;
    }

    @Override
    public String getPokSImg(int pok) {
        switch (pok){
            case 0:
                return pk1.getImgS();
            case 1:
                return pk2.getImgS();
            case 2:
                return pk3.getImgS();
            case 3:
                return pk4.getImgS();
            case 4:
                return pk5.getImgS();
            case 5:
                return pk6.getImgS();

        }
        return null;
    }

    @Override
    public String getPokName(int pok) {
        switch (pok){
            case 0:
                return pk1.getNombre();
            case 1:
                return pk2.getNombre();
            case 2:
                return pk3.getNombre();
            case 3:
                return pk4.getNombre();
            case 4:
                return pk5.getNombre();
            case 5:
                return pk6.getNombre();

        }
        return "";
    }

    @Override
    public int getPokId(int pok) {
        switch (pok){
            case 0:
                return pk1.getId();
            case 1:
                return pk2.getId();
            case 2:
                return pk3.getId();
            case 3:
                return pk4.getId();
            case 4:
                return pk5.getId();
            case 5:
                return pk6.getId();
        }
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

    @Override
    public void setName(String s) {
        nombreEquipo = s;
    }

    @Override
    public void setUser(String s) {
        autor = s;
    }

    @Override
    public void setApiID(String s) {
        apiId = s;
    }

    @Override
    public void setPokemon(int pos, int id, String name, String img, String imgS) {
        switch (pos){
            case 0:
                pk1 = new PokemonRealm(id,name,img,imgS);
                break;
            case 1:
                pk2= new PokemonRealm(id,name,img,imgS);
                break;
            case 2:
                pk3= new PokemonRealm(id,name,img,imgS);
                break;
            case 3:
                pk4= new PokemonRealm(id,name,img,imgS);
                break;
            case 4:
                pk5= new PokemonRealm(id,name,img,imgS);
                break;
            case 5:
                pk6= new PokemonRealm(id,name,img,imgS);
        }
    }

    @Override
    public void setLikes(int l) {

    }

    @Override
    public void setFavs(int f) {

    }
}
