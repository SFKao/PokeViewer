package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;
import com.sfkao.pokeviewer.utils.Login;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class VisualizarAmigoFragment extends Fragment {

    private MainActivity context;

    private ImageView friendStatus;
    private TextView friendUsername;
    private TextView friendLikes;
    private TextView friendFavs;
    private ImageView friendPok1;
    private ImageView friendPok2;
    private ImageView friendPok3;
    private RecyclerView recyclerEquiposAmigo;
    private EquipoAdapter adapterEquipos;
    private Button volverButton;
    private Button borrarAmistadButton;
    private  boolean estasSeguroBorrar = false;

    private ArrayList<EquipoForAdapterInterface> equipoDeAmigoApiList = new ArrayList<>();
    AmigoApi amigoApi;

    public VisualizarAmigoFragment() {
        // Required empty public constructor
    }

    public static VisualizarAmigoFragment newInstance(AmigoApi amigo) {
        VisualizarAmigoFragment fragment = new VisualizarAmigoFragment();
        Bundle args = new Bundle();
        args.putSerializable("amigo",amigo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initView(context);

        if (getArguments() != null) {
            amigoApi = (AmigoApi) getArguments().getSerializable("amigo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualizar_amigo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initView(context);


        friendUsername.setText(amigoApi.getUsername());
        friendLikes.setText(String.valueOf(amigoApi.getLikes()));
        friendFavs.setText(String.valueOf(amigoApi.getFavoritos()));
        if(amigoApi.getPk1()!=null)
            Picasso.get().load(amigoApi.getPk1().img).into(friendPok1);
        if(amigoApi.getPk2()!=null)
            Picasso.get().load(amigoApi.getPk2().img).into(friendPok2);
        if(amigoApi.getPk3()!=null)
            Picasso.get().load(amigoApi.getPk3().img).into(friendPok3);
        switch (amigoApi.getEstadoAmistad()){
            case "pendiente":
                friendStatus.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.ic_baseline_email_24,null));
                break;
            case "aceptada":
                friendStatus.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.equipos,null));
                break;
            default:
                friendStatus.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.bug,null));
        }
        equipoDeAmigoApiList.addAll(amigoApi.getEquipos());

        adapterEquipos = new EquipoAdapter(context);
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(context);
        recyclerEquiposAmigo.setLayoutManager(layoutManagerDebilidades);
        recyclerEquiposAmigo.setAdapter(adapterEquipos);

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

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new AmigosFragment());
            }
        });

        borrarAmistadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!estasSeguroBorrar){
                    Toast.makeText(context, R.string.estas_seguro_de_ello, Toast.LENGTH_SHORT).show();
                    estasSeguroBorrar = true;
                    return;
                }
                PokeviewerConexion.getInstance().borrarAmigo(Login.getUsuario().getApi_key(),amigoApi.getUsername());
                changeFragment(new AmigosFragment());
            }
        });

        //Se lo acoplo al recycler
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerEquiposAmigo);

        adapterEquipos.setEquipos(equipoDeAmigoApiList);
        adapterEquipos.notifyDataSetChanged();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(MainActivity context) {
        friendStatus = (ImageView) context.findViewById(R.id.friend_status_info);
        friendUsername = (TextView) context.findViewById(R.id.friend_username_info);
        friendLikes = (TextView) context.findViewById(R.id.friend_likes_info);
        friendFavs = (TextView) context.findViewById(R.id.friend_favs_info);
        friendPok1 = (ImageView) context.findViewById(R.id.friend_pok1_info);
        friendPok2 = (ImageView) context.findViewById(R.id.friend_pok2_info);
        friendPok3 = (ImageView) context.findViewById(R.id.friend_pok3_info);
        recyclerEquiposAmigo = (RecyclerView) context.findViewById(R.id.recycler_equipos_amigo_info);
        volverButton = (Button) context.findViewById(R.id.volver_button_info);
        borrarAmistadButton = (Button) context.findViewById(R.id.borrar_amistad_button_info);
    }

    private void cargar(){
        Handler handler = new Handler();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    private void changeFragment(Fragment f){
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_main, f);
        ft.commit();
    }
}