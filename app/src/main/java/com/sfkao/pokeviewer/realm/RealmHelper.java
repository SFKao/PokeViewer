package com.sfkao.pokeviewer.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmHelper {

    public static RealmConfiguration configuration;

    public static Realm realm = null;

    public static void initRealm(Context context){
        if(realm==null) {
            Realm.init(context);
            String realmName = "Pokeviewer";
            configuration = new RealmConfiguration.Builder()
                    .name(realmName)
                    .allowQueriesOnUiThread(true)
                    .allowWritesOnUiThread(true)
                    .compactOnLaunch()
                    .build();
            Realm.removeDefaultConfiguration();
            Realm.setDefaultConfiguration(configuration);
            realm = Realm.getDefaultInstance();
        }
    }
}
