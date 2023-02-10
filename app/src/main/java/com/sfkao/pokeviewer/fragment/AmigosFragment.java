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
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.UsuariosAdapter;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;
import com.sfkao.pokeviewer.utils.Login;

import java.util.List;

public class AmigosFragment extends Fragment implements UsuariosAdapter.OnItemLongClickListener {

    RecyclerView recyclerAmigos;
    UsuariosAdapter adapterAmigos;
    FloatingActionButton buscadorButton, solicitudesButton;

    MainActivity context;
    boolean cargandoElementos;
    boolean viendoSolicitudes = false;
    List<AmigoApi> solicitudesDeAmistad, amigos;

    public AmigosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Obtengo sus views
        recyclerAmigos = (RecyclerView) view.findViewById(R.id.recycler_mis_amigos);
        buscadorButton = view.findViewById(R.id.buscador_button);
        solicitudesButton = view.findViewById(R.id.peticiones_button);
        adapterAmigos = new UsuariosAdapter(context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAmigos.setLayoutManager(layoutManager);
        adapterAmigos.setOnItemLongClickListener(this);
        recyclerAmigos.setAdapter(adapterAmigos);

        buscadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment buscadorAmigo = new BuscarAmigoDialog();
                FragmentManager fm = context.getSupportFragmentManager();
                buscadorAmigo.show(fm, "Buscar usuario");
            }
        });

        solicitudesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viendoSolicitudes){
                    adapterAmigos.setAmigos(amigos);
                    viendoSolicitudes = false;
                    solicitudesButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_mark_email_read_24, null));
                }else{
                    if(solicitudesDeAmistad==null){
                        Toast.makeText(context, R.string.no_tienes_solicitudes, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    adapterAmigos.setAmigos(solicitudesDeAmistad);
                    viendoSolicitudes = true;
                    solicitudesButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_back_24, null));
                }
            }
        });


        cargandoElementos = true;
        cargar();

    }

    private void cargar() {
        if(Login.isInvited()){
            Toast.makeText(context,R.string.necesitas_estar_logueado, Toast.LENGTH_LONG).show();
            return;
        }
        Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                adapterAmigos.getAmigos().add(null);
                amigos = PokeviewerConexion.getInstance().getAmigos(Login.getUsuario().getApi_key());
                if(amigos == null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Amigos null", Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }
                solicitudesDeAmistad = PokeviewerConexion.getInstance().getSolicitudesDeAmistad(Login.getUsuario().getApi_key());
                if(solicitudesDeAmistad !=null && solicitudesDeAmistad.size()>0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            solicitudesButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_mark_email_unread_24, null));
                            Toast.makeText(context, R.string.hay_solicitudes_de_amistad_pentientes, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                handler.post(() -> {
                    adapterAmigos.setAmigos(amigos);
                    cargandoElementos = false;
                });
            }
        }.start();


    }

    @Override
    public boolean onItemLongClicked(AmigoApi e) {
        Toast.makeText(context, e.getUsername(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amigos, container, false);
    }
}