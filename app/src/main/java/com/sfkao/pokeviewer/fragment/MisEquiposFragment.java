package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
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
import com.sfkao.pokeviewer.modelo.Equipo;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.utils.EquipoSingleton;
import com.sfkao.pokeviewer.utils.Login;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * Fragmento que almacena mis equipos, se invoca desde el menu lateral
 */
public class MisEquiposFragment extends Fragment implements EquipoAdapter.OnItemLongClickListener {

    RecyclerView recyclerEquipos;
    RecyclerView.Adapter adapterEquipos;

    FloatingActionButton floatingActionButton;

    MainActivity context;

    public MisEquiposFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_equipos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Obtengo sus views
        recyclerEquipos = (RecyclerView) view.findViewById(R.id.recycler_mis_equipos);
        adapterEquipos = new EquipoAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(getContext());
        recyclerEquipos.setLayoutManager(layoutManagerDebilidades);
        recyclerEquipos.setAdapter(adapterEquipos);

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
                //Izquierda borra
                if(direction == ItemTouchHelper.LEFT){
                    if(EquipoSingleton.getEquipos().get(pos).getId()!=null){
                        Toast.makeText(context, R.string.no_se_puede_borrar_un_equipo_subido, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                        return;
                    }
                    EquipoSingleton.getEquipos().remove(pos);
                    ((EquipoAdapter)adapterEquipos).getEquipos().remove(pos);
                    EquipoSingleton.guardarEquipos(context);
                    adapterEquipos.notifyItemRemoved(pos);
                //Derecha edita
                }else if(direction == ItemTouchHelper.RIGHT){
                    if(EquipoSingleton.getEquipos().get(pos).getId()!=null){
                        Toast.makeText(context, R.string.no_se_puede_editar_un_equipo_subido, Toast.LENGTH_SHORT).show();
                        adapterEquipos.notifyItemChanged(pos);
                        return;
                    }
                    DialogFragment anyadirEquipo = new NuevoEquipoFragment(EquipoSingleton.getEquipos().get(pos),pos);
                    FragmentManager fm = context.getSupportFragmentManager();
                    anyadirEquipo.show(fm, "Añadir equipo");
                    adapterEquipos.notifyItemChanged(pos);
                }

            }

            //Parte de una libreria de terceros llamada RecyclerViewSwipeDecorator.
            //Añade el fondo verde y rojo al desilizar junto a sus iconos
            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive){

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.dark_red))
                        .addSwipeLeftActionIcon(R.drawable.delete)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.lime))
                        .addSwipeRightActionIcon(R.drawable.edit)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        //Se lo acoplo al recycler
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerEquipos);

        ((EquipoAdapter)adapterEquipos).setOnItemLongClickListener(this);

        //Hago que el boton llame a un dialog fragment al ser pulsado
        floatingActionButton= view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment anyadirEquipo = new NuevoEquipoFragment();
                FragmentManager fm = context.getSupportFragmentManager();
                anyadirEquipo.show(fm, "Añadir equipo");
            }
        });

        //Coloco los equipos del singleton
        Handler handler = new Handler();
        new Thread(){
            @Override
            public void run() {
                ArrayList<Equipo> es = EquipoSingleton.cargarEquipos(context);
                ArrayList<EquipoForAdapterInterface> in = new ArrayList<>(es);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((EquipoAdapter)adapterEquipos).setEquipos(in);
                    }
                });
            }
        }.start();
    }

    @Override
    public boolean onItemLongClicked(EquipoForAdapterInterface e) {
        if(Login.isInvited()){
            Toast.makeText(context, getString(R.string.necesitas_estar_logueado), Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e.getId()==null){
            Equipo devuelto = PokeviewerConexion.getInstance().subirEquipo((Equipo) e);
            int pos = ((EquipoAdapter)adapterEquipos).getEquipos().indexOf(e);
            ((EquipoAdapter)adapterEquipos).getEquipos().set(pos,devuelto);
            adapterEquipos.notifyItemChanged(pos);
            EquipoSingleton.guardarEquipos(context);
            return true;
        }else{
            boolean borrado = PokeviewerConexion.getInstance().borrarEquipo(e.getId());
            if(borrado) {
                Toast.makeText(context, R.string.se_ha_borrado_el_equipo, Toast.LENGTH_SHORT).show();
                int pos = ((EquipoAdapter)adapterEquipos).getEquipos().indexOf(e);
                ((Equipo)((EquipoAdapter)adapterEquipos).getEquipos().get(pos)).setIdentificador(null);
                adapterEquipos.notifyItemChanged(pos);
                EquipoSingleton.guardarEquipos(context);
            }
            return borrado;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity)context;
        super.onAttach(context);
    }
}