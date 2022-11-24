package com.sfkao.pokeviewer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.fragment.BuscadorFragment;
import com.sfkao.pokeviewer.fragment.MisEquipos;
import com.sfkao.pokeviewer.utils.Login;
import com.sfkao.pokeviewer.utils.Util;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    ApiConexion apiConexion;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationView barraLateral;

    TextView nombreDeUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy( new StrictMode.ThreadPolicy.Builder().permitAll().build());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Util.diccionarioNombreAID = new HashMap<>();
        Util.diccionarioNombreAID.put("normal", ResourcesCompat.getDrawable(getResources(),R.drawable.normal,null));
        Util.diccionarioNombreAID.put("bug",ResourcesCompat.getDrawable(getResources(),R.drawable.bug,null));
        Util.diccionarioNombreAID.put("dark",ResourcesCompat.getDrawable(getResources(),R.drawable.dark,null));
        Util.diccionarioNombreAID.put("dragon",ResourcesCompat.getDrawable(getResources(),R.drawable.dragon,null));
        Util.diccionarioNombreAID.put("electric",ResourcesCompat.getDrawable(getResources(),R.drawable.electric,null));
        Util.diccionarioNombreAID.put("fairy",ResourcesCompat.getDrawable(getResources(),R.drawable.fairy,null));
        Util.diccionarioNombreAID.put("fighting",ResourcesCompat.getDrawable(getResources(),R.drawable.fighting,null));
        Util.diccionarioNombreAID.put("fire",ResourcesCompat.getDrawable(getResources(),R.drawable.fire,null));
        Util.diccionarioNombreAID.put("flying",ResourcesCompat.getDrawable(getResources(),R.drawable.flying,null));
        Util.diccionarioNombreAID.put("ghost",ResourcesCompat.getDrawable(getResources(),R.drawable.ghost,null));
        Util.diccionarioNombreAID.put("grass",ResourcesCompat.getDrawable(getResources(),R.drawable.grass,null));
        Util.diccionarioNombreAID.put("ground",ResourcesCompat.getDrawable(getResources(),R.drawable.ground,null));
        Util.diccionarioNombreAID.put("ice",ResourcesCompat.getDrawable(getResources(),R.drawable.ice,null));
        Util.diccionarioNombreAID.put("poison",ResourcesCompat.getDrawable(getResources(),R.drawable.poison,null));
        Util.diccionarioNombreAID.put("psychic",ResourcesCompat.getDrawable(getResources(),R.drawable.psychic,null));
        Util.diccionarioNombreAID.put("rock",ResourcesCompat.getDrawable(getResources(),R.drawable.rock,null));
        Util.diccionarioNombreAID.put("steel",ResourcesCompat.getDrawable(getResources(),R.drawable.steel,null));
        Util.diccionarioNombreAID.put("water",ResourcesCompat.getDrawable(getResources(),R.drawable.water,null));
        Util.diccionarioNombreAID.put("x4",ResourcesCompat.getDrawable(getResources(),R.drawable.x4,null));

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.buscador_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreDeUsuario = findViewById(R.id.nombreDeUsuarioText);

        barraLateral = findViewById(R.id.barra_lateral);
        barraLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_buscador:
                        changeFragment(new BuscadorFragment());
                        break;
                    case R.id.nav_mis_equipos:
                        changeFragment(new MisEquipos());
                        break;
                    case R.id.iniciar_sesion_bar:
                        irALogin();
                        if(!Login.isInvited()) {
                            barraLateral.getMenu().findItem(R.id.cerrar_sesion).setVisible(true);
                            barraLateral.getMenu().findItem(R.id.iniciar_sesion_bar).setVisible(false);
                        }
                        nombreDeUsuario.setText(Login.getUsername());
                        break;
                    case R.id.cerrar_sesion:
                        Login.logout();
                        barraLateral.getMenu().clear();
                        barraLateral.inflateMenu(R.menu.menu_tab_main);
                        barraLateral.getMenu().findItem(R.id.cerrar_sesion).setVisible(false);
                        barraLateral.getMenu().findItem(R.id.iniciar_sesion_bar).setVisible(true);

                        nombreDeUsuario.setText(Login.getUsername());
                        break;
                }
                drawerLayout.close();
                return true;
            }
        });
        if(Login.isInvited())
            barraLateral.getMenu().findItem(R.id.cerrar_sesion).setVisible(false);
        else
            barraLateral.getMenu().findItem(R.id.iniciar_sesion_bar).setVisible(false);
        nombreDeUsuario.setText(Login.getUsername());

    }

    private void irALogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mostrarDrawerBarButton) {
            return true;
        } else if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        changeFragment(new BuscadorFragment());
    }

    private void changeFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_main, f);
        ft.commit();
    }





}