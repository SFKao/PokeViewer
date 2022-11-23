package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.WeaknessAdapter;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;
import com.sfkao.pokeviewer.utils.PokemonSingleton;
import com.sfkao.pokeviewer.utils.Util;

import java.util.ArrayList;
import java.util.Map;

public class DebilidadesFragment extends Fragment {

    RecyclerView recyclerDebilidades;
    RecyclerView.Adapter recyclerDebilidadesAdapter;
    RecyclerView recyclerInmunidades;
    RecyclerView.Adapter recyclerInmunidadesAdapter;
    RecyclerView recyclerResistencias;
    RecyclerView.Adapter recyclerResistenciasAdapter;

    private MainActivity context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context;

    }

    public DebilidadesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debilidades, container, false);
    }



    public void mostrarDebilidadesPokemon(Pokemon pokemon){
        Tipo tipo1 = ApiConexion.getInstance().getTipo(pokemon.getTypes().get(0).getType().getName());
        Tipo tipo2 = null;
        if(pokemon.getTypes().size()!=1)
            tipo2 = ApiConexion.getInstance().getTipo(pokemon.getTypes().get(1).getType().getName());

        Map<String, ArrayList<String>> relaciones = Util.calcularRelacionDeTipos(tipo1, tipo2);

        ((WeaknessAdapter)recyclerDebilidadesAdapter).setTipos(relaciones.get("debilidades"));
        if(relaciones.get("debilidadesX4") != null && relaciones.get("debilidadesX4").size()!=0) {
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerDebilidadesAdapter).getTipos().addAll(relaciones.get("debilidadesX4"));
        }
        recyclerDebilidadesAdapter.notifyDataSetChanged();

        ((WeaknessAdapter)recyclerResistenciasAdapter).setTipos(relaciones.get("resistencias"));
        if(relaciones.get("resistenciasX4") != null && relaciones.get("resistenciasX4").size()!=0) {
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().add("x4");
            ((WeaknessAdapter) recyclerResistenciasAdapter).getTipos().addAll(relaciones.get("resistenciasX4"));
        }
        recyclerResistenciasAdapter.notifyDataSetChanged();

        ((WeaknessAdapter)recyclerInmunidadesAdapter).setTipos(relaciones.get("inmunidades"));
        recyclerInmunidadesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerDebilidades = (RecyclerView) requireView().findViewById(R.id.recyclerDebilidades);
        recyclerDebilidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(context);
        recyclerDebilidades.setLayoutManager(layoutManagerDebilidades);
        recyclerDebilidades.setAdapter(recyclerDebilidadesAdapter);

        recyclerInmunidades = (RecyclerView) requireView().findViewById(R.id.recyclerInmunidades);
        recyclerInmunidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerInmunidades = new LinearLayoutManager(context);
        recyclerInmunidades.setLayoutManager(layoutManagerInmunidades);
        recyclerInmunidades.setAdapter(recyclerInmunidadesAdapter);

        recyclerResistencias = (RecyclerView) requireView().findViewById(R.id.recyclerResistencias);
        recyclerResistenciasAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerResistencias = new LinearLayoutManager(context);
        recyclerResistencias.setLayoutManager(layoutManagerResistencias);
        recyclerResistencias.setAdapter(recyclerResistenciasAdapter);

        PokemonSingleton.getPokemon2LiveData().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon2) {
                mostrarDebilidadesPokemon(pokemon2);
            }
        });

        if(PokemonSingleton.getPokemon()!=null)
            mostrarDebilidadesPokemon(PokemonSingleton.getPokemon());
    }
}