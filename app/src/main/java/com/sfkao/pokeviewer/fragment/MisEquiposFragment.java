package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
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
import com.sfkao.pokeviewer.realm.EquipoRealm;
import com.sfkao.pokeviewer.realm.EquipoRealmOperaciones;
import com.sfkao.pokeviewer.utils.Login;

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
        recyclerEquipos = view.findViewById(R.id.recycler_mis_equipos);
        adapterEquipos = new EquipoAdapter(context);
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
                if(((EquipoAdapter)adapterEquipos).getEquipos().get(pos).getApiId()!=null){
                    adapterEquipos.notifyItemChanged(pos);
                    Toast.makeText(context, R.string.no_se_puede_editar_un_equipo_subido, Toast.LENGTH_SHORT).show();
                    return;
                }
                //Izquierda borra
                if(direction == ItemTouchHelper.LEFT){
                    EquipoRealmOperaciones.borrarEquipo((EquipoRealm) ((EquipoAdapter)adapterEquipos).getEquipos().get(pos));
                    ((EquipoAdapter)adapterEquipos).getEquipos().remove(pos);
                    adapterEquipos.notifyItemRemoved(pos);
                //Derecha edita
                }else if(direction == ItemTouchHelper.RIGHT){
                    DialogFragment anyadirEquipo = new NuevoEquipoFragment(EquipoRealmOperaciones.getEquipos().get(pos),pos);
                    FragmentManager fm = context.getSupportFragmentManager();
                    anyadirEquipo.show(fm, "Añadir equipo");
                    ((EquipoAdapter)adapterEquipos).setEquipos( EquipoRealmOperaciones.getEquiposForAdapter());
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
                ((EquipoAdapter)adapterEquipos).setEquipos( EquipoRealmOperaciones.getEquiposForAdapter());
            }
        });

        ((EquipoAdapter)adapterEquipos).setEquipos( EquipoRealmOperaciones.getEquiposForAdapter());
    }

    @Override
    public boolean onItemLongClicked(EquipoForAdapterInterface e) {
        if(Login.isInvited()){
            Toast.makeText(context, getString(R.string.necesitas_estar_logueado), Toast.LENGTH_SHORT).show();
            return true;
        }
        if(e.getApiId()==null) {
            EquipoForAdapterInterface devuelto = PokeviewerConexion.getInstance().subirEquipo(e);
            int pos = ((EquipoAdapter) adapterEquipos).getEquipos().indexOf(e);
            EquipoRealmOperaciones.actualizaSubeEquipo((EquipoRealm) e, devuelto.getApiId(), Login.getUsername());
            adapterEquipos.notifyItemChanged(pos);
        }else{
            boolean borrado = PokeviewerConexion.getInstance().borrarEquipo(e.getApiId());
            if(borrado) {
                int pos = ((EquipoAdapter) adapterEquipos).getEquipos().indexOf(e);
                EquipoRealmOperaciones.actualizaSubeEquipo((EquipoRealm) e,null, e.getUser());
                adapterEquipos.notifyItemChanged(pos);
            }else{
                Toast.makeText(context, R.string.hubo_un_error, Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity)context;
        super.onAttach(context);
    }
}