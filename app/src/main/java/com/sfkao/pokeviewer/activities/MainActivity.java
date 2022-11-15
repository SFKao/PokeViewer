package com.sfkao.pokeviewer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.adapters.SearchPokemonPagerAdapter;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.Pokemon;
import com.sfkao.pokeviewer.utils.Util;
import com.sfkao.pokeviewer.adapters.WeaknessAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Button botonBuscar;
    ImageView imagePokemon;
    EditText textoPokemon;

    TextView textError;

    ImageView imageTipoIzquierda, imageTipoMedio, imageTipoDerecha;

    ApiConexion apiConexion;

    //RecyclerView recyclerDebilidades;
    RecyclerView.Adapter recyclerDebilidadesAdapter;
    //RecyclerView recyclerInmunidades;
    RecyclerView.Adapter recyclerInmunidadesAdapter;
    //RecyclerView recyclerResistencias;
    RecyclerView.Adapter recyclerResistenciasAdapter;


    ViewPager2 buscadorDatos;
    SearchPokemonPagerAdapter buscadorDatosAdapter;
    TabLayout tabBuscadorDatos;

    TextView habilidad1, habilidad2,habilidadOculta;
    TextView psText, ataqueText, defensaText, sAtaqueText, sDefensaText, velocidadText;
    ProgressBar psBar, ataqueBar, defensaBar, sAtaqueBar, sDefensaBar, velocidadBar;

    Pokemon pokemonMostrado;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        apiConexion = new ApiConexion(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        botonBuscar = (Button) findViewById(R.id.buttonSearchPokemon);
        imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        textoPokemon = (EditText) findViewById(R.id.textfieldPokemonNameOrNumber);

        imageTipoIzquierda = (ImageView) findViewById(R.id.imageTypeLeft);
        imageTipoMedio = (ImageView) findViewById(R.id.imageTypeMiddle);
        imageTipoDerecha = (ImageView) findViewById(R.id.imageTypeRight);

        textError = (TextView) findViewById(R.id.textError);

        tabBuscadorDatos = findViewById(R.id.tabMainActivity);

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

        buscadorDatos = findViewById(R.id.datosPokemonPager);
        buscadorDatosAdapter = new SearchPokemonPagerAdapter(this);
        buscadorDatos.setAdapter(buscadorDatosAdapter);
        new TabLayoutMediator(tabBuscadorDatos,buscadorDatos,true,true,(tab, position) -> tab.setText(null)).attach();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.buscador_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mostrarDrawerBarButton) {
            return true;
        } else if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    buscar();
                }
        });

        textoPokemon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscar();
                return false;
            }
        });
    }

    private void buscar(){
        apiConexion.buscarPokemonYMostrar(String.valueOf(textoPokemon.getText()));
    }

    public void imprimirPokemon(Pokemon pokemon){

        //GuardarImagen en local y llamar a que se imprima al crear el fragment. Dividir esto en metodos

        pokemonMostrado = pokemon;

        Picasso.get().load(pokemon.getUrlFoto()).into(imagePokemon);
        if(pokemon.getTipo2()==null){
            imageTipoMedio.setImageDrawable(Util.getType(pokemon.getTipo1()));
            imageTipoDerecha.setVisibility(View.INVISIBLE);
            imageTipoIzquierda.setVisibility(View.INVISIBLE);
            imageTipoMedio.setVisibility(View.VISIBLE);
        }else{
            imageTipoIzquierda.setImageDrawable(Util.getType(pokemon.getTipo1()));
            imageTipoDerecha.setImageDrawable(Util.getType(pokemon.getTipo2()));
            imageTipoDerecha.setVisibility(View.VISIBLE);
            imageTipoIzquierda.setVisibility(View.VISIBLE);
            imageTipoMedio.setVisibility(View.INVISIBLE);
        }


        ((WeaknessAdapter)recyclerDebilidadesAdapter).setTipos(new ArrayList<>(pokemon.getDebilidades()));
        if(pokemon.getDobleDebilidades() != null && pokemon.getDobleDebilidades().size()!=0) {
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().addAll(pokemon.getDobleDebilidades());
        }
        recyclerDebilidadesAdapter.notifyDataSetChanged();
        ((WeaknessAdapter)recyclerInmunidadesAdapter).setTipos(new ArrayList<>(pokemon.getInmunidades()));
        recyclerInmunidadesAdapter.notifyDataSetChanged();
        ((WeaknessAdapter)recyclerResistenciasAdapter).setTipos(new ArrayList<>(pokemon.getResistencias()));
        if(pokemon.getDobleResistencias() != null && pokemon.getDobleResistencias().size()!=0) {
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().addAll(pokemon.getDobleResistencias());
        }
        recyclerResistenciasAdapter.notifyDataSetChanged();

        imprimirCaracteristicas(pokemon);

    }

    public void imprimirCaracteristicas(Pokemon pokemon){
        if(pokemon == null)
            return;
        if(psText==null)
            return;
        psText.setText(String.valueOf(pokemon.getPs()));
        psBar.setProgress(pokemon.getPs());
        ataqueText.setText(String.valueOf(pokemon.getAtk()));
        ataqueBar.setProgress(pokemon.getAtk());
        defensaText.setText(String.valueOf(pokemon.getDef()));
        defensaBar.setProgress(pokemon.getDef());
        sAtaqueText.setText(String.valueOf(pokemon.getsAtk()));
        sAtaqueBar.setProgress(pokemon.getsAtk());
        sDefensaText.setText(String.valueOf(pokemon.getsDef()));
        sDefensaBar.setProgress(pokemon.getsDef());
        velocidadText.setText(String.valueOf(pokemon.getSpe()));
        velocidadBar.setProgress(pokemon.getSpe());

        habilidad1.setText(pokemon.getHabilidad1());
        if(pokemon.getHabilidad2()==null)
            habilidad2.setText("");
        else
            habilidad2.setText(pokemon.getHabilidad2());
        if(pokemon.getHabilidadOculta()==null)
            habilidadOculta.setText("");
        else
            habilidadOculta.setText(pokemon.getHabilidadOculta());


    }

    public void cargadasCaracteristicas(){
        if(pokemonMostrado != null)
            imprimirCaracteristicas(pokemonMostrado);
    }


    public void setRecyclerDebilidadesAdapter(RecyclerView.Adapter recyclerDebilidadesAdapter) {
        this.recyclerDebilidadesAdapter = recyclerDebilidadesAdapter;
    }

    public void setRecyclerInmunidadesAdapter(RecyclerView.Adapter recyclerInmunidadesAdapter) {
        this.recyclerInmunidadesAdapter = recyclerInmunidadesAdapter;
    }

    public void setRecyclerResistenciasAdapter(RecyclerView.Adapter recyclerResistenciasAdapter) {
        this.recyclerResistenciasAdapter = recyclerResistenciasAdapter;
    }

    public void setHabilidad1(TextView habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public void setHabilidad2(TextView habilidad2) {
        this.habilidad2 = habilidad2;
    }

    public void setHabilidadOculta(TextView habilidadOculta) {
        this.habilidadOculta = habilidadOculta;
    }

    public void setPsText(TextView psText) {
        this.psText = psText;
    }

    public void setAtaqueText(TextView ataqueText) {
        this.ataqueText = ataqueText;
    }

    public void setDefensaText(TextView defensaText) {
        this.defensaText = defensaText;
    }

    public void setsAtaqueText(TextView sAtaqueText) {
        this.sAtaqueText = sAtaqueText;
    }

    public void setsDefensaText(TextView sDefensaText) {
        this.sDefensaText = sDefensaText;
    }

    public void setVelocidadText(TextView velocidadText) {
        this.velocidadText = velocidadText;
    }

    public void setPsBar(ProgressBar psBar) {
        this.psBar = psBar;
    }

    public void setAtaqueBar(ProgressBar ataqueBar) {
        this.ataqueBar = ataqueBar;
    }

    public void setDefensaBar(ProgressBar defensaBar) {
        this.defensaBar = defensaBar;
    }

    public void setsAtaqueBar(ProgressBar sAtaqueBar) {
        this.sAtaqueBar = sAtaqueBar;
    }

    public void setsDefensaBar(ProgressBar sDefensaBar) {
        this.sDefensaBar = sDefensaBar;
    }

    public void setVelocidadBar(ProgressBar velocidadBar) {
        this.velocidadBar = velocidadBar;
    }
}