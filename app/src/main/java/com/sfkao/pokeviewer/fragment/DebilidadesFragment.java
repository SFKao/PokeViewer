package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.WeaknessAdapter;
import com.sfkao.pokeviewer.modelo.Pokemon;

import java.util.ArrayList;

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
        recyclerDebilidades = (RecyclerView) this.context.findViewById(R.id.recyclerDebilidades);
        recyclerDebilidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(context);
        recyclerDebilidades.setLayoutManager(layoutManagerDebilidades);
        recyclerDebilidades.setAdapter(recyclerDebilidadesAdapter);

        recyclerInmunidades = (RecyclerView) this.context.findViewById(R.id.recyclerInmunidades);
        recyclerInmunidadesAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerInmunidades = new LinearLayoutManager(context);
        recyclerInmunidades.setLayoutManager(layoutManagerInmunidades);
        recyclerInmunidades.setAdapter(recyclerInmunidadesAdapter);

        recyclerResistencias = (RecyclerView) this.context.findViewById(R.id.recyclerResistencias);
        recyclerResistenciasAdapter = new WeaknessAdapter();
        RecyclerView.LayoutManager layoutManagerResistencias = new LinearLayoutManager(context);
        recyclerResistencias.setLayoutManager(layoutManagerResistencias);
        recyclerResistencias.setAdapter(recyclerResistenciasAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debilidades, container, false);
    }


    public void mostrarDebilidadesPokemon(Pokemon pokemon){
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
    }

}