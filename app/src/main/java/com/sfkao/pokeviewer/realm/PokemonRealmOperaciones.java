package com.sfkao.pokeviewer.realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PokemonRealmOperaciones {

    public static void insertarPokemon(int id, String nombre, String img, String imgS){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PokemonRealm pokemonRealm = realm.createObject(PokemonRealm.class,id);
                pokemonRealm.setNombre(nombre);
                pokemonRealm.setImg(img);
                pokemonRealm.setImgS(imgS);
            }
        });
    }

    public static void insertarPokemon(PokemonRealm pokemonRealm){
        Realm.getDefaultInstance().insertOrUpdate(pokemonRealm);
    }

    public static void actualizarPokemon(int id, String nombre, String img, String imgS){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PokemonRealm pokemonRealm = realm.where(PokemonRealm.class).equalTo("id",id).findFirst();
                pokemonRealm.setNombre(nombre);
                pokemonRealm.setImg(img);
                pokemonRealm.setImgS(imgS);
            }
        });
    }

    public static ArrayList<PokemonRealm> getPokemons(){
        final ArrayList[] pokemonRealms = new ArrayList[]{null};
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<PokemonRealm> all = realm.where(PokemonRealm.class).findAll();
                pokemonRealms[0] = new ArrayList<>();
                pokemonRealms[0].addAll(all);
            }
        });
        return pokemonRealms[0];
    }


    public static void borrarPokemon(int id){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PokemonRealm pk= realm.where(PokemonRealm.class).equalTo("id", id).findFirst();
                if(pk!=null)
                    pk.deleteFromRealm();
            }
        });
    }

    public static void isNecesario(int id){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PokemonRealm pk= realm.where(PokemonRealm.class).equalTo("id", id).findFirst();
                if(pk==null)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk1",id).count()!=0)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk2",id).count()!=0)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk3",id).count()!=0)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk4",id).count()!=0)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk5",id).count()!=0)
                    return;
                if(realm.where(EquipoRealm.class).equalTo("pk6",id).count()!=0)
                    return;
                pk.deleteFromRealm();
            }
        });
    }

}
