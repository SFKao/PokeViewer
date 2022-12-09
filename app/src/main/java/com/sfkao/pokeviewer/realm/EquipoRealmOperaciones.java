package com.sfkao.pokeviewer.realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class EquipoRealmOperaciones {

    public static void insertaOActualiza(EquipoRealm equipoRealm){
        Realm.getDefaultInstance().insertOrUpdate(equipoRealm);
    }

    public static void borrarEquipo(EquipoRealm equipoRealm){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                EquipoRealm e = realm.where(EquipoRealm.class).equalTo("id",equipoRealm.id).findFirst();
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
                RealmResults<EquipoRealm> all = realm.where(EquipoRealm.class).findAll();
                equipoRealms[0] = new ArrayList<>();
                equipoRealms[0].addAll(all);
            }
        });
        return equipoRealms[0];
    }



}
