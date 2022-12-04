package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.Equipo;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;

import java.util.ArrayList;
import java.util.List;

public class EquiposOnlineFragment extends Fragment {

    RecyclerView recyclerEquipos;
    EquipoAdapter adapterEquipos;

    MainActivity context;

    int cargados = 0;
    int aCargar = 10;

    boolean cargandoElementos;

    public EquiposOnlineFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static EquiposOnlineFragment newInstance(String param1, String param2) {
        return new EquiposOnlineFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Obtengo sus views
        recyclerEquipos = (RecyclerView) view.findViewById(R.id.recycler_equipos_online);
        adapterEquipos = new EquipoAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(getContext());
        recyclerEquipos.setLayoutManager(layoutManagerDebilidades);
        recyclerEquipos.setAdapter(adapterEquipos);

        recyclerEquipos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!cargandoElementos) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if(linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() >= adapterEquipos.getItemCount()-5){
                        cargandoElementos = true;
                        cargarMas();
                    }
                }
            }
        });
        cargandoElementos = true;
        cargarMas();
    }

    private void cargarMas() {
        Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                List<EquipoApi> equipoApis = PokeviewerConexion.getInstance().getEquipos(aCargar, cargados);
                cargados+=aCargar;
                List<Equipo> toAdd = new ArrayList<>();
                equipoApis.forEach((equipoApi -> toAdd.add(equipoApi.load())));
                handler.post(() -> {
                    adapterEquipos.finnishedLoading(toAdd);
                    cargandoElementos = false;
                });
            }
        }.start();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipos_online, container, false);
    }
}