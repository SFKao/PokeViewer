package com.sfkao.pokeviewer.realm;

import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class EquipoRealmOperaciones {

    public static void insertaOActualiza(EquipoRealm equipoRealm){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk1);
                if (equipoRealm.pk2!=null)
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk2);
                if (equipoRealm.pk3!=null)
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk3);
                if (equipoRealm.pk4!=null)
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk4);
                if (equipoRealm.pk5!=null)
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk5);
                if (equipoRealm.pk6!=null)
                PokemonRealmOperaciones.insertarPokemon(equipoRealm.pk6);
                realm.insertOrUpdate(equipoRealm);
            }
        });
    }

    public static void borrarEquipo(EquipoRealm equipoRealm){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                EquipoRealm e = realm.where(EquipoRealm.class).equalTo("localId",equipoRealm.localId).findFirst();
                if(e!=null)
                    e.deleteFromRealm();
            }
        });
    }

    public static ArrayList<EquipoRealm> getEquipos(){
        final ArrayList[] equipoRealms = new ArrayList[]{null};
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<EquipoRealm> all = realm.where(EquipoRealm.class).findAll().sort("localId");
                equipoRealms[0] = new ArrayList<>();
                equipoRealms[0].addAll(all);
            }
        });
        return equipoRealms[0];
    }

    public static ArrayList<EquipoForAdapterInterface> getEquiposForAdapter(){
        final ArrayList[] equipoRealms = new ArrayList[]{null};
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<EquipoRealm> all = realm.where(EquipoRealm.class).findAll();
                equipoRealms[0] = new ArrayList<>();
                equipoRealms[0].addAll(all);
            }
        });
        return equipoRealms[0];
    }

    public static EquipoRealm getEquipo(String id){
        final EquipoRealm[] e = {null};
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                e[0] = realm.where(EquipoRealm.class).equalTo("id",id).findFirst();
            }
        });
        return e[0];
    }

    public static void actualizaSubeEquipo(EquipoRealm e, String id, String user){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                e.setApiID(id);
                e.setUser(user);
            }
        });
    }

}
