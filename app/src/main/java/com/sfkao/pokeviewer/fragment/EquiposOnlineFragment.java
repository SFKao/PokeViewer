package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;

import java.util.ArrayList;
import java.util.List;

public class EquiposOnlineFragment extends Fragment {

    RecyclerView recyclerEquipos;
    EquipoAdapter adapterEquipos;
    FloatingActionButton buscadorButton;

    MainActivity context;

    int cargados = 0;
    int aCargar = 20;

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
        buscadorButton = view.findViewById(R.id.buscador_button);
        adapterEquipos = new EquipoAdapter(context);
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(getContext());
        recyclerEquipos.setLayoutManager(layoutManagerDebilidades);
        recyclerEquipos.setAdapter(adapterEquipos);

        recyclerEquipos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!cargandoElementos) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if(linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() >= cargados-aCargar/2){
                        cargandoElementos = true;
                        cargarMas();
                    }
                }
            }
        });

        buscadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment buscadorEquipo = new BuscadorEquiposFragment();
                FragmentManager fm = context.getSupportFragmentManager();
                buscadorEquipo.show(fm, "Buscar equipo");
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
                if(equipoApis == null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Equipo null", Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }
                cargados+=aCargar;
                List<EquipoForAdapterInterface> toAdd = new ArrayList<>(equipoApis);
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