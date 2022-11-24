package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.sfkao.pokeviewer.utils.EquipoSingleton;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MisEquipos extends Fragment {

    RecyclerView recyclerEquipos;
    RecyclerView.Adapter adapterEquipos;

    FloatingActionButton floatingActionButton;

    MainActivity context;

    public MisEquipos() {
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

        recyclerEquipos = (RecyclerView) view.findViewById(R.id.recycler_mis_equipos);
        adapterEquipos = new EquipoAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(getContext());
        recyclerEquipos.setLayoutManager(layoutManagerDebilidades);
        recyclerEquipos.setAdapter(adapterEquipos);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getBindingAdapterPosition();
                if(direction == ItemTouchHelper.LEFT){
                    EquipoSingleton.getEquipos().remove(pos);
                    adapterEquipos.notifyItemRemoved(pos);
                    EquipoSingleton.guardarEquipos(context);
                }else if(direction == ItemTouchHelper.RIGHT){
                    DialogFragment anyadirEquipo = new new_equipo_fragment(EquipoSingleton.getEquipos().get(pos),pos);
                    FragmentManager fm = context.getSupportFragmentManager();
                    anyadirEquipo.show(fm, "Añadir equipo");
                    adapterEquipos.notifyItemChanged(pos);
                }
            }

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerEquipos);


        floatingActionButton= view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment anyadirEquipo = new new_equipo_fragment();
                FragmentManager fm = context.getSupportFragmentManager();
                anyadirEquipo.show(fm, "Añadir equipo");
            }
        });
        ((EquipoAdapter)adapterEquipos).setEquipos(EquipoSingleton.cargarEquipos(context));
    }



    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity)context;
        super.onAttach(context);
    }
}