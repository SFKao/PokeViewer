package com.sfkao.pokeviewer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiConexion = new ApiConexion(this);

        botonBuscar = (Button) findViewById(R.id.buttonSearchPokemon);
        imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        textoPokemon = (EditText) findViewById(R.id.textfieldPokemonNameOrNumber);

        imageTipoIzquierda = (ImageView) findViewById(R.id.imageTypeLeft);
        imageTipoMedio = (ImageView) findViewById(R.id.imageTypeMiddle);
        imageTipoDerecha = (ImageView) findViewById(R.id.imageTypeRight);

        textError = (TextView) findViewById(R.id.textError);

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


        /*
        recyclerDebilidades = (RecyclerView) findViewById(R.id.recyclerDebilidades);
        recyclerDebilidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(this);
        recyclerDebilidades.setLayoutManager(layoutManagerDebilidades);
        recyclerDebilidades.setAdapter(recyclerDebilidadesAdapter);

        recyclerInmunidades = (RecyclerView) findViewById(R.id.recyclerInmunidades);
        recyclerInmunidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerInmunidades = new LinearLayoutManager(this);
        recyclerInmunidades.setLayoutManager(layoutManagerInmunidades);
        recyclerInmunidades.setAdapter(recyclerInmunidadesAdapter);

        recyclerResistencias = (RecyclerView) findViewById(R.id.recyclerResistencias);
        recyclerResistenciasAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerResistencias = new LinearLayoutManager(this);
        recyclerResistencias.setLayoutManager(layoutManagerResistencias);
        recyclerResistencias.setAdapter(recyclerResistenciasAdapter);
        */

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

    public RecyclerView.Adapter getRecyclerDebilidadesAdapter() {
        return recyclerDebilidadesAdapter;
    }

    public void setRecyclerDebilidadesAdapter(RecyclerView.Adapter recyclerDebilidadesAdapter) {
        this.recyclerDebilidadesAdapter = recyclerDebilidadesAdapter;
    }

    public RecyclerView.Adapter getRecyclerInmunidadesAdapter() {
        return recyclerInmunidadesAdapter;
    }

    public void setRecyclerInmunidadesAdapter(RecyclerView.Adapter recyclerInmunidadesAdapter) {
        this.recyclerInmunidadesAdapter = recyclerInmunidadesAdapter;
    }

    public RecyclerView.Adapter getRecyclerResistenciasAdapter() {
        return recyclerResistenciasAdapter;
    }

    public void setRecyclerResistenciasAdapter(RecyclerView.Adapter recyclerResistenciasAdapter) {
        this.recyclerResistenciasAdapter = recyclerResistenciasAdapter;
    }

    public void imprimirPokemon(Pokemon pokemon){

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
        if(pokemon.getDobleDebilidades() != null) {
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().addAll(pokemon.getDobleDebilidades());
        }
        recyclerDebilidadesAdapter.notifyDataSetChanged();
        ((WeaknessAdapter)recyclerInmunidadesAdapter).setTipos(new ArrayList<>(pokemon.getInmunidades()));
        recyclerInmunidadesAdapter.notifyDataSetChanged();
        ((WeaknessAdapter)recyclerResistenciasAdapter).setTipos(new ArrayList<>(pokemon.getResistencias()));
        if(pokemon.getDobleResistencias() != null) {
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().addAll(pokemon.getDobleResistencias());
        }
        recyclerResistenciasAdapter.notifyDataSetChanged();


    }


}