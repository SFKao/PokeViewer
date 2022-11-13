package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link debilidadesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class debilidadesFragment extends Fragment {

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

    public debilidadesFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment debilidades.
     */
    // TODO: Rename and change types and number of parameters
    public static debilidadesFragment newInstance(String param1, String param2) {
        debilidadesFragment fragment = new debilidadesFragment();
        return fragment;
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

        context.setRecyclerDebilidadesAdapter(recyclerDebilidadesAdapter);
        context.setRecyclerResistenciasAdapter(recyclerResistenciasAdapter);
        context.setRecyclerInmunidadesAdapter(recyclerInmunidadesAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debilidades, container, false);
    }

}