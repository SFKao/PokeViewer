package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.utils.Login;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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

        //Permite arrastrar los elementos del recycler para que este haga acciones.
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //Al deslizarse izquierda o derecha
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getBindingAdapterPosition();
                if(Login.isInvited()){
                    Toast.makeText(context, R.string.necesitas_estar_logueado, Toast.LENGTH_SHORT).show();
                    adapterEquipos.notifyItemChanged(pos);
                    return;
                }
                boolean exito = false;
                //Izquierda like
                if(direction == ItemTouchHelper.LEFT){
                    if(adapterEquipos.getEquipos().get(pos).getDadoLike()){
                        exito = PokeviewerConexion.getInstance().quitarLike(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                        if(exito){
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setLikes(adapterEquipos.getEquipos().get(pos).getLikes()-1);
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoLike(false);
                            if(adapterEquipos.getEquipos().get(pos).getDadoFav()) {
                                exito = PokeviewerConexion.getInstance().quitarFavorito(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                                if(exito){
                                    ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setFavs(((EquipoApi) adapterEquipos.getEquipos().get(pos)).getFavs()-1);
                                    ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoFavoritos(false);
                                }else
                                    Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                            }
                        }else
                            Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                    }else{
                        exito = PokeviewerConexion.getInstance().darLike(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                        if(exito){
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setLikes(adapterEquipos.getEquipos().get(pos).getLikes()+1);
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoLike(true);
                        }else
                            Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                    }
                //Derecha favorito
                }else if(direction == ItemTouchHelper.RIGHT){
                    if(adapterEquipos.getEquipos().get(pos).getDadoFav()){
                        exito = PokeviewerConexion.getInstance().quitarFavorito(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                        if(exito){
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setFavs(((EquipoApi) adapterEquipos.getEquipos().get(pos)).getFavs()-1);
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoFavoritos(false);
                        }else
                            Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                    }else{
                        exito = PokeviewerConexion.getInstance().darFavorito(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                        if(exito){
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setFavs(((EquipoApi) adapterEquipos.getEquipos().get(pos)).getFavs()+1);
                            ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoFavoritos(true);
                            if(!adapterEquipos.getEquipos().get(pos).getDadoLike()) {
                                exito = PokeviewerConexion.getInstance().darLike(adapterEquipos.getEquipos().get(pos).getApiId(), Login.getUsuario().getApi_key());
                                if(exito){
                                    ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setLikes(adapterEquipos.getEquipos().get(pos).getLikes()+1);
                                    ((EquipoApi)adapterEquipos.getEquipos().get(pos)).setDadoLike(true);
                                }else
                                    Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                            }
                        }else
                            Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                    }
                }

            }

            //Parte de una libreria de terceros llamada RecyclerViewSwipeDecorator.
            //Añade el fondo verde y rojo al desilizar junto a sus iconos
            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.lime))
                        .addSwipeLeftActionIcon(R.drawable.like_unpressed)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                        .addSwipeRightActionIcon(R.drawable.star_unpressed)
                        .addSwipeLeftCornerRadius(TypedValue.COMPLEX_UNIT_DIP,50)
                        .addSwipeRightCornerRadius(TypedValue.COMPLEX_UNIT_DIP,50)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        //Se lo acoplo al recycler
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerEquipos);

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