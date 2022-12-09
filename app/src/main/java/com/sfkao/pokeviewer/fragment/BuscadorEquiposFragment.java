package com.sfkao.pokeviewer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo.EquipoApi;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;

public class BuscadorEquiposFragment extends DialogFragment {
    private View view;
    private ImageView[] pokemons;
    private TextView nombre, autor, codigo, likes, favoritos;
    private Button favoritosButton, likeButton, salirButton;
    private ImageButton buscarButton;
    private EditText idInput;

    public ImageView likeImg, favImg;

    private EquipoApi equipo;

    private MainActivity context;

    public BuscadorEquiposFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.buscador_equipo_fragment,null);
        pokemons = new ImageView[6];
        pokemons[0] = view.findViewById(R.id.imagenPokemon1);
        pokemons[1] = view.findViewById(R.id.imagenPokemon2);
        pokemons[2] = view.findViewById(R.id.imagenPokemon3);
        pokemons[3] = view.findViewById(R.id.imagenPokemon4);
        pokemons[4] = view.findViewById(R.id.imagenPokemon5);
        pokemons[5] = view.findViewById(R.id.imagenPokemon6);

        nombre = view.findViewById(R.id.nombre_equipo);
        autor = view.findViewById(R.id.autor_equipo);
        codigo = view.findViewById(R.id.codigo_equipo);
        likes = view.findViewById(R.id.likes_equipo);
        favoritos = view.findViewById(R.id.favoritos_equipo);

        likeImg = view.findViewById(R.id.equipo_like_img);
        favImg = view.findViewById(R.id.equipo_favorito_img);

        favoritosButton = view.findViewById(R.id.favoritos_button);
        likeButton = view.findViewById(R.id.like_button);
        salirButton = view.findViewById(R.id.salir_button);

        buscarButton = view.findViewById(R.id.buscar_equipo);
        idInput = view.findViewById(R.id.id_equipo_input);


        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipo = PokeviewerConexion.getInstance().getEquipo(idInput.getText().toString());
                if(equipo==null){
                    Toast.makeText(context, R.string.equipo_no_encontrado, Toast.LENGTH_SHORT).show();
                    return;
                }
                imprimirEquipo();
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        return alertDialogBuilder.create();
    }

    private void imprimirEquipo(){
        nombre.setText(equipo.getName());
        autor.setText(MessageFormat.format("{0}{1}", view.getResources().getString(R.string.autor), equipo.getUser()));
        codigo.setText(MessageFormat.format("{0}{1}", view.getResources().getString(R.string.id), equipo.getId()));
        likes.setText(String.valueOf(equipo.getLikes()));
        favoritos.setText(String.valueOf(equipo.getFavs()));
        //Coloco las 6 imagenes de los pokemon
        for (int i = 0; i < pokemons.length; i++) {
            if (equipo.isPokemon(i)) {
                Picasso.get().load(equipo.getPokImg(i)).into(pokemons[i]);
            }
        }
        if(equipo.getDadoLike()){
            likeImg.setImageResource(R.drawable.like_presssed);
            likeImg.setColorFilter(ContextCompat.getColor(context, R.color.lime), android.graphics.PorterDuff.Mode.SRC_IN);
        }else{
            likeImg.setImageResource(R.drawable.like_unpressed);
            likeImg.setColorFilter(ContextCompat.getColor(context, R.color.text), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        if(equipo.getDadoFav()){
            favImg.setImageResource(R.drawable.star_pressed);
            favImg.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
        }else{
            favImg.setImageResource(R.drawable.star_unpressed);
            favImg.setColorFilter(ContextCompat.getColor(context, R.color.text), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
}
